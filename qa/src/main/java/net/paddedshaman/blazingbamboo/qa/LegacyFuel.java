package net.paddedshaman.blazingbamboo.qa;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
public class LegacyFuel {
 public static int duration(ItemStack stack,RecipeType<?> type) {
  try {return (int)Class.forName("net.neoforged.neoforge.common.CommonHooks").getMethod("getBurnTime",ItemStack.class,RecipeType.class).invoke(null,stack,type);}catch(Exception e){throw new IllegalStateException(e);}
 }
}
