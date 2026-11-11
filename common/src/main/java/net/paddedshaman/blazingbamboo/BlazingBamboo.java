package net.paddedshaman.blazingbamboo;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.block.entity.BBBlockEntities;
import net.paddedshaman.blazingbamboo.entity.BBEntities;
import net.paddedshaman.blazingbamboo.item.BBItems;
import net.paddedshaman.blazingbamboo.item.BBCreativeModeTabs;
import net.paddedshaman.blazingbamboo.worldgen.BlazingBambooFeature;
public final class BlazingBamboo {
 public static final String MOD_ID="blazingbamboo";
 public static ResourceLocation id(String name) { return ResourceLocation.fromNamespaceAndPath(MOD_ID,name); }
 public static void initialize() {
  BBBlocks.registerBlocks(); BBItems.registerItems(); BBBlockEntities.registerBlockEntities(); BBEntities.registerEntities(); BBCreativeModeTabs.registerCreativeModeTabs();
  Registry.register(BuiltInRegistries.FEATURE,id("blazing_bamboo"),new BlazingBambooFeature(RandomPatchConfiguration.CODEC));
 }
}
