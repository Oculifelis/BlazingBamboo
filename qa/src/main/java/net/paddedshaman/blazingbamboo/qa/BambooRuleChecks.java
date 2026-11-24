package net.paddedshaman.blazingbamboo.qa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.item.BBItems;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.paddedshaman.blazingbamboo.worldgen.BlazingBambooFeature;

public final class BambooRuleChecks {
    private BambooRuleChecks() {}

    public static void run(MinecraftServer server, boolean override) throws IOException {
        check(LegacyFuel.duration(new ItemStack(BBItems.BLAZING_BAMBOO_ITEM), RecipeType.SMELTING) == (override ? 325 : 800), "stalk fuel");
        check(LegacyFuel.duration(new ItemStack(BBItems.BLAZING_BAMBOO_BUNDLE), RecipeType.SMELTING) == (override ? 0 : 8000), "bundle fuel");
        check(LegacyFuel.duration(new ItemStack(BBBlocks.BLAZING_BAMBOO_PLANKS), RecipeType.SMELTING) == (override ? 99 : 0), "plank fuel");
        check(LegacyFuel.duration(new ItemStack(BBBlocks.BLAZING_BAMBOO_SLAB), RecipeType.SMELTING) == 0, "slab fuel");
        check(LegacyFuel.duration(new ItemStack(Items.FLINT), RecipeType.SMELTING) == (override ? 77 : 0), "new fuel");

        ServerLevel level = server.getLevel(Level.NETHER);
        check(level != null, "Nether available");
        check(hasPatch(level, Biomes.CRIMSON_FOREST) == !override, "crimson forest selection");
        check(hasPatch(level, Biomes.WARPED_FOREST) == override, "warped forest selection");
        BlockPos pos = new BlockPos(8, 190, 8);
        check(patchGrows(level, pos, Blocks.CRIMSON_NYLIUM) == !override, "crimson nylium placement");
        check(patchGrows(level, pos, Blocks.WARPED_NYLIUM) == override, "warped nylium placement");

        for (Block plant : new Block[]{BBBlocks.BLAZING_BAMBOO_SAPLING, BBBlocks.BLAZING_BAMBOO}) {
            check(growsOn(level, pos, plant, Blocks.PACKED_ICE) == override, "packed ice below " + plant);
            check(growsOn(level, pos, plant, Blocks.GOLD_BLOCK) == !override, "gold below " + plant);
            check(expiresBeside(level, pos, plant, Blocks.WATER) == !override, "water beside " + plant);
            check(expiresBeside(level, pos, plant, Blocks.GLASS) == override, "glass beside " + plant);
        }
        var bundle = BBBlocks.BLAZING_BAMBOO_BUNDLE.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);
        level.setBlock(pos, bundle, 2);
        ItemStack axe = new ItemStack(Items.IRON_AXE);
        var hit = new BlockHitResult(Vec3.atCenterOf(pos), Direction.UP, pos, false);
        Player player = new Player(level, pos, 0f, new GameProfile(UUID.randomUUID(), "BambooCheck")) {
            @Override public boolean isCreative() { return false; }
            @Override public boolean isSpectator() { return false; }
        };
        player.setItemInHand(InteractionHand.MAIN_HAND, axe);
        axe.getItem().useOn(new UseOnContext(player, InteractionHand.MAIN_HAND, hit));
        var stripped = level.getBlockState(pos);
        check(stripped.is(override ? Blocks.STRIPPED_BIRCH_LOG : BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE)
                && stripped.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.X, "stripped result and axis");
        Files.writeString(Path.of("bamboo-rules.passed"), override ? "override" : "defaults");
    }

    private static boolean hasPatch(ServerLevel level, ResourceKey<Biome> biome) {
        var key = ResourceKey.create(Registries.PLACED_FEATURE, BlazingBamboo.id("blazing_bamboo_placed"));
        var settings = level.registryAccess().registryOrThrow(Registries.BIOME).getHolderOrThrow(biome).value().getGenerationSettings();
        return settings.features().stream().anyMatch(step -> step.stream().anyMatch(feature -> feature.is(key)));
    }

    private static void clear(ServerLevel level, BlockPos pos) {
        for (BlockPos at : BlockPos.betweenClosed(pos.offset(-1, -2, -1), pos.offset(1, 16, 1))) {
            level.setBlock(at, Blocks.AIR.defaultBlockState(), 2);
        }
    }

    private static boolean patchGrows(ServerLevel level, BlockPos pos, Block soil) {
        clear(level, pos);
        level.setBlock(pos.below(), soil.defaultBlockState(), 2);
        var feature = level.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolderOrThrow(
                ResourceKey.create(Registries.PLACED_FEATURE, BlazingBamboo.id("blazing_bamboo_placed")));
        var patch = new ConfiguredFeature<>(new BlazingBambooFeature(RandomPatchConfiguration.CODEC),
                new RandomPatchConfiguration(1, 0, 0, feature));
        patch.place(level, level.getChunkSource().getGenerator(), RandomSource.create(0), pos);
        return level.getBlockState(pos).is(BBBlocks.BLAZING_BAMBOO);
    }

    private static boolean growsOn(ServerLevel level, BlockPos pos, Block plant, Block substrate) {
        clear(level, pos);
        level.setBlock(pos.below(2), substrate.defaultBlockState(), 2);
        level.setBlock(pos.below(), Blocks.CRIMSON_NYLIUM.defaultBlockState(), 2);
        level.setBlock(pos, plant.defaultBlockState(), 2);
        level.getBlockState(pos).randomTick(level, pos, RandomSource.create(0));
        return level.getBlockState(pos.above()).is(BBBlocks.BLAZING_BAMBOO);
    }

    private static boolean expiresBeside(ServerLevel level, BlockPos pos, Block plant, Block neighbor) {
        clear(level, pos);
        level.setBlock(pos.below(), Blocks.CRIMSON_NYLIUM.defaultBlockState(), 2);
        level.setBlock(pos.east(), neighbor.defaultBlockState(), 2);
        level.setBlock(pos, plant.defaultBlockState(), 2);
        level.getBlockState(pos).randomTick(level, pos, RandomSource.create(0));
        return level.getBlockState(pos).is(plant == BBBlocks.BLAZING_BAMBOO ? BBBlocks.DEAD_BAMBOO : BBBlocks.DEAD_BAMBOO_SAPLING);
    }

    private static void check(boolean success, String label) {
        if (!success) throw new AssertionError(label);
    }
}
