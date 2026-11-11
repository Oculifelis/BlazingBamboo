package net.paddedshaman.blazingbamboo.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.util.BBDamageTypes;

public class BBRotatedPillarBlock extends RotatedPillarBlock {
    public BBRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }

    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pState.is(BBBlocks.BLAZING_BAMBOO_BUNDLE) && pEntity instanceof LivingEntity) {
            if (pLevel instanceof net.minecraft.server.level.ServerLevel server) pEntity.hurtServer(server, new net.minecraft.world.damagesource.DamageSource(pLevel.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE).getOrThrow(BBDamageTypes.BLAZING_HOT)), 2.0F);
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
