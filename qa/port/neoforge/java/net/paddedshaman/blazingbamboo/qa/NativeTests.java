package net.paddedshaman.blazingbamboo.qa;

import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Consumer;

@Mod("blazingbamboo_checks")
public final class NativeTests {
    public NativeTests(IEventBus bus) {
        DeferredRegister<Consumer<GameTestHelper>> functions = DeferredRegister.create(Registries.TEST_FUNCTION, "blazingbamboo_checks");
        functions.register("bamboo_behaviour", () -> BambooChecks::check);
        functions.register(bus);
    }
}
