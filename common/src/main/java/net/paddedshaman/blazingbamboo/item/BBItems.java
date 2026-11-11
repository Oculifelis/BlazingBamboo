package net.paddedshaman.blazingbamboo.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BoatItem;
import net.paddedshaman.blazingbamboo.entity.BBEntities;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;

public class BBItems {
    public static final Item BLAZING_BAMBOO_ITEM = registerItem("blazing_bamboo_item",
            new BlockItem(BBBlocks.BLAZING_BAMBOO, new Item.Properties().overrideDescription("block.blazingbamboo.blazing_bamboo").setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_item"))).fireResistant().cookingFuel(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.CONTEXT_INT_PROVIDER, BlazingBamboo.id("cooking/bamboo")))));
    public static final Item BLAZING_BAMBOO_BUNDLE = registerItem("blazing_bamboo_bundle",
            new BlockItem(BBBlocks.BLAZING_BAMBOO_BUNDLE, new Item.Properties().useBlockDescriptionPrefix().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_bundle"))).fireResistant().cookingFuel(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.CONTEXT_INT_PROVIDER, BlazingBamboo.id("cooking/bundle")))));
    public static final Item BLAZING_BAMBOO_SIGN = registerItem("blazing_bamboo_sign",
            new SignItem(BBBlocks.BLAZING_BAMBOO_SIGN, BBBlocks.BLAZING_BAMBOO_WALL_SIGN, new Item.Properties().useBlockDescriptionPrefix().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_sign"))).fireResistant().stacksTo(16)));
    public static final Item BLAZING_BAMBOO_HANGING_SIGN = registerItem("blazing_bamboo_hanging_sign",
            new HangingSignItem(BBBlocks.BLAZING_BAMBOO_HANGING_SIGN, BBBlocks.BLAZING_BAMBOO_WALL_HANGING_SIGN,
                    new Item.Properties().useBlockDescriptionPrefix().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_hanging_sign"))).fireResistant().stacksTo(16)));
    public static final Item BLAZING_BAMBOO_RAFT = registerItem("blazing_bamboo_raft",
            new BoatItem(BBEntities.BB_RAFT, new Item.Properties().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_raft"))).fireResistant().stacksTo(1)));
    public static final Item BLAZING_BAMBOO_CHEST_RAFT = registerItem("blazing_bamboo_chest_raft",
            new BoatItem(BBEntities.BB_CHEST_RAFT, new Item.Properties().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_chest_raft"))).fireResistant().stacksTo(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, name), item);
    }
    public static void registerItems() {}
}
