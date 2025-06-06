package net.kapitencraft.tutorial.advancement;

import com.google.gson.JsonObject;
import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class ManaConsumedCriterionTrigger extends SimpleCriterionTrigger<ManaConsumedCriterionTrigger.TriggerInstance> {
    //IGNORE CHANGES IN THIS FILE; DEGRADING FROM 1.20.2 to 1.20.1 created some issues
    private static final ResourceLocation LOCATION = TutorialMod.res("mana_consumed");

    @Override
    protected TriggerInstance createInstance(JsonObject pJson, @NotNull ContextAwarePredicate pPredicate, DeserializationContext pDeserializationContext) {
        MinMaxBounds.Ints bounds = MinMaxBounds.Ints.fromJson(pJson.get("bounds"));
        return new TriggerInstance(pPredicate, bounds);
    }


    public void trigger(ServerPlayer player, int amount) {
        trigger(player, triggerInstance -> triggerInstance.matches(amount));
    }

    public static TriggerInstance any() {
        return create(MinMaxBounds.Ints.ANY);
    }

    public static TriggerInstance atLeast(int min) {
        return create(MinMaxBounds.Ints.atLeast(min));
    }

    private static TriggerInstance create(MinMaxBounds.Ints bounds) {
        return new TriggerInstance(null, bounds);
    }

    @Override
    public ResourceLocation getId() {
        return LOCATION;
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final MinMaxBounds.Ints bounds;

        public TriggerInstance(ContextAwarePredicate pPlayer, MinMaxBounds.Ints bounds) {
            super(LOCATION, pPlayer);
            this.bounds = bounds;
        }

        @Override
        public @NotNull JsonObject serializeToJson(SerializationContext context) {
            JsonObject object = super.serializeToJson(context);
            object.add("bounds", bounds.serializeToJson());
            return object;
        }

        public boolean matches(int amount) {
            return bounds.matches(amount);
        }
    }
}
