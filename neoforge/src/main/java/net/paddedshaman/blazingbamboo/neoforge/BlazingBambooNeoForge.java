package net.paddedshaman.blazingbamboo.neoforge;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.minecraft.core.registries.Registries;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
@Mod(BlazingBamboo.MOD_ID) public class BlazingBambooNeoForge {
 public BlazingBambooNeoForge(IEventBus bus) { bus.addListener((RegisterEvent event)->{if(event.getRegistryKey().equals(Registries.BLOCK))BlazingBamboo.initialize();}); }
}
