package net.paddedshaman.blazingbamboo.block.entity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;

public class BBBlockEntities {
    public static final BlockEntityType<BBSignBlockEntity> MOD_SIGN = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "mod_sign"),
            new BlockEntityType<>(BBSignBlockEntity::new, java.util.Set.of(BBBlocks.BLAZING_BAMBOO_SIGN, BBBlocks.BLAZING_BAMBOO_WALL_SIGN)));
    public static final BlockEntityType<BBHangingSignBlockEntity> MOD_HANGING_SIGN = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "mod_hanging_sign"),
            new BlockEntityType<>(BBHangingSignBlockEntity::new, java.util.Set.of(BBBlocks.BLAZING_BAMBOO_HANGING_SIGN, BBBlocks.BLAZING_BAMBOO_WALL_HANGING_SIGN)));

    public static void registerBlockEntities() {}
}
