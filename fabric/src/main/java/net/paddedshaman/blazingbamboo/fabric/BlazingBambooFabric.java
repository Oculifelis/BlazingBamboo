package net.paddedshaman.blazingbamboo.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.paddedshaman.blazingbamboo.BambooRules.FurnaceTime;
import net.paddedshaman.blazingbamboo.BambooRules.Stripping;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.util.BBTags;

public class BlazingBambooFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BlazingBamboo.woodTypeRegistrar = type -> WoodTypeBuilder.copyOf(type)
                .register(Identifier.parse(type.name()), type.setType());
        BlazingBamboo.initialize();
        DynamicRegistries.registerSynced(FurnaceTime.KEY, FurnaceTime.CODEC);
        DynamicRegistries.registerSynced(Stripping.KEY, Stripping.CODEC);
        BiomeModifications.addFeature(BiomeSelectors.tag(BBTags.GROWS_IN),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, BlazingBamboo.id("blazing_bamboo_placed")));
    }
}
