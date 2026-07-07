package net.kapitencraft.tutorial.commands;


import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber
public class CommandsRegister {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        HealCommand.register(event.getDispatcher());
        TestCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void registerClientCommands(RegisterClientCommandsEvent event) {
        OpenScreenCommand.register(event.getDispatcher());
    }
}
