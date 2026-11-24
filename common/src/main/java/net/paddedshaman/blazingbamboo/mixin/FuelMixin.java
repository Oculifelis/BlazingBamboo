package net.paddedshaman.blazingbamboo.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntSortedMap;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.FuelValues;
import net.paddedshaman.blazingbamboo.BambooRules.FurnaceTime;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FuelValues.Builder.class)
public abstract class FuelMixin {
    @Shadow @Final private HolderLookup<Item> items;
    @Shadow @Final private Object2IntSortedMap<Item> values;
    @Unique private HolderLookup.RegistryLookup<FurnaceTime> blazingbamboo$times;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void blazingbamboo$readTimes(HolderLookup.Provider registries, FeatureFlagSet features, CallbackInfo ci) {
        blazingbamboo$times = registries.lookup(FurnaceTime.KEY).orElse(null);
    }

    @Inject(method = "build", at = @At("HEAD"))
    private void blazingbamboo$applyTimes(CallbackInfoReturnable<FuelValues> ci) {
        if (blazingbamboo$times == null) return;
        blazingbamboo$times.listElements().forEach(entry -> {
            var key = ResourceKey.create(Registries.ITEM, entry.key().identifier());
            items.get(key).ifPresent(item -> {
                int ticks = entry.value().ticks();
                if (ticks == 0) values.removeInt(item.value());
                else values.put(item.value(), ticks);
            });
        });
    }
}
