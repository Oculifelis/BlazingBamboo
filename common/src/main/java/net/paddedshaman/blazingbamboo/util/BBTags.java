package net.paddedshaman.blazingbamboo.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

public class BBTags {
    public static final TagKey<Biome> GROWS_IN = TagKey.create(Registries.BIOME, BlazingBamboo.id("grows_in"));

    public static class Blocks {
        public static final TagKey<Block> PATCH_SOIL = tag("patch_soil");
        public static final TagKey<Block> FREEZES_GROWTH = tag("freezes_growth");
        public static final TagKey<Block> EXTINGUISHES_BAMBOO = tag("extinguishes_bamboo");
        public static final TagKey<Block> BLAZING_BAMBOO_PLANTABLE_ON = tag("blazing_bamboo_plantable_on");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(BuiltInRegistries.BLOCK.key(), Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, name));
        }
    }
}
