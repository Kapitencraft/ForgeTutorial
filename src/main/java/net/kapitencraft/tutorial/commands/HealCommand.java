package net.kapitencraft.tutorial.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kapitencraft.tutorial.advancement.ModCriterionTriggers;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.Collection;
import java.util.Objects;

public class HealCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("heal").requires(commandSourceStack -> commandSourceStack.hasPermission(4))
                        .then(Commands.argument("amount", IntegerArgumentType.integer(1, 100)).executes(HealCommand::healSelfWithAmount)
                                .then(Commands.argument("types", EntityArgument.entities()).executes(HealCommand::healOthers)))
        );
    }

    private static int healOthers(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        int amount = IntegerArgumentType.getInteger(context, "amount");
        Collection<? extends Entity> targets = EntityArgument.getEntities(context, "types");
        targets.forEach(entity -> {
            if (entity instanceof LivingEntity living) living.heal(amount);
        });
        return targets.size();
    }

    private static int healSelfWithAmount(CommandContext<CommandSourceStack> context) {
        int amount = IntegerArgumentType.getInteger(context, "amount");

        ServerPlayer player = Objects.requireNonNull(context.getSource().getPlayer(), "command may not be executed from the console!");
        player.heal(amount);
        ModCriterionTriggers.MANA_CONSUMED.trigger(player, 100);
        return 1;
    }
}
