package net.kapitencraft.tutorial.item.armor;


import net.kapitencraft.tutorial.TutorialMod;
import net.kapitencraft.tutorial.item.armor.client.model.WizardHatModel;
import net.kapitencraft.tutorial.item.armor.client.provider.ArmorModelProvider;
import net.kapitencraft.tutorial.item.armor.client.provider.SimpleModelProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.Nullable;

public class WizardHatArmorItem extends AbstractArmorItem  {
    private static final ResourceLocation TEXTURE_LOCATION = makeCustomTextureLocation(TutorialMod.MOD_ID, "wizard_hat");

    public WizardHatArmorItem() {
        super(ArmorMaterials.LEATHER, Type.HELMET, new Properties().rarity(Rarity.EPIC));
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return TEXTURE_LOCATION;
    }
}
