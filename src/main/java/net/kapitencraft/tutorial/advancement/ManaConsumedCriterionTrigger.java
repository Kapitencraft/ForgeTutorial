package net.kapitencraft.tutorial.advancement;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ManaConsumedCriterionTrigger extends SimpleCriterionTrigger<ManaConsumedCriterionTrigger.TriggerInstance> {

    @Override
    protected @NotNull TriggerInstance createInstance(JsonObject pJson, Optional<ContextAwarePredicate> pPlayer, DeserializationContext pDeserializationContext) {
        MinMaxBounds.Ints bounds = MinMaxBounds.Ints.fromJson(pJson.get("bounds"));
        return new TriggerInstance(pPlayer, bounds);
    }

    public void trigger(ServerPlayer player, int amount) {
        trigger(player, triggerInstance -> triggerInstance.matches(amount));
    }

    public static Criterion<TriggerInstance> any() {
        return create(MinMaxBounds.Ints.ANY);
    }

    public static Criterion<TriggerInstance> atLeast(int min) {
        return create(MinMaxBounds.Ints.atLeast(min));
    }

    private static Criterion<TriggerInstance> create(MinMaxBounds.Ints bounds) {
        return ModCriterionTriggers.MANA_CONSUMED.createCriterion(new TriggerInstance(Optional.empty(), bounds));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final MinMaxBounds.Ints bounds;

        @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
        public TriggerInstance(Optional<ContextAwarePredicate> pPlayer, MinMaxBounds.Ints bounds) {
            super(pPlayer);
            this.bounds = bounds;
        }

        @Override
        public @NotNull JsonObject serializeToJson() {
            JsonObject object = super.serializeToJson();
            object.add("bounds", bounds.serializeToJson());
            return object;
        }

        public boolean matches(int amount) {
            return bounds.matches(amount);
        }
    }
}
