package net.paddedshaman.blazingbamboo.qa;

import net.minecraft.gametest.framework.GameTestHelper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

final class BambooChecks {
    private BambooChecks() {}

    static void check(GameTestHelper h) {
        PortChecks.run(h.getLevel().getServer());
        try {
            Files.writeString(Path.of("blazingbamboo_checks.passed"), "bamboo_behaviour");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        h.succeed();
    }
}
