package net.kapitencraft.tutorial.item.armor.client;

import net.kapitencraft.tutorial.item.armor.client.provider.ArmorModelProvider;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

import java.util.Objects;

public class DyedArmorClientExtension extends ArmorClientExtension {
    public DyedArmorClientExtension(ArmorModelProvider provider) {
        super(provider);
    }

    @Override
    public int getArmorLayerTintColor(ItemStack stack, LivingEntity entity, ArmorMaterial.Layer layer, int layerIdx, int fallbackColor) {
        return Objects.equals(layer.suffix, "_overlay") ? -1 : FastColor.ARGB32.opaque(DyedItemColor.getOrDefault(stack, DyedItemColor.LEATHER_COLOR));
    }
}
