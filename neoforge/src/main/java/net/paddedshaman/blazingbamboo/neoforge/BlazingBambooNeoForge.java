package net.paddedshaman.blazingbamboo.neoforge;

import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent.BlockToolModificationEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.paddedshaman.blazingbamboo.BambooRules;
import net.paddedshaman.blazingbamboo.BambooRules.FurnaceTime;
import net.paddedshaman.blazingbamboo.BambooRules.Stripping;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

@Mod(BlazingBamboo.MOD_ID)
public class BlazingBambooNeoForge {
    public BlazingBambooNeoForge(IEventBus bus) {
        bus.addListener((RegisterEvent event) -> {
            if (event.getRegistryKey().equals(Registries.BLOCK)) BlazingBamboo.initialize();
        });
        bus.addListener((DataPackRegistryEvent.NewRegistry event) -> {
            event.dataPackRegistry(FurnaceTime.KEY, FurnaceTime.CODEC, FurnaceTime.CODEC);
            event.dataPackRegistry(Stripping.KEY, Stripping.CODEC, Stripping.CODEC);
        });
        NeoForge.EVENT_BUS.addListener((BlockToolModificationEvent event) -> {
            if (event.getItemAbility() == ItemAbilities.AXE_STRIP) {
                BambooRules.strip(event.getContext().getLevel().registryAccess(), event.getState())
                        .ifPresent(event::setFinalState);
            }
        });
    }
}
