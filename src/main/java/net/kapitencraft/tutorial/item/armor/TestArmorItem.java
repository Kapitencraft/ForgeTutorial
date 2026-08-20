package net.kapitencraft.tutorial.item.armor;

import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.Nullable;

public class TestArmorItem extends AbstractArmorItem {

    public TestArmorItem(Type pType) {
        super(ModArmorMaterials.TEST_MATERIAL, pType, new Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return TutorialMod.res("textures/models/armor/custom/test" + layer.suffix + ".png");
    }
}
