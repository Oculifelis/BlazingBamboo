package net.paddedshaman.blazingbamboo.client;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
public final class BBClient {
 public static final ModelLayerLocation RAFT=new ModelLayerLocation(BlazingBamboo.id("raft/blazing_bamboo"),"main");
 public static final ModelLayerLocation CHEST_RAFT=new ModelLayerLocation(BlazingBamboo.id("chest_raft/blazing_bamboo"),"main");

 public static void registerSignSprites() {
  var wood=net.paddedshaman.blazingbamboo.block.BBBlocks.BLAZING_BAMBOO_TYPE;
  net.minecraft.client.renderer.Sheets.SIGN_SPRITES.put(wood,net.paddedshaman.blazingbamboo.mixin.SheetsAccess.sign(wood));
  net.minecraft.client.renderer.Sheets.HANGING_SIGN_SPRITES.put(wood,net.paddedshaman.blazingbamboo.mixin.SheetsAccess.hanging(wood));
 }
}
