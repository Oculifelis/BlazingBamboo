package net.paddedshaman.blazingbamboo.mixin;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat.Status;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.material.Fluid;
import net.paddedshaman.blazingbamboo.entity.BBRaftUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
@Mixin(AbstractBoat.class) public abstract class BoatMixin extends net.minecraft.world.entity.Entity {
 protected BoatMixin(net.minecraft.world.entity.EntityType<?> type, net.minecraft.world.level.Level level) { super(type,level); }
 @org.spongepowered.asm.mixin.Shadow private double waterLevel;
 @org.spongepowered.asm.mixin.Shadow private double lastYd;
 @org.spongepowered.asm.mixin.Shadow private float landFriction;
 @org.spongepowered.asm.mixin.Shadow private float deltaRotation;
 @org.spongepowered.asm.mixin.Shadow private AbstractBoat.Status status;
 @org.spongepowered.asm.mixin.Shadow private AbstractBoat.Status oldStatus;
 @org.spongepowered.asm.mixin.Shadow public abstract float getWaterLevelAbove();
 @org.spongepowered.asm.mixin.injection.Inject(method="floatBoat",at=@At("HEAD"),cancellable=true)
 private void blazingbamboo$buoyancy(org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
  if (!BBRaftUtil.isRaft(this)) return;

        float friction;
        double d0 = (double)-0.03999999910593033F;
        double d1 = this.isNoGravity() ? 0.0D : (double)-0.03999999910593033F;
        double d2 = 0.0D;
        friction = 0.05F;
        if (this.oldStatus == Status.IN_AIR && this.status != Status.IN_AIR && this.status != Status.ON_LAND) {
            this.waterLevel = this.getY(1.0D);
            this.setPos(this.getX(), (double)(this.getWaterLevelAbove() - this.getBbHeight()) + 0.101D, this.getZ());
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
            this.lastYd = 0.0D;
            this.status = Status.IN_WATER;
        } else {
            if (this.status == Status.IN_WATER) {
                d2 = (this.waterLevel - this.getY()) / (double)this.getBbHeight();
                friction = 0.9F;
            } else if (this.status == Status.UNDER_FLOWING_WATER) {
                d1 = -7.0E-4D;
                friction = 0.9F;
            } else if (this.status == Status.UNDER_WATER) {
                d2 = 0.01F;
                friction = 0.45F;
            } else if (this.status == Status.IN_AIR) {
                friction = 0.9F;
            } else if (this.status == Status.ON_LAND) {
                friction = this.landFriction;
                if (this.getControllingPassenger() instanceof Player) {
                    this.landFriction /= 2.0F;
                }
            }

            Vec3 vector3d = this.getDeltaMovement();
            this.setDeltaMovement(vector3d.x * (double)friction, vector3d.y + d1, vector3d.z * (double)friction);
            this.deltaRotation *= friction;
            if (d2 > 0.0D) {
                Vec3 vector3d1 = this.getDeltaMovement();
                this.setDeltaMovement(vector3d1.x, (vector3d1.y + d2 * 0.06153846016296973D) * 0.75D, vector3d1.z);
            }
        }
    
  ci.cancel();
 }


 @org.spongepowered.asm.mixin.injection.Inject(method="getWaterLevelAbove",at=@At("HEAD"),cancellable=true)
 private void blazingbamboo$levelAbove(org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable<Float> cir) { if(BBRaftUtil.isRaft(this))cir.setReturnValue(blazingbamboo$legacylevelAbove()); }
 private float blazingbamboo$legacylevelAbove() {
        AABB boundingBox = this.getBoundingBox();
        int minX = Mth.floor(boundingBox.minX);
        int maxX = Mth.ceil(boundingBox.maxX);
        int maxY = Mth.floor(boundingBox.maxY);
        int minY = Mth.ceil(boundingBox.maxY - this.lastYd);
        int minZ = Mth.floor(boundingBox.minZ);
        int maxZ = Mth.ceil(boundingBox.maxZ);
        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();

        for(int y = maxY; y < minY; ++y) {
            float f = 0.0F;
            for(int x = minX; x < maxX; ++x) {
                for(int z = minZ; z < maxZ; ++z) {
                    blockPos.set(x, y, z);
                    FluidState fluidstate = this.level().getFluidState(blockPos);
                    if (fluidstate.is(FluidTags.LAVA)) {
                        f = Math.max(f, fluidstate.getHeight(this.level(), blockPos));
                    }
                }
            }
            if (f < 1.0F) {
                return blockPos.getY() + f;
            }
        }
        return minY + 1;
    }

 @org.spongepowered.asm.mixin.injection.Inject(method="checkInWater",at=@At("HEAD"),cancellable=true)
 private void blazingbamboo$inLava(org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable<Boolean> cir) { if(BBRaftUtil.isRaft(this))cir.setReturnValue(blazingbamboo$legacyinLava()); }
 private boolean blazingbamboo$legacyinLava() {
        AABB aabb = this.getBoundingBox();
        int i = Mth.floor(aabb.minX);
        int j = Mth.ceil(aabb.maxX);
        int k = Mth.floor(aabb.minY);
        int l = Mth.ceil(aabb.minY + 0.001D);
        int i1 = Mth.floor(aabb.minZ);
        int j1 = Mth.ceil(aabb.maxZ);
        boolean flag = false;
        this.waterLevel = -Double.MAX_VALUE;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int k1 = i; k1 < j; ++k1) {
            for(int l1 = k; l1 < l; ++l1) {
                for(int i2 = i1; i2 < j1; ++i2) {
                    blockpos$mutableblockpos.set(k1, l1, i2);
                    FluidState fluidstate = this.level().getFluidState(blockpos$mutableblockpos);
                    if (fluidstate.is(FluidTags.LAVA)) {
                        float f = (float)l1 + fluidstate.getHeight(this.level(), blockpos$mutableblockpos);
                        this.waterLevel = Math.max((double)f, this.waterLevel);
                        flag |= aabb.minY < (double)f;
                    }
                }
            }
        }
        return flag;
    }

 @org.spongepowered.asm.mixin.injection.Inject(method="isUnderwater",at=@At("HEAD"),cancellable=true)
 private void blazingbamboo$underLava(org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable<Status> cir) { if(BBRaftUtil.isRaft(this))cir.setReturnValue(blazingbamboo$legacyunderLava()); }
 private Status blazingbamboo$legacyunderLava() {
        AABB aabb = this.getBoundingBox();
        double d0 = aabb.maxY + 0.5D;
        int i = Mth.floor(aabb.minX);
        int j = Mth.ceil(aabb.maxX);
        int k = Mth.floor(aabb.maxY);
        int l = Mth.ceil(d0);
        int i1 = Mth.floor(aabb.minZ);
        int j1 = Mth.ceil(aabb.maxZ);
        boolean flag = false;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int k1 = i; k1 < j; ++k1) {
            for(int l1 = k; l1 < l; ++l1) {
                for(int i2 = i1; i2 < j1; ++i2) {
                    blockpos$mutableblockpos.set(k1, l1, i2);
                    FluidState fluidstate = this.level().getFluidState(blockpos$mutableblockpos);
                    if (fluidstate.is(FluidTags.LAVA) && d0 < (double)((float)blockpos$mutableblockpos.getY() + fluidstate.getHeight(this.level(), blockpos$mutableblockpos))) {
                        if (!fluidstate.isSource()) {
                            return Status.UNDER_FLOWING_WATER;
                        }

                        flag = true;
                    }
                }
            }
        }
        return flag ? Status.UNDER_WATER : null;
    }
}
