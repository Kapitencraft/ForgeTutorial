package net.kapitencraft.tutorial.event;

import net.kapitencraft.tutorial.item.CustomItem;
import net.kapitencraft.tutorial.item.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientBusEvents {

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register(CustomItem::getColor, ModItems.CUSTOM_ITEM.get());
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        //event.register(TestBlock::getColor, ModBlocks.TEST.get());
    }
}
