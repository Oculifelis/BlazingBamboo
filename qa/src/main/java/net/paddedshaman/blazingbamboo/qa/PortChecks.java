package net.paddedshaman.blazingbamboo.qa;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BambooStalkBlock;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.block.BlazingBambooBlock;
import net.paddedshaman.blazingbamboo.block.BlazingBambooSapling;
import net.paddedshaman.blazingbamboo.item.BBItems;
import net.paddedshaman.blazingbamboo.entity.BBEntities;
public final class PortChecks {
 static void check(boolean v,String message){if(!v)throw new AssertionError(message);System.out.println("BBQA CHECK "+message);}
 public static void run(MinecraftServer server) {
  ServerLevel level=server.overworld();
  check(BuiltInRegistries.ITEM.getKey(BBItems.BLAZING_BAMBOO_ITEM).getNamespace().equals("blazingbamboo"),"registry namespace");
  check(fuel(level,Blocks.FURNACE,new ItemStack(BBItems.BLAZING_BAMBOO_ITEM))==800,"bamboo fuel 800");
  check(fuel(level,Blocks.FURNACE,new ItemStack(BBItems.BLAZING_BAMBOO_BUNDLE))==8000,"bundle fuel 8000");
  check(fuel(level,Blocks.FURNACE,new ItemStack(BBBlocks.BLAZING_BAMBOO_PLANKS))==0,"planks not fuel");
  check(fuel(level,Blocks.FURNACE,new ItemStack(BBBlocks.BLAZING_BAMBOO_SLAB))==0,"slabs not fuel");
  var featureKey=net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.PLACED_FEATURE,net.paddedshaman.blazingbamboo.BlazingBamboo.id("blazing_bamboo_placed"));
  var crimson=level.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.BIOME).getOrThrow(net.minecraft.world.level.biome.Biomes.CRIMSON_FOREST).value();
  boolean hasFeature=false;for(var step:crimson.getGenerationSettings().features())for(var placed:step)if(placed.is(featureKey))hasFeature=true;
  check(hasFeature,"bamboo feature present in crimson forest");
  check(level.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.FEATURE).getOrThrow(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.FEATURE,net.paddedshaman.blazingbamboo.BlazingBamboo.id("blazing_bamboo")))!=null,"bamboo configuration loads");
  check(new ItemStack(BBItems.BLAZING_BAMBOO_ITEM).has(net.minecraft.core.component.DataComponents.COOKING_FUEL),"bamboo cooking component");
  check(!new ItemStack(BBBlocks.BLAZING_BAMBOO_PLANKS).has(net.minecraft.core.component.DataComponents.COOKING_FUEL),"planks have no cooking fuel");
  check(fuel(level,Blocks.BLAST_FURNACE,new ItemStack(BBItems.BLAZING_BAMBOO_ITEM))==400,"blast furnace fuel 400");
  check(fuel(level,Blocks.SMOKER,new ItemStack(BBItems.BLAZING_BAMBOO_BUNDLE))==4000,"smoker bundle fuel 4000");
  BlockPos base=new BlockPos(0,100,0);level.setBlockAndUpdate(base,Blocks.CRIMSON_NYLIUM.defaultBlockState());
  level.setBlockAndUpdate(base.above(),BBBlocks.BLAZING_BAMBOO_SAPLING.defaultBlockState());
  level.setBlockAndUpdate(base.east(),Blocks.WATER.defaultBlockState());
  ((BlazingBambooSapling)BBBlocks.BLAZING_BAMBOO_SAPLING).randomTick(BBBlocks.BLAZING_BAMBOO_SAPLING.defaultBlockState(),level,base.above(),level.getRandom());
  check(level.getBlockState(base.above()).is(BBBlocks.DEAD_BAMBOO_SAPLING),"sapling dies beside water");
  level.setBlockAndUpdate(base.above(),BBBlocks.BLAZING_BAMBOO.defaultBlockState());
  level.setBlockAndUpdate(base.above(2),BBBlocks.BLAZING_BAMBOO.defaultBlockState());
  ((BlazingBambooBlock)BBBlocks.BLAZING_BAMBOO).randomTick(level.getBlockState(base.above()),level,base.above(),level.getRandom());
  check(level.getBlockState(base.above()).is(BBBlocks.DEAD_BAMBOO)&&level.getBlockState(base.above(2)).is(BBBlocks.DEAD_BAMBOO),"whole stalk extinguishes");
  check(!((BlazingBambooBlock)BBBlocks.DEAD_BAMBOO).isValidBonemealTarget(level,base.above(),level.getBlockState(base.above()),net.minecraft.world.level.block.BonemealSource.INTERACTION),"dead bamboo cannot grow");
  var raft=BBEntities.BB_RAFT.create(level,EntitySpawnReason.COMMAND);var chest=BBEntities.BB_CHEST_RAFT.create(level,EntitySpawnReason.COMMAND);
  check(raft!=null&&raft.fireImmune()&&chest!=null&&chest.fireImmune(),"both rafts create and resist fire");
  raft.setPos(3,101,0);level.addFreshEntity(raft);chest.setPos(6,101,0);level.addFreshEntity(chest);
  level.setBlockAndUpdate(base.offset(3,0,0),Blocks.LAVA.defaultBlockState());level.setBlockAndUpdate(base.offset(6,0,0),Blocks.LAVA.defaultBlockState());
  for(var type:new net.minecraft.world.level.block.entity.BlockEntityType<?>[]{net.paddedshaman.blazingbamboo.block.entity.BBBlockEntities.MOD_SIGN,net.paddedshaman.blazingbamboo.block.entity.BBBlockEntities.MOD_HANGING_SIGN})check(type!=null,"sign entity type");
  System.out.println("BBQA MOD JAR "+BBBlocks.class.getProtectionDomain().getCodeSource().getLocation());
  try { ThaumaturgeChecks.run(level); } catch(Exception e) { throw new IllegalStateException(e); }
 }

 private static int fuel(ServerLevel level,net.minecraft.world.level.block.Block block,ItemStack item) {
  BlockPos pos=new BlockPos(20,100,0);level.setBlockAndUpdate(pos,block.defaultBlockState());
  try {
   var method=net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity.class.getDeclaredMethod("getBurnDuration",ServerLevel.class,ItemStack.class);
   method.setAccessible(true);return (int)method.invoke(level.getBlockEntity(pos),level,item);
  }catch(Exception e){throw new IllegalStateException(e);}
 }
}
