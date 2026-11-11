package net.paddedshaman.blazingbamboo.entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.vehicle.boat.ChestRaft;
import net.minecraft.world.level.Level;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.paddedshaman.blazingbamboo.item.BBItems;
public class BBChestRaftEntity extends ChestRaft {
 public BBChestRaftEntity(EntityType<? extends BBChestRaftEntity> type, Level level) { super(type, level, () -> BBItems.BLAZING_BAMBOO_CHEST_RAFT); }
 @Override public boolean fireImmune() { return true; }
 @Override public boolean isOnFire() { return false; }
 @Override protected double rideHeight(EntityDimensions dimensions) { return 0.25; }
 @Override protected boolean canAddPassenger(Entity entity) { return getPassengers().size() < 1 && !isEyeInFluid(FluidTags.LAVA); }
 @Override protected void checkFallDamage(double delta, boolean ground, BlockState state, BlockPos pos) { ((net.paddedshaman.blazingbamboo.mixin.BoatAccess)this).blazingbamboo$lastYd(getDeltaMovement().y); }
}
