package net.paddedshaman.blazingbamboo.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.custom.*;

public class BBBlocks {
    public static final WoodType BLAZING_BAMBOO_TYPE = BlazingBamboo.woodTypeRegistrar.apply(new WoodType(BlazingBamboo.MOD_ID + ":blazing_bamboo", BlockSetType.BAMBOO));

    public static final Block BLAZING_BAMBOO = Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo"),
            new BlazingBambooBlock(BlockBehaviour.Properties.of().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo"))).mapColor(MapColor.PLANT).forceSolidOn().randomTicks().instabreak()
                    .strength(1.0F, 6.0F).sound(SoundType.BAMBOO).lightLevel(pLightEmission -> 8)
                    .noOcclusion().dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.POPPED)));
    public static final Block DEAD_BAMBOO = Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "dead_bamboo"),
            new DeadBambooBlock(BlockBehaviour.Properties.of().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "dead_bamboo"))).mapColor(MapColor.COLOR_GRAY).forceSolidOn().instabreak()
                    .strength(0.0F, 6.0F).sound(SoundType.HANGING_ROOTS)
                    .noOcclusion().dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.POPPED)));
    public static final Block BLAZING_BAMBOO_SAPLING = Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_sapling"),
            new BlazingBambooSapling(BlockBehaviour.Properties.of().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_sapling"))).mapColor(MapColor.WOOD).forceSolidOn().randomTicks().instabreak().noCollision()
                    .strength(0.0F, 6.0F).sound(SoundType.BAMBOO_SAPLING).lightLevel(pLightEmission -> 5)
                    .noOcclusion().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.POPPED)));
    public static final Block DEAD_BAMBOO_SAPLING = Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "dead_bamboo_sapling"),
            new DeadBambooSapling(BlockBehaviour.Properties.of().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "dead_bamboo_sapling"))).mapColor(MapColor.COLOR_GRAY).forceSolidOn().instabreak().noCollision()
                    .strength(0.0F, 6.0F).sound(SoundType.HANGING_ROOTS)
                    .noOcclusion().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.POPPED)));
    
    public static final Block POTTED_BLAZING_BAMBOO = Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "potted_blazing_bamboo"),
            new BlazingFlowerPotBlock(BBBlocks.BLAZING_BAMBOO, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_BAMBOO).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "potted_blazing_bamboo"))).strength(0.0F, 6.0F)
                    .lightLevel(pLightEmission -> 6).noOcclusion()));

    public static final Block BLAZING_BAMBOO_BUNDLE = Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_bundle"),
            new BBRotatedPillarBlock(BlockBehaviour.Properties.of().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_bundle"))).mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 6.0F).lightLevel(pLightEmission -> 10).sound(SoundType.BAMBOO_WOOD)));
    public static final Block STRIPPED_BLAZING_BAMBOO_BUNDLE = registerBlock("stripped_blazing_bamboo_bundle",
            new BBRotatedPillarBlock(BlockBehaviour.Properties.of().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "stripped_blazing_bamboo_bundle"))).mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 6.0F).sound(SoundType.BAMBOO_WOOD)));

    // Wood derivatives
    public static final Block BLAZING_BAMBOO_PLANKS = registerBlock("blazing_bamboo_planks",
            new Block(BlockBehaviour.Properties.of().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_planks"))).mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 6.0F).sound(SoundType.BAMBOO_WOOD)));
    public static final Block BLAZING_BAMBOO_SLAB = registerBlock("blazing_bamboo_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(BBBlocks.BLAZING_BAMBOO_PLANKS).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_slab")))));
    public static final Block BLAZING_BAMBOO_STAIRS = registerBlock("blazing_bamboo_stairs",
            new StairBlock(BBBlocks.BLAZING_BAMBOO_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(BBBlocks.BLAZING_BAMBOO_PLANKS).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_stairs")))));
    public static final Block BLAZING_BAMBOO_MOSAIC = registerBlock("blazing_bamboo_mosaic",
            new Block(BlockBehaviour.Properties.ofFullCopy(BBBlocks.BLAZING_BAMBOO_PLANKS).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_mosaic")))));
    public static final Block BLAZING_BAMBOO_MOSAIC_SLAB = registerBlock("blazing_bamboo_mosaic_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(BBBlocks.BLAZING_BAMBOO_PLANKS).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_mosaic_slab")))));
    public static final Block BLAZING_BAMBOO_MOSAIC_STAIRS = registerBlock("blazing_bamboo_mosaic_stairs",
            new StairBlock(BBBlocks.BLAZING_BAMBOO_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(BBBlocks.BLAZING_BAMBOO_PLANKS).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_mosaic_stairs")))));
    public static final Block BLAZING_BAMBOO_BUTTON = registerBlock("blazing_bamboo_button",
            new BBButtonBlock(BlockSetType.BAMBOO, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_BUTTON).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_button"))).strength(0.5F, 6.0F)
                    .sound(SoundType.BAMBOO_WOOD).noCollision()));
    public static final Block BLAZING_BAMBOO_PRESSURE_PLATE = registerBlock("blazing_bamboo_pressure_plate",
            new BBPressurePlateBlock(BlockSetType.BAMBOO, BlockBehaviour.Properties.of().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_pressure_plate"))).mapColor(MapColor.COLOR_YELLOW)
                    .forceSolidOn().instrument(NoteBlockInstrument.BASS).sound(SoundType.BAMBOO_WOOD).noCollision()
                    .strength(0.5F, 6.0F).pushReaction(PushReaction.POPPED)));
    public static final Block BLAZING_BAMBOO_FENCE = registerBlock("blazing_bamboo_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(BBBlocks.BLAZING_BAMBOO_PLANKS).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_fence"))).forceSolidOn()));
    public static final Block BLAZING_BAMBOO_FENCE_GATE = registerBlock("blazing_bamboo_fence_gate",
            new FenceGateBlock(BLAZING_BAMBOO_TYPE, BlockBehaviour.Properties.ofFullCopy(BBBlocks.BLAZING_BAMBOO_PLANKS).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_fence_gate")))));
    public static final Block BLAZING_BAMBOO_DOOR = registerBlock("blazing_bamboo_door",
            new DoorBlock(BlockSetType.BAMBOO, BlockBehaviour.Properties.of().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_door"))).mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F, 6.0F).noOcclusion().pushReaction(PushReaction.POPPED)));
    public static final Block BLAZING_BAMBOO_TRAPDOOR = registerBlock("blazing_bamboo_trapdoor",
            new TrapDoorBlock(BlockSetType.BAMBOO, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_TRAPDOOR).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_trapdoor"))).sound(SoundType.BAMBOO_WOOD)
                    .strength(3.0F, 6.0F)));
    public static final Block BLAZING_BAMBOO_SIGN = Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_sign"),
            new BBStandingSignBlock(BlockBehaviour.Properties.of().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_sign"))).mapColor(MapColor.COLOR_YELLOW).forceSolidOn().instrument(NoteBlockInstrument.BASS)
                    .noCollision().strength(1.0F, 6.0F), BLAZING_BAMBOO_TYPE));
    public static final Block BLAZING_BAMBOO_WALL_SIGN = Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_wall_sign"),
            new BBWallSignBlock(BlockBehaviour.Properties.of().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_wall_sign"))).mapColor(MapColor.COLOR_YELLOW).forceSolidOn().instrument(NoteBlockInstrument.BASS)
                    .noCollision().strength(1.0F, 6.0F), BLAZING_BAMBOO_TYPE));
    public static final Block BLAZING_BAMBOO_HANGING_SIGN = Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_hanging_sign"),
            new BBHangingSignBlock(BlockBehaviour.Properties.of().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_hanging_sign"))).mapColor(MapColor.COLOR_YELLOW).forceSolidOn().instrument(NoteBlockInstrument.BASS)
                    .noCollision().strength(1.0F, 6.0F), BLAZING_BAMBOO_TYPE));
    public static final Block BLAZING_BAMBOO_WALL_HANGING_SIGN = Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_wall_hanging_sign"),
            new BBWallHangingSignBlock(BlockBehaviour.Properties.of().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_wall_hanging_sign"))).mapColor(MapColor.COLOR_YELLOW).forceSolidOn().instrument(NoteBlockInstrument.BASS)
                    .noCollision().strength(1.0F, 6.0F), BLAZING_BAMBOO_TYPE));

    // Stone derivatives

    public static final Block BLAZING_STONE = registerBlock("blazing_stone",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_stone"))).sound(SoundType.STONE)));
    public static final Block BLAZING_STONE_SLAB = registerBlock("blazing_stone_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_stone_slab"))).sound(SoundType.STONE)));
    public static final Block BLAZING_STONE_STAIRS = registerBlock("blazing_stone_stairs",
            new StairBlock(BBBlocks.BLAZING_STONE.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_stone_stairs"))).sound(SoundType.STONE)));
    public static final Block BLAZING_STONE_BUTTON = registerBlock("blazing_stone_button",
            new BBButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_stone_button"))).sound(SoundType.STONE).noCollision()));
    public static final Block BLAZING_STONE_PRESSURE_PLATE = registerBlock("blazing_stone_pressure_plate",
            new BBPressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_stone_pressure_plate"))).sound(SoundType.STONE).noCollision()));

    public static final Block BLAZING_BRICKS = registerBlock("blazing_bricks",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bricks"))).sound(SoundType.NETHER_BRICKS)));
    public static final Block BLAZING_BRICK_SLAB = registerBlock("blazing_brick_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_brick_slab"))).sound(SoundType.NETHER_BRICKS)));
    public static final Block BLAZING_BRICK_STAIRS = registerBlock("blazing_brick_stairs",
            new StairBlock(BBBlocks.BLAZING_BRICKS.defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_brick_stairs"))).sound(SoundType.NETHER_BRICKS)));
    public static final Block BLAZING_BRICK_WALL = registerBlock("blazing_brick_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_brick_wall"))).sound(SoundType.NETHER_BRICKS)));
    public static final Block BLAZING_BRICKS_CHISELED = registerBlock("blazing_bricks_chiseled",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bricks_chiseled"))).sound(SoundType.NETHER_BRICKS)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, name))).fireResistant()));
    }
    public static void registerBlocks() {}
}
