package net.kapitencraft.tutorial.mob_effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class FlyingMobEffect extends MobEffect {
    protected FlyingMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -1);
    }
}
