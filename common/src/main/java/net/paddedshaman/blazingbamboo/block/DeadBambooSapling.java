package net.paddedshaman.blazingbamboo.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class DeadBambooSapling extends BlazingBambooSapling {
    public DeadBambooSapling(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity, net.minecraft.world.entity.InsideBlockEffectApplier effects, boolean precise) {}

    @Override
    public BlockState updateShape(BlockState pState, LevelReader pLevel, net.minecraft.world.level.ScheduledTickAccess ticks, BlockPos pCurrentPos, Direction pFacing, BlockPos pFacingPos, BlockState pFacingState, RandomSource random) {
        if (!pState.canSurvive(pLevel, pCurrentPos))
            return Blocks.AIR.defaultBlockState();
        return this.defaultBlockState();
    }

    @Override
    @NotNull
    public ItemStack getCloneItemStack(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean includeData) {
        return new ItemStack(Items.GUNPOWDER);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return false;
    }
    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {}
    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, net.minecraft.world.level.block.BonemealSource source) { return false; }
}
