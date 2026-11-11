package net.paddedshaman.blazingbamboo.block.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.paddedshaman.blazingbamboo.util.BBDamageTypes;

public class BlazingFlowerPotBlock extends FlowerPotBlock {

    public BlazingFlowerPotBlock(Block content, Properties properties) {
        super(content, properties);
    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity, net.minecraft.world.entity.InsideBlockEffectApplier effects, boolean precise) {
        if (pEntity instanceof LivingEntity) {
            if (pLevel instanceof ServerLevel server) pEntity.hurtServer(server, new net.minecraft.world.damagesource.DamageSource(pLevel.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE).getOrThrow(BBDamageTypes.BLAZING_HOT)), 1.0F);
        }
    }
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pEntity instanceof LivingEntity) {
            if (pLevel instanceof ServerLevel server) pEntity.hurtServer(server, new net.minecraft.world.damagesource.DamageSource(pLevel.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE).getOrThrow(BBDamageTypes.BLAZING_HOT)), 1.0F);
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
