package net.kapitencraft.tutorial.event;

import net.kapitencraft.tutorial.advancement.ModCriterionTriggers;
import net.kapitencraft.tutorial.attribute.ModAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

    @SubscribeEvent
    public static void addAttributesToEntities(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, ModAttributes.HEAL_ATTRIBUTE.get());
        event.add(EntityType.PLAYER, ModAttributes.MANA.get());
    }

    @SubscribeEvent
    public void onFMLCommonSetup(FMLCommonSetupEvent event) {
        ModCriterionTriggers.init();
    }
}
