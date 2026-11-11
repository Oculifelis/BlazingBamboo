package net.paddedshaman.blazingbamboo.qa;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(Minecraft.class) public abstract class ClientCheckMixin {
 @Inject(method="tick",at=@At("TAIL")) private void bbqa$tick(CallbackInfo ci) { ClientCheck.tick((Minecraft)(Object)this); }
}
