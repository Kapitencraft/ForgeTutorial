package net.kapitencraft.tutorial.item;

import net.kapitencraft.tutorial.TutorialMod;
import net.kapitencraft.tutorial.item.armor.AbstractArmorItem;
import net.kapitencraft.tutorial.item.armor.FrozenBlazeArmorItem;
import net.kapitencraft.tutorial.item.armor.WizardHatArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public interface ModItems {
    DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    RegistryObject<CustomItem> CUSTOM_ITEM = REGISTRY.register("custom", CustomItem::new);
    Map<ArmorItem.Type, RegistryObject<FrozenBlazeArmorItem>> FROZEN_BLAZE_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "frozen_blaze", FrozenBlazeArmorItem::new);
    RegistryObject<WizardHatArmorItem> WIZARD_HAT = REGISTRY.register("wizard_hat", WizardHatArmorItem::new);
    RegistryObject<PaladinShieldItem> PALADIN_SHIELD = REGISTRY.register("paladin_shield", PaladinShieldItem::new);
}