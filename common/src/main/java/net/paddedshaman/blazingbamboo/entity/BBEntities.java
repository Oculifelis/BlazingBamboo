package net.paddedshaman.blazingbamboo.entity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class BBEntities {
    public static final EntityType<BBRaftEntity> BB_RAFT = Registry.register(BuiltInRegistries.ENTITY_TYPE, "blazingbamboo:bb_raft",
            EntityType.Builder.<BBRaftEntity>of(BBRaftEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).fireImmune().build(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, net.minecraft.resources.Identifier.fromNamespaceAndPath("blazingbamboo", "bb_raft"))));
    public static final EntityType<BBChestRaftEntity> BB_CHEST_RAFT = Registry.register(BuiltInRegistries.ENTITY_TYPE, "blazingbamboo:bb_chest_raft",
            EntityType.Builder.<BBChestRaftEntity>of(BBChestRaftEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).fireImmune().build(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, net.minecraft.resources.Identifier.fromNamespaceAndPath("blazingbamboo", "bb_chest_raft"))));

    public static void registerEntities() {}
}
