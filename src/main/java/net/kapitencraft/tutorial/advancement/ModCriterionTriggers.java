package net.kapitencraft.tutorial.advancement;

import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface ModCriterionTriggers {
    DeferredRegister<CriterionTrigger<?>> REGISTRY = DeferredRegister.create(Registries.TRIGGER_TYPE, TutorialMod.MOD_ID);

    Supplier<ManaConsumedCriterionTrigger> MANA_CONSUMED = register("mana_consumed", ManaConsumedCriterionTrigger::new);

    static <T extends CriterionTrigger<?>> Supplier<T> register(String name, Supplier<T> creator) {
        return REGISTRY.register(name, creator);
    }
}