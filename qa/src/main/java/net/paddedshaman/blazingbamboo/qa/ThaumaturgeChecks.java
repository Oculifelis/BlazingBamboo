package net.paddedshaman.blazingbamboo.qa;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.LinkedHashMap;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.paddedshaman.blazingbamboo.item.BBItems;
public final class ThaumaturgeChecks {
 private static Map<String,Integer> amounts(Object list) throws Exception {
  Map<String,Integer> out=new LinkedHashMap<>();
  for(Object entry:(Iterable<?>)list.getClass().getMethod("entries").invoke(list)) {
   Holder<?> aspect=(Holder<?>)entry.getClass().getMethod("aspect").invoke(entry);
   String key=aspect.unwrapKey().orElseThrow().toString();
   out.put(key,((Number)entry.getClass().getMethod("amount").invoke(entry)).intValue());
  }
  return out;
 }
 private static int amount(Map<String,Integer> values,String id) { return values.entrySet().stream().filter(e->e.getKey().contains("thaumaturge:"+id+"]")).mapToInt(Map.Entry::getValue).sum(); }
 public static void run(ServerLevel level) throws Exception {
  Class<?> access;
  try {access=Class.forName("com.leclowndu93150.thaumaturge.api.aspect.AspectIndexAccess");}
  catch(ClassNotFoundException absent){System.out.println("BBQA THAUMATURGE ABSENT PASS");return;}
  Method lookup=access.getMethod("of",ItemStack.class);
  int count=0;
  for(var item:BuiltInRegistries.ITEM) if(BuiltInRegistries.ITEM.getKey(item).getNamespace().equals("blazingbamboo")) {
   Map<String,Integer> values=amounts(lookup.invoke(null,new ItemStack(item)));
   PortChecks.check(!values.isEmpty(),"aspects "+BuiltInRegistries.ITEM.getKey(item));count++;
  }
  PortChecks.check(count==29,"all 29 obtainable items have aspects");
  var bamboo=amounts(lookup.invoke(null,new ItemStack(BBItems.BLAZING_BAMBOO_ITEM)));
  PortChecks.check(amount(bamboo,"ignis")==4&&amount(bamboo,"herba")==1,"bamboo 4 Ignis 1 Herba");
  var rods=amounts(lookup.invoke(null,new ItemStack(Items.BLAZE_ROD)));
  PortChecks.check(amount(rods,"ignis")>0&&amount(rods,"potentia")>0,"vanilla rod Ignis and Potentia");
  BlockPos pos=new BlockPos(10,100,0);
  var block=BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("thaumaturge","crucible"));
  level.setBlockAndUpdate(pos,block.defaultBlockState());
  var crucible=level.getBlockEntity(pos);PortChecks.check(crucible!=null,"crucible placed");
  var fake=Class.forName("net.neoforged.neoforge.common.util.FakePlayerFactory").getMethod("getMinecraft",ServerLevel.class).invoke(null,level);
  var fluidClass=Class.forName("net.neoforged.neoforge.fluids.FluidStack");
  var water=fluidClass.getConstructor(net.minecraft.world.level.material.Fluid.class,int.class).newInstance(net.minecraft.world.level.material.Fluids.WATER,1000);
  var tank=crucible.getClass().getMethod("getTank").invoke(crucible);tank.getClass().getMethod("setFluid",fluidClass).invoke(tank,water);
  var boiling=crucible.getClass().getDeclaredField("BOIL_HEAT");boiling.setAccessible(true);
  var heat=crucible.getClass().getDeclaredField("heat");heat.setAccessible(true);heat.setShort(crucible,(short)boiling.getInt(null));
  var remainder=(ItemStack)crucible.getClass().getMethod("attemptSmelt",ItemStack.class,Player.class).invoke(crucible,new ItemStack(BBItems.BLAZING_BAMBOO_ITEM),fake);
  PortChecks.check(remainder==null||remainder.isEmpty(),"crucible dissolves bamboo");
  var gained=amounts(crucible.getClass().getMethod("getAspects").invoke(crucible));
  PortChecks.check(amount(gained,"ignis")==4&&amount(gained,"herba")==1,"crucible receives exact bamboo aspects");
  System.out.println("BBQA THAUMATURGE ASPECTS CRUCIBLE PASS");
 }
}
