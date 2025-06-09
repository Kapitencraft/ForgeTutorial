package net.kapitencraft.tutorial.datagen.registry;

import net.kapitencraft.tutorial.ModTags;
import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;

import java.util.Map;

public interface Structures {
    ResourceKey<Structure> DEPOT = create("depot");

    private static ResourceKey<Structure> create(String id) {
        return ResourceKey.create(Registries.STRUCTURE, TutorialMod.res(id));
    }

    static void bootstrap(BootstapContext<Structure> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> templatePools = context.lookup(Registries.TEMPLATE_POOL);

        context.register(DEPOT, new JigsawStructure(
                new Structure.StructureSettings(
                        biomes.getOrThrow(ModTags.Biomes.HAS_DEPOT),
                        Map.of(
                                MobCategory.MISC, new StructureSpawnOverride(
                                        StructureSpawnOverride.BoundingBoxType.STRUCTURE,
                                        WeightedRandomList.create(new MobSpawnSettings.SpawnerData(EntityType.ALLAY, 1, 1, 2))
                                )
                        ),
                        GenerationStep.Decoration.SURFACE_STRUCTURES,
                        TerrainAdjustment.BURY
                ),
                templatePools.getOrThrow(TemplatePools.DEPOT),
                1,
                UniformHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.TOP),
                false,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES
        ));
    }
}
