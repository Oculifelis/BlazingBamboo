package net.paddedshaman.blazingbamboo.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import java.util.Optional;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.paddedshaman.blazingbamboo.BambooRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AxeItem.class)
public abstract class AxeMixin {
    @WrapOperation(method = "evaluateNewBlockState", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/item/AxeItem;getStripped(Lnet/minecraft/world/level/block/state/BlockState;)Ljava/util/Optional;"))
    private Optional<BlockState> blazingbamboo$strip(AxeItem axe, BlockState state,
            Operation<Optional<BlockState>> original, @Local(argsOnly = true) Level level) {
        return BambooRules.strip(level.registryAccess(), state).or(() -> original.call(axe, state));
    }
}
