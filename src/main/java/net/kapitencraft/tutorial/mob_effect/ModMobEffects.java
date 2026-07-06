package net.kapitencraft.tutorial.mob_effect;

import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface ModMobEffects {
    DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, TutorialMod.MOD_ID);

    Holder<MobEffect> FLYING = REGISTRY.register("flying", FlyingMobEffect::new);
}
