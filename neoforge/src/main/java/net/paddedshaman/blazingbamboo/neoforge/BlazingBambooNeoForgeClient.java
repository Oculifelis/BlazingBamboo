package net.paddedshaman.blazingbamboo.neoforge;
import net.neoforged.fml.common.Mod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.minecraft.client.model.object.boat.RaftModel;
import net.minecraft.client.renderer.blockentity.StandingSignRenderer;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.entity.RaftRenderer;
import net.paddedshaman.blazingbamboo.block.entity.BBBlockEntities;
import net.paddedshaman.blazingbamboo.entity.BBEntities;
import net.paddedshaman.blazingbamboo.client.BBClient;
@Mod(value="blazingbamboo",dist=Dist.CLIENT) public class BlazingBambooNeoForgeClient {
 public BlazingBambooNeoForgeClient(IEventBus bus) { bus.addListener(BlazingBambooNeoForgeClient::layers);bus.addListener(BlazingBambooNeoForgeClient::renderers); }
 static void layers(EntityRenderersEvent.RegisterLayerDefinitions event) {
  event.registerLayerDefinition(BBClient.RAFT,RaftModel::createRaftModel);event.registerLayerDefinition(BBClient.CHEST_RAFT,RaftModel::createChestRaftModel);
 }
 static void renderers(EntityRenderersEvent.RegisterRenderers event) {
  BBClient.registerSignSprites();
  event.registerEntityRenderer(BBEntities.BB_RAFT,c->new RaftRenderer(c,BBClient.RAFT));event.registerEntityRenderer(BBEntities.BB_CHEST_RAFT,c->new RaftRenderer(c,BBClient.CHEST_RAFT));
  event.registerBlockEntityRenderer(BBBlockEntities.MOD_SIGN,StandingSignRenderer::new);event.registerBlockEntityRenderer(BBBlockEntities.MOD_HANGING_SIGN,HangingSignRenderer::new);
 }
}
