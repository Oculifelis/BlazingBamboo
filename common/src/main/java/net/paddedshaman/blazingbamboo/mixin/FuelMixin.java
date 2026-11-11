package net.paddedshaman.blazingbamboo.mixin;
import it.unimi.dsi.fastutil.objects.Object2IntSortedMap;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.item.Item;
import net.paddedshaman.blazingbamboo.item.BBItems;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(FuelValues.Builder.class) public abstract class FuelMixin {
 @Shadow @Final private Object2IntSortedMap<Item> values;
 @Inject(method="build",at=@At("HEAD"))
 private void blazingbamboo$fuels(CallbackInfoReturnable<FuelValues> cir) {
  values.put(BBItems.BLAZING_BAMBOO_ITEM,800);
  values.put(BBItems.BLAZING_BAMBOO_BUNDLE,8000);
  values.removeInt(BBBlocks.BLAZING_BAMBOO_PLANKS.asItem());
  values.removeInt(BBBlocks.BLAZING_BAMBOO_SLAB.asItem());
 }
}
