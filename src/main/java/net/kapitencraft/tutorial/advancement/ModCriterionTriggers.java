package net.kapitencraft.tutorial.advancement;

import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;

import java.util.function.Supplier;

public interface ModCriterionTriggers {
    ManaConsumedCriterionTrigger MANA_CONSUMED = register("mana_consumed", ManaConsumedCriterionTrigger::new);

    static <T extends CriterionTrigger<?>> T register(String name, Supplier<T> creator) {
        return CriteriaTriggers.register(TutorialMod.MOD_ID +  ":" + name, creator.get());
    }

    static void init() {
    }
}
