package net.kapitencraft.tutorial.item;

import net.kapitencraft.tutorial.TutorialMod;
import net.kapitencraft.tutorial.item.armor.AbstractArmorItem;
import net.kapitencraft.tutorial.item.armor.FrozenBlazeArmorItem;
import net.kapitencraft.tutorial.item.armor.TestArmorItem;
import net.kapitencraft.tutorial.item.armor.WizardHatArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;

public interface ModItems {
    DeferredRegister.Items REGISTRY = DeferredRegister.createItems(TutorialMod.MOD_ID);

    DeferredItem<CustomItem> CUSTOM_ITEM = REGISTRY.register("custom", CustomItem::new);
    Map<ArmorItem.Type, DeferredItem<FrozenBlazeArmorItem>> FROZEN_BLAZE_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "frozen_blaze", FrozenBlazeArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<TestArmorItem>> TEST_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "test", TestArmorItem::new);
    DeferredItem<WizardHatArmorItem> WIZARD_HAT = REGISTRY.register("wizard_hat", WizardHatArmorItem::new);
    DeferredItem<PaladinShieldItem> PALADIN_SHIELD = REGISTRY.register("paladin_shield", PaladinShieldItem::new);
    DeferredItem<BackpackItem> BACKPACK = REGISTRY.registerItem("backpack", p -> new BackpackItem(p, 27));
}