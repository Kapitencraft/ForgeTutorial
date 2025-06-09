package net.kapitencraft.tutorial.datagen.registry;

import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

public interface StructureSets {
    ResourceKey<StructureSet> DEPOT = create("depot");

    private static ResourceKey<StructureSet> create(String id) {
        return ResourceKey.create(Registries.STRUCTURE_SET, TutorialMod.res(id));
    }

    static void bootstrap(BootstapContext<StructureSet> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);
        context.register(DEPOT, new StructureSet(
                structures.getOrThrow(Structures.DEPOT),
                new RandomSpreadStructurePlacement(
                        20, 4, RandomSpreadType.TRIANGULAR, 1232435235
                )
        ));
    }
}
