package net.kapitencraft.tutorial.datagen.registry;

import com.mojang.datafixers.util.Pair;
import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.List;

public interface TemplatePools {
    ResourceKey<StructureTemplatePool> DEPOT = create("depot");

    private static ResourceKey<StructureTemplatePool> create(String id) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, TutorialMod.res(id));
    }

    static void bootstrap(BootstapContext<StructureTemplatePool> context) {
        HolderGetter<StructureTemplatePool> templatePools = context.lookup(Registries.TEMPLATE_POOL);
        context.register(DEPOT, new StructureTemplatePool(
                templatePools.getOrThrow(Pools.EMPTY),
                List.of(
                        Pair.of(StructurePoolElement.single("tutorial_mod:depot/diamond_depot"), 1),
                        Pair.of(StructurePoolElement.single("tutorial_mod:depot/gold_depot"), 2),
                        Pair.of(StructurePoolElement.single("tutorial_mod:depot/lapis_depot"), 3),
                        Pair.of(StructurePoolElement.single("tutorial_mod:depot/redstone_depot"), 4)
                ),
                StructureTemplatePool.Projection.TERRAIN_MATCHING
        ));
    }
}
