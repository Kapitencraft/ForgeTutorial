package net.kapitencraft.tutorial.item.armor;

import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Map;

public interface ModArmorMaterials {
    DeferredRegister<ArmorMaterial> REGISTRY = DeferredRegister.create(Registries.ARMOR_MATERIAL, TutorialMod.MOD_ID);

    Holder<ArmorMaterial> TEST_MATERIAL = REGISTRY.register("test", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.HELMET, 1, ArmorItem.Type.CHESTPLATE, 2, ArmorItem.Type.LEGGINGS, 3, ArmorItem.Type.BOOTS, 4),
            100,
            SoundEvents.ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.of(Items.NETHER_STAR),
            List.of(
                    new ArmorMaterial.Layer(ResourceLocation.withDefaultNamespace("test"), "_overlay", false),
                    new ArmorMaterial.Layer(ResourceLocation.withDefaultNamespace("test"), "", true)
            ),
            10,
            5
    ));
}
