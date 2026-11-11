package net.paddedshaman.blazingbamboo.mixin;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import java.util.Optional;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(AxeItem.class) public abstract class AxeMixin {
 @Inject(method="getStripped",at=@At("HEAD"),cancellable=true)
 private void blazingbamboo$strip(BlockState state, CallbackInfoReturnable<Optional<BlockState>> cir) {
  if(state.is(BBBlocks.BLAZING_BAMBOO_BUNDLE))cir.setReturnValue(Optional.of(BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.defaultBlockState().setValue(RotatedPillarBlock.AXIS,state.getValue(RotatedPillarBlock.AXIS))));
 }
}
