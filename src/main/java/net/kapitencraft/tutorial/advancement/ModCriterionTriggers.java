package net.kapitencraft.tutorial.advancement;

import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;

import java.util.function.Supplier;

public interface ModCriterionTriggers {
    ManaConsumedTrigger MANA_CONSUMED = register("mana_consumed", ManaConsumedTrigger::new);

    private static <T extends CriterionTrigger<?>> T register(String name, Supplier<T> sup) {
        return CriteriaTriggers.register(TutorialMod.MOD_ID + ":" + name, sup.get());
    }

    static void init() {
    }
}
