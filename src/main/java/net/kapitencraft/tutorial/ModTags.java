package net.kapitencraft.tutorial;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModTags {

    public interface Biomes {
        private static TagKey<Biome> create(String id) {
            return TagKey.create(Registries.BIOME, TutorialMod.res(id));
        }

        TagKey<Biome> HAS_DEPOT = create("has_structure/depot");
    }
}
