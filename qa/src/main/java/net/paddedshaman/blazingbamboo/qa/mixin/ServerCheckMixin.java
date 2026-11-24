package net.paddedshaman.blazingbamboo.qa.mixin;
import net.paddedshaman.blazingbamboo.qa.BambooRuleChecks;
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
   try {
    String rules = System.getProperty("blazingbamboo.ruleTest");
    if (rules == null) PortChecks.run(server);
    else BambooRuleChecks.run(server, rules.equals("override"));
    System.out.println("BBQA SERVER PASS");
   }
   catch(Throwable t) {System.out.println("BBQA SERVER FAIL");t.printStackTrace();}
   finally {server.halt(false);}
  }
 }
}
