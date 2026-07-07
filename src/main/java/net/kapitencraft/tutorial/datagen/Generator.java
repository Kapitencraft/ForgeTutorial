package net.kapitencraft.tutorial.datagen;

import net.kapitencraft.tutorial.datagen.registry.ModRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber
public class Generator {

    @SubscribeEvent
    public static void registerDataGenerators(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();
        generator.addProvider(true, new ModBlockStateProvider(output, helper));
        generator.addProvider(true, new ModAdvancementProvider(output, registries, helper));
        generator.addProvider(true, new ModRegistryProvider(output, registries));
        generator.addProvider(true, new ModBiomeTagsProvider(output, registries, helper));
        generator.addProvider(true, new ModItemModelsProvider(output, helper));
    }
}
