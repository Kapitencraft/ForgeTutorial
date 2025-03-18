package net.kapitencraft.tutorial.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.kapitencraft.tutorial.advancement.ModCriterionTriggers;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

public class TestCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("test").then(Commands.argument("amount", IntegerArgumentType.integer()).executes(TestCommand::testAdvancementTrigger)));
    }

    private static int testAdvancementTrigger(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        int amount = IntegerArgumentType.getInteger(context, "amount");
        ModCriterionTriggers.MANA_CONSUMED.trigger(player, amount);
        return 1;
    }
}
