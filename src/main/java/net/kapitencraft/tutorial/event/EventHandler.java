package net.kapitencraft.tutorial.event;

import net.kapitencraft.tutorial.attribute.ModAttributes;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public static void increaseHealingFromAttribute(LivingHealEvent event) {
        double attrVal = ModAttributes.getAttributeValue(event.getEntity(), ModAttributes.HEAL_ATTRIBUTE.get());
        if (attrVal != -1) event.setAmount((float) (event.getAmount() * (1 + attrVal / 100)));
    }
}
