package net.paddedshaman.blazingbamboo.mixin;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
@Mixin(WoodType.class) public interface WoodTypeAccess {
 @Invoker("register") static WoodType register(WoodType type) { throw new AssertionError(); }
}
