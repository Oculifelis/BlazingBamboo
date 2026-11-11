package net.paddedshaman.blazingbamboo.fabric;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
public class BlazingBambooFabric implements ModInitializer {
 public void onInitialize() { BlazingBamboo.initialize(); BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.CRIMSON_FOREST),GenerationStep.Decoration.VEGETAL_DECORATION,ResourceKey.create(Registries.PLACED_FEATURE,BlazingBamboo.id("blazing_bamboo_placed"))); }
}
