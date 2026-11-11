package net.paddedshaman.blazingbamboo.mixin;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.component.BlockTransformer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.BlockTransformers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.sounds.SoundEvents;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import java.util.List;
import java.util.ArrayList;
@Mixin(BlockTransformer.class) public abstract class AxeMixin {
 @ModifyExpressionValue(method={"transformBlock(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;","transformBlock(Lnet/minecraft/world/item/context/UseOnContext;Lnet/minecraft/core/Holder;)Lnet/minecraft/world/InteractionResult;"},at=@At(value="FIELD",target="Lnet/minecraft/core/component/BlockTransformer;transforms:Ljava/util/List;"))
 private List<BlockTransformer.BlockTransformData> blazingbamboo$strip(List<BlockTransformer.BlockTransformData> original,UseOnContext context) {
  var tool=context.getItemInHand().get(DataComponents.BLOCK_TRANSFORMER);
  var state=context.getLevel().getBlockState(context.getClickedPos());
  if(tool==null||!tool.is(BlockTransformers.AXE)||!state.is(BBBlocks.BLAZING_BAMBOO_BUNDLE))return original;
  var stripped=BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.defaultBlockState().setValue(RotatedPillarBlock.AXIS,state.getValue(RotatedPillarBlock.AXIS));
  var result=new ArrayList<BlockTransformer.BlockTransformData>(original.size()+1);
  result.add(BlockTransformer.BlockTransformData.builder(BlockStateProvider.of(stripped)).sound(SoundEvents.AXE_STRIP).itemDamagePerUse(1).build());
  result.addAll(original);return result;
 }
}
