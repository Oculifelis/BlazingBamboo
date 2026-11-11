package net.paddedshaman.blazingbamboo.entity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.paddedshaman.blazingbamboo.mixin.BoatAccess;
public class BBRaftUtil {
 public static boolean isRaft(Object entity) { return entity instanceof BBRaftEntity || entity instanceof BBChestRaftEntity; }
 public static boolean isImmune(Entity entity) {
  if (!isRaft(entity.getVehicle())) return false;
  AbstractBoat.Status status=((BoatAccess)entity.getVehicle()).blazingbamboo$status();
  return status!=AbstractBoat.Status.UNDER_WATER && status!=AbstractBoat.Status.UNDER_FLOWING_WATER;
 }
}
