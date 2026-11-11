package net.paddedshaman.blazingbamboo.neoforge;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.minecraft.core.registries.Registries;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
@Mod(BlazingBamboo.MOD_ID) public class BlazingBambooNeoForge {
 public BlazingBambooNeoForge(IEventBus bus) { bus.addListener((RegisterEvent event)->{if(event.getRegistryKey().equals(Registries.BLOCK))BlazingBamboo.initialize();}); net.neoforged.neoforge.common.NeoForge.EVENT_BUS.addListener(BlazingBambooNeoForge::fuel); }

 private static void fuel(net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent event) {
  var stack=event.getItemStack();
  if(stack.is(net.paddedshaman.blazingbamboo.item.BBItems.BLAZING_BAMBOO_ITEM))event.setBurnTime(800);
  else if(stack.is(net.paddedshaman.blazingbamboo.item.BBItems.BLAZING_BAMBOO_BUNDLE))event.setBurnTime(8000);
  else if(stack.is(net.paddedshaman.blazingbamboo.block.BBBlocks.BLAZING_BAMBOO_PLANKS.asItem())||stack.is(net.paddedshaman.blazingbamboo.block.BBBlocks.BLAZING_BAMBOO_SLAB.asItem()))event.setBurnTime(0);
 }
}
