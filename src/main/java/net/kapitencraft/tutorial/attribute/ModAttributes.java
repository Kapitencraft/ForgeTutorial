package net.kapitencraft.tutorial.attribute;

import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface ModAttributes {
    DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(Registries.ATTRIBUTE, TutorialMod.MOD_ID);
    Holder<Attribute> HEAL_ATTRIBUTE = REGISTRY.register("heal", ()-> new RangedAttribute("generic.heal", 0, 0, 200));
    Holder<Attribute> MANA = REGISTRY.register("mana", () -> new RangedAttribute("generic.mana", 100, 0, Double.MAX_VALUE));


    static double getAttributeValue(LivingEntity living, Holder<Attribute> attribute) {
        AttributeInstance instance = living.getAttribute(attribute);
        if (instance == null) return -1;
        else return instance.getValue();
    }
}
