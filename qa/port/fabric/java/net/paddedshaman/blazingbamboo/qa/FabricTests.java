package net.paddedshaman.blazingbamboo.qa;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public final class FabricTests implements ModInitializer {
    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.TEST_FUNCTION, Identifier.fromNamespaceAndPath("blazingbamboo_checks", "bamboo_behaviour"),
                BambooChecks::check);
    }
}
