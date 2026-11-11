package net.paddedshaman.blazingbamboo.qa.mixin;
import net.paddedshaman.blazingbamboo.qa.PortChecks;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(MinecraftServer.class) public abstract class ServerCheckMixin {
 private int bbqa$ticks;
 @Inject(method="tickServer",at=@At("TAIL")) private void bbqa$tick(CallbackInfo ci) {
  MinecraftServer server=(MinecraftServer)(Object)this;
  if(server.isDedicatedServer() && ++bbqa$ticks==40) {
   try { PortChecks.run(server); System.out.println("BBQA SERVER PASS"); }
   catch(Throwable t) {System.out.println("BBQA SERVER FAIL");t.printStackTrace();}
   finally {server.halt(false);}
  }
 }
}
