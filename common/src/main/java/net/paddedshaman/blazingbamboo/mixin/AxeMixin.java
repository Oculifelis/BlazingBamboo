package net.paddedshaman.blazingbamboo.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.component.BlockTransformer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.component.BlockTransformers;
import net.minecraft.world.item.context.UseOnContext;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockTransformer.class)
public abstract class AxeMixin {
    @Unique
    private static final ResourceKey<BlockTransformer> blazingbamboo$STRIPPING =
            ResourceKey.create(Registries.BLOCK_TRANSFORMER, BlazingBamboo.id("stripping"));

    @ModifyExpressionValue(method = {
            "transformBlock(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;",
            "transformBlock(Lnet/minecraft/world/item/context/UseOnContext;Lnet/minecraft/core/Holder;)Lnet/minecraft/world/InteractionResult;"
    }, at = @At(value = "FIELD", target = "Lnet/minecraft/core/component/BlockTransformer;transforms:Ljava/util/List;"))
    private List<BlockTransformer.BlockTransformData> blazingbamboo$strip(
            List<BlockTransformer.BlockTransformData> original, UseOnContext context) {
        var tool = context.getItemInHand().get(DataComponents.BLOCK_TRANSFORMER);
        if (tool == null || !tool.is(BlockTransformers.AXE)) return original;
        var rules = context.getLevel().registryAccess().lookupOrThrow(Registries.BLOCK_TRANSFORMER)
                .get(blazingbamboo$STRIPPING);
        if (rules.isEmpty()) return original;
        var extra = rules.get().value().transforms();
        var result = new ArrayList<BlockTransformer.BlockTransformData>(extra.size() + original.size());
        result.addAll(extra);
        result.addAll(original);
        return result;
    }
}
