package net.paddedshaman.blazingbamboo.qa;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.WorldDataConfiguration;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.levelgen.presets.WorldPresets;
public final class ClientCheck {
 private static boolean creating,done;private static int ticks,idle;
 public static void tick(Minecraft mc) {
  if(done)return;
  try {
   mc.options.pauseOnLostFocus=false;mc.options.getSoundSourceOptionInstance(SoundSource.MASTER).set(0.0);
   mc.options.framerateLimit().set(20);mc.options.renderDistance().set(3);mc.options.simulationDistance().set(5);
   if(mc.level==null || mc.player==null) {
    if(!creating && mc.getOverlay()==null && mc.screen instanceof TitleScreen title) {
     creating=true;
     LevelSettings settings=new LevelSettings("bbqa",GameType.CREATIVE,false,net.minecraft.world.Difficulty.NORMAL,true,new net.minecraft.world.level.GameRules(),WorldDataConfiguration.DEFAULT);
     mc.createWorldOpenFlows().createFreshLevel("bbqa",settings,new WorldOptions(1030015L,false,false),WorldPresets::createNormalWorldDimensions,title);
    } else if(!creating && mc.getOverlay()==null && ++idle>200)mc.setScreen(new TitleScreen());
    return;
   }
   if(++ticks==40) {
    mc.getSingleplayerServer().execute(()->{try {PortChecks.run(mc.getSingleplayerServer());System.out.println("BBQA CLIENT WORLD PASS");}catch(Throwable t){System.out.println("BBQA CLIENT FAIL");t.printStackTrace();}});
   }
   if(ticks>120) {done=true;mc.stop();}
  } catch(Throwable t){System.out.println("BBQA CLIENT FAIL");t.printStackTrace();done=true;mc.stop();}
 }
}
