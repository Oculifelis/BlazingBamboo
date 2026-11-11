package net.paddedshaman.blazingbamboo.worldgen;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
/** Patch parameters retained from the original feature; its nested feature field was unused. */
public record BambooPatchConfiguration(int tries, int xzSpread, int ySpread) implements FeatureConfiguration {
 public static final Codec<BambooPatchConfiguration> CODEC=RecordCodecBuilder.create(i->i.group(Codec.intRange(1,Integer.MAX_VALUE).fieldOf("tries").orElse(128).forGetter(BambooPatchConfiguration::tries),Codec.intRange(0,Integer.MAX_VALUE).fieldOf("xz_spread").orElse(7).forGetter(BambooPatchConfiguration::xzSpread),Codec.intRange(0,Integer.MAX_VALUE).fieldOf("y_spread").orElse(3).forGetter(BambooPatchConfiguration::ySpread)).apply(i,BambooPatchConfiguration::new));
}
