package net.kapitencraft.tutorial.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.kapitencraft.tutorial.TutorialMod;
import net.kapitencraft.tutorial.gui.TestScreen;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class OpenScreenCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("open").executes(OpenScreenCommand::execute));
    }

    private static int execute(CommandContext<CommandSourceStack> context) {
        TutorialMod.postCommandScreen = new TestScreen();
        return 1;
    }
}
