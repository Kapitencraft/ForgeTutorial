package net.kapitencraft.tutorial;

import com.mojang.logging.LogUtils;
import net.kapitencraft.tutorial.advancement.ModCriterionTriggers;
import net.kapitencraft.tutorial.attribute.ModAttributes;
import net.kapitencraft.tutorial.block.ModBlocks;
import net.kapitencraft.tutorial.item.ModDataComponents;
import net.kapitencraft.tutorial.item.ModItems;
import net.kapitencraft.tutorial.item.armor.ModArmorMaterials;
import net.kapitencraft.tutorial.mob_effect.ModMobEffects;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.item.ItemExpireEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TutorialMod.MOD_ID)
public class TutorialMod {
    public static final String MOD_ID = "tutorial_mod";

    public static Screen postCommandScreen = null;

    public static ResourceLocation res(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static final Logger LOGGER = LogUtils.getLogger();

    public TutorialMod(IEventBus modEventBus, ModContainer container) {

        ModAttributes.REGISTRY.register(modEventBus);
        ModItems.REGISTRY.register(modEventBus);
        ModBlocks.REGISTRY.register(modEventBus);
        ModMobEffects.REGISTRY.register(modEventBus);
        ModCriterionTriggers.REGISTRY.register(modEventBus);
        ModDataComponents.REGISTRY.register(modEventBus);
        ModArmorMaterials.REGISTRY.register(modEventBus);

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
