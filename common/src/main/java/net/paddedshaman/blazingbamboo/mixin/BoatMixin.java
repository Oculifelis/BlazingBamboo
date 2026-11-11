package net.paddedshaman.blazingbamboo.mixin;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.material.Fluid;
import net.paddedshaman.blazingbamboo.entity.BBRaftUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
@Mixin(AbstractBoat.class) public abstract class BoatMixin {
 @ModifyArg(method={"getWaterLevelAbove","checkInWater","isUnderwater"},at=@At(value="INVOKE",target="Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z"),index=0)
 private TagKey<Fluid> blazingbamboo$fluid(TagKey<Fluid> fluid) { return BBRaftUtil.isRaft(this) ? FluidTags.LAVA : fluid; }
}
