package net.paddedshaman.blazingbamboo;

import com.mojang.serialization.Codec;
import java.util.Optional;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public final class BambooRules {
    public record FurnaceTime(int ticks) {
        public static final ResourceKey<Registry<FurnaceTime>> KEY =
                ResourceKey.createRegistryKey(BlazingBamboo.id("furnace_time"));
        public static final Codec<FurnaceTime> CODEC = Codec.intRange(0, Integer.MAX_VALUE)
                .fieldOf("ticks").codec().xmap(FurnaceTime::new, FurnaceTime::ticks);
    }

    public record Stripping(Block result) {
        public static final ResourceKey<Registry<Stripping>> KEY =
                ResourceKey.createRegistryKey(BlazingBamboo.id("stripping"));
        public static final Codec<Stripping> CODEC = BuiltInRegistries.BLOCK.byNameCodec()
                .fieldOf("result").codec().xmap(Stripping::new, Stripping::result);
    }

    public static Optional<BlockState> strip(HolderLookup.Provider registries, BlockState state) {
        var key = ResourceKey.create(Stripping.KEY, BuiltInRegistries.BLOCK.getKey(state.getBlock()));
        return registries.lookupOrThrow(Stripping.KEY).get(key)
                .map(rule -> rule.value().result().withPropertiesOf(state));
    }

    private BambooRules() {}
}
