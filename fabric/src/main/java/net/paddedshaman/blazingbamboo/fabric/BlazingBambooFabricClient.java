package net.paddedshaman.blazingbamboo.fabric;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.object.boat.RaftModel;
import net.minecraft.client.renderer.blockentity.StandingSignRenderer;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.entity.RaftRenderer;
import net.paddedshaman.blazingbamboo.block.entity.BBBlockEntities;
import net.paddedshaman.blazingbamboo.entity.BBEntities;
import net.paddedshaman.blazingbamboo.client.BBClient;
public class BlazingBambooFabricClient implements ClientModInitializer {
 public void onInitializeClient() {
  BBClient.registerSignSprites();
  ModelLayerRegistry.registerModelLayer(BBClient.RAFT,RaftModel::createRaftModel);
  ModelLayerRegistry.registerModelLayer(BBClient.CHEST_RAFT,RaftModel::createChestRaftModel);
  EntityRendererRegistry.register(BBEntities.BB_RAFT,c->new RaftRenderer(c,BBClient.RAFT));
  EntityRendererRegistry.register(BBEntities.BB_CHEST_RAFT,c->new RaftRenderer(c,BBClient.CHEST_RAFT));
  BlockEntityRendererRegistry.register((net.minecraft.world.level.block.entity.BlockEntityType<net.minecraft.world.level.block.entity.SignBlockEntity>)(net.minecraft.world.level.block.entity.BlockEntityType<?>)BBBlockEntities.MOD_SIGN,StandingSignRenderer::new);
  BlockEntityRendererRegistry.register((net.minecraft.world.level.block.entity.BlockEntityType<net.minecraft.world.level.block.entity.SignBlockEntity>)(net.minecraft.world.level.block.entity.BlockEntityType<?>)BBBlockEntities.MOD_HANGING_SIGN,HangingSignRenderer::new);
 }
}
