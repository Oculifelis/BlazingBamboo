package net.paddedshaman.blazingbamboo.mixin;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
@Mixin(AbstractBoat.class) public interface BoatAccess { @Invoker("getStatus") AbstractBoat.Status blazingbamboo$status(); }
