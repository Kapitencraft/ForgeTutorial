package net.kapitencraft.tutorial.advancement;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;

import java.util.function.Supplier;

public interface ModCriterionTriggers {
    ManaConsumedCriterionTrigger MANA_CONSUMED = register(ManaConsumedCriterionTrigger::new);

    static <T extends CriterionTrigger<?>> T register(Supplier<T> creator) {
        return CriteriaTriggers.register(creator.get());
    }

    static void init() {
    }
}
