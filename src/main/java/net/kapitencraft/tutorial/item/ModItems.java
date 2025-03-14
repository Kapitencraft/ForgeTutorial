package net.kapitencraft.tutorial.item;

import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface ModItems {
    DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    RegistryObject<CustomItem> CUSTOM_ITEM = REGISTRY.register("custom", CustomItem::new);
}