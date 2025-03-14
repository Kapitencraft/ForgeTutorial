package net.kapitencraft.tutorial.advancement;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ManaConsumedTrigger extends SimpleCriterionTrigger<ManaConsumedTrigger.InstanceTrigger> {

    public void trigger(ServerPlayer player, int amount) {
        this.trigger(player, instanceTrigger -> instanceTrigger.matches(amount));
    }


    @Override
    protected @NotNull InstanceTrigger createInstance(JsonObject pJson, @NotNull Optional<ContextAwarePredicate> pPlayer, @NotNull DeserializationContext pDeserializationContext) {
        MinMaxBounds.Ints amount = MinMaxBounds.Ints.fromJson(pJson.get("amount"));
        return new InstanceTrigger(pPlayer, amount);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static class InstanceTrigger extends AbstractCriterionTriggerInstance {
        private final MinMaxBounds.Ints amount;

        public InstanceTrigger(Optional<ContextAwarePredicate> pPlayer, MinMaxBounds.Ints amount) {
            super(pPlayer);
            this.amount = amount;
        }

        @Override
        public @NotNull JsonObject serializeToJson() {
            JsonObject object = super.serializeToJson();
            object.add("amount", amount.serializeToJson());
            return object;
        }

        private boolean matches(int amount) {
            return this.amount.matches(amount);
        }
    }
}
