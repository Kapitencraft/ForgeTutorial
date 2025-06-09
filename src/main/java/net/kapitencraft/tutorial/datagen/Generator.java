package net.kapitencraft.tutorial.datagen;

import net.kapitencraft.tutorial.datagen.registry.ModRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
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
    }
}
