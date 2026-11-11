package net.paddedshaman.blazingbamboo.neoforge;
import net.neoforged.fml.common.Mod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.minecraft.client.model.RaftModel;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.paddedshaman.blazingbamboo.entity.BBRaftRenderer;
import net.paddedshaman.blazingbamboo.block.entity.BBBlockEntities;
import net.paddedshaman.blazingbamboo.entity.BBEntities;

@Mod(value="blazingbamboo",dist=Dist.CLIENT) public class BlazingBambooNeoForgeClient {
 public BlazingBambooNeoForgeClient(IEventBus bus) { bus.addListener(BlazingBambooNeoForgeClient::layers);bus.addListener(BlazingBambooNeoForgeClient::renderers); }
 static void layers(EntityRenderersEvent.RegisterLayerDefinitions event) {
  event.registerLayerDefinition(BBRaftRenderer.BLAZING_BAMBOO_RAFT_LAYER,RaftModel::createBodyModel);event.registerLayerDefinition(BBRaftRenderer.BLAZING_BAMBOO_CHEST_RAFT_LAYER,net.minecraft.client.model.ChestRaftModel::createBodyModel);
 }
 static void renderers(EntityRenderersEvent.RegisterRenderers event) {
  event.registerEntityRenderer(BBEntities.BB_RAFT,c->new BBRaftRenderer(c,false));event.registerEntityRenderer(BBEntities.BB_CHEST_RAFT,c->new BBRaftRenderer(c,true));
  event.registerBlockEntityRenderer(BBBlockEntities.MOD_SIGN,SignRenderer::new);event.registerBlockEntityRenderer(BBBlockEntities.MOD_HANGING_SIGN,HangingSignRenderer::new);
 }
}
