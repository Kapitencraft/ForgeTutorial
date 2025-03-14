package net.kapitencraft.tutorial;

import com.mojang.logging.LogUtils;
import net.kapitencraft.tutorial.attribute.ModAttributes;
import net.kapitencraft.tutorial.block.ModBlocks;
import net.kapitencraft.tutorial.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TutorialMod.MOD_ID)
public class TutorialMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "examplemod";

    public static Screen postCommandScreen = null;

    public static ResourceLocation res(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public TutorialMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModAttributes.REGISTRY.register(modEventBus);
        ModItems.REGISTRY.register(modEventBus);
        ModBlocks.REGISTRY.register(modEventBus);

        //StartupMessageManager.addModMessage("Counting!");
        //ProgressMeter meter = StartupMessageManager.addProgressBar("Counting...", Integer.MAX_VALUE);
        //boolean labelChanged = false;
        //while (meter.progress() != 1f) {
        //    meter.increment();
        //    if (!labelChanged && meter.progress() > .75f) {
        //        meter.label("Counting... almost done!");
        //        labelChanged = true;
        //    }
        //};
        //meter.complete();

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        modEventBus.addListener(CommonListener::commonSetupListener);
    }

    public static class CommonListener {
        @SubscribeEvent()
        public static void commonSetupListener(FMLCommonSetupEvent event) {

        }
    }

    public static class ItemHolder {
        @SubscribeEvent
        public void onItemExpired(ItemExpireEvent event) {

        }
    }
}
