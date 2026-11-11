package net.paddedshaman.blazingbamboo.mixin;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
@Mixin(Sheets.class) public interface SheetsAccess {
 @Invoker("createSignSprite") static SpriteId sign(WoodType type) {throw new AssertionError();}
 @Invoker("createHangingSignSprite") static SpriteId hanging(WoodType type) {throw new AssertionError();}
}
