package net.paddedshaman.blazingbamboo;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.paddedshaman.blazingbamboo.worldgen.BambooPatchConfiguration;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.block.entity.BBBlockEntities;
import net.paddedshaman.blazingbamboo.entity.BBEntities;
import net.paddedshaman.blazingbamboo.item.BBItems;
import net.paddedshaman.blazingbamboo.item.BBCreativeModeTabs;
import net.paddedshaman.blazingbamboo.worldgen.BlazingBambooFeature;
public final class BlazingBamboo {
 public static final String MOD_ID="blazingbamboo";
 public static Identifier id(String name) { return Identifier.fromNamespaceAndPath(MOD_ID,name); }
 public static java.util.function.UnaryOperator<net.minecraft.world.level.block.state.properties.WoodType> woodTypeRegistrar = net.paddedshaman.blazingbamboo.mixin.WoodTypeAccess::register;
 public static void initialize() {
  BBBlocks.registerBlocks(); BBItems.registerItems(); BBBlockEntities.registerBlockEntities(); BBEntities.registerEntities(); BBCreativeModeTabs.registerCreativeModeTabs();
  Registry.register(BuiltInRegistries.FEATURE,id("blazing_bamboo"),new BlazingBambooFeature(BambooPatchConfiguration.CODEC));
 }
}
