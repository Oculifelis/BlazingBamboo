package net.paddedshaman.blazingbamboo.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.chunk.ChunkGenerator;
import com.mojang.serialization.MapCodec;
import net.paddedshaman.blazingbamboo.worldgen.BambooPatchConfiguration;
import net.paddedshaman.blazingbamboo.block.BBBlocks;

public class BlazingBambooFeature implements Feature {
    private static final BlockState BAMBOO_TRUNK = BBBlocks.BLAZING_BAMBOO.defaultBlockState().setValue(BambooStalkBlock.AGE, 1).setValue(BambooStalkBlock.LEAVES, BambooLeaves.NONE).setValue(BambooStalkBlock.STAGE, 0);
    private static final BlockState BAMBOO_FINAL_LARGE = BAMBOO_TRUNK.setValue(BambooStalkBlock.LEAVES, BambooLeaves.LARGE).setValue(BambooStalkBlock.STAGE, 1);
    private static final BlockState BAMBOO_TOP_LARGE = BAMBOO_TRUNK.setValue(BambooStalkBlock.LEAVES, BambooLeaves.LARGE);
    private static final BlockState BAMBOO_TOP_SMALL = BAMBOO_TRUNK.setValue(BambooStalkBlock.LEAVES, BambooLeaves.SMALL);

    public static final MapCodec<BlazingBambooFeature> CODEC=BambooPatchConfiguration.CODEC.xmap(BlazingBambooFeature::new,feature->feature.config);
    private final BambooPatchConfiguration config;
    public BlazingBambooFeature(BambooPatchConfiguration config) { this.config=config; }
    @Override public MapCodec<BlazingBambooFeature> codec() { return CODEC; }

    public boolean place(WorldGenLevel level, ChunkGenerator generator, RandomSource random, BlockPos origin) {
        BambooPatchConfiguration config = this.config;
        RandomSource randomOffset = random;
        int xzConfig = config.xzSpread() + 1;
        int yConfig = config.ySpread() + 1;
        int $$1 = 0;
        BlockPos pos = origin;
        RandomSource randomHeight = random;
        BlockPos.MutableBlockPos mutablePos = pos.mutable();
        for (int i = 0; i < config.tries(); ++i) {
            mutablePos.setWithOffset(pos,
                    randomOffset.nextInt(xzConfig) - randomOffset.nextInt(xzConfig),
                    randomOffset.nextInt(yConfig) - randomOffset.nextInt(yConfig),
                    randomOffset.nextInt(xzConfig) - randomOffset.nextInt(xzConfig));
            if (level.isEmptyBlock(mutablePos)) {
                if (BBBlocks.BLAZING_BAMBOO.defaultBlockState().canSurvive(level, mutablePos)
                        && level.getBlockState(mutablePos.below()).is(Blocks.CRIMSON_NYLIUM)) {
                    int height = randomHeight.nextInt(8) + 5;

                    for (int j = 0; j < height && level.isEmptyBlock(mutablePos); ++j) {
                        level.setBlock(mutablePos, BAMBOO_TRUNK, 2);
                        mutablePos.move(Direction.UP, 1);
                    }

                    if (mutablePos.getY() - pos.getY() >= 3) {
                        level.setBlock(mutablePos, BAMBOO_FINAL_LARGE, 2);
                        level.setBlock(mutablePos.move(Direction.DOWN, 1), BAMBOO_TOP_LARGE, 2);
                        level.setBlock(mutablePos.move(Direction.DOWN, 1), BAMBOO_TOP_SMALL, 2);
                    }
                }

                ++$$1;
            }
        }

        return $$1 > 0;
    }
}
