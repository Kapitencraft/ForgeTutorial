package net.kapitencraft.tutorial.commands;

import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommandsRegister {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        HealCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void registerClientCommands(RegisterClientCommandsEvent event) {
        OpenScreenCommand.register(event.getDispatcher());
    }
}
