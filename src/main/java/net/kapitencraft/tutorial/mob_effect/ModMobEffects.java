package net.kapitencraft.tutorial.mob_effect;

import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface ModMobEffects {
    DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TutorialMod.MOD_ID);

    RegistryObject<MobEffect> FLYING = REGISTRY.register("flying", FlyingMobEffect::new);
}
