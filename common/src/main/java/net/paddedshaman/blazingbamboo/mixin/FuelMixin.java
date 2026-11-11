package net.paddedshaman.blazingbamboo.mixin;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.item.ItemStack;
import net.paddedshaman.blazingbamboo.item.BBItems;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(FuelValues.class) public abstract class FuelMixin {
 @Inject(method="burnDuration",at=@At("HEAD"),cancellable=true)
 private void blazingbamboo$fuel(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
  if(stack.is(BBItems.BLAZING_BAMBOO_ITEM))cir.setReturnValue(800);
  else if(stack.is(BBItems.BLAZING_BAMBOO_BUNDLE))cir.setReturnValue(8000);
  else if(stack.is(BBBlocks.BLAZING_BAMBOO_PLANKS.asItem())||stack.is(BBBlocks.BLAZING_BAMBOO_SLAB.asItem()))cir.setReturnValue(0);
 }
 @Inject(method="isFuel",at=@At("HEAD"),cancellable=true)
 private void blazingbamboo$isFuel(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
  if(stack.is(BBItems.BLAZING_BAMBOO_ITEM)||stack.is(BBItems.BLAZING_BAMBOO_BUNDLE))cir.setReturnValue(true);
  else if(stack.is(BBBlocks.BLAZING_BAMBOO_PLANKS.asItem())||stack.is(BBBlocks.BLAZING_BAMBOO_SLAB.asItem()))cir.setReturnValue(false);
 }
}
