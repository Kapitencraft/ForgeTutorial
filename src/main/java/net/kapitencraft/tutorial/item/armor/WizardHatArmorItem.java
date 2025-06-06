package net.kapitencraft.tutorial.item.armor;


import net.kapitencraft.tutorial.TutorialMod;
import net.kapitencraft.tutorial.item.armor.client.model.WizardHatModel;
import net.kapitencraft.tutorial.item.armor.client.provider.ArmorModelProvider;
import net.kapitencraft.tutorial.item.armor.client.provider.SimpleModelProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.Nullable;

public class WizardHatArmorItem extends AbstractArmorItem  {
    private static final String TEXTURE_LOCATION = makeCustomTextureLocation(TutorialMod.MOD_ID, "wizard_hat");

    public WizardHatArmorItem() {
        super(ArmorMaterials.LEATHER, Type.HELMET, new Properties().rarity(Rarity.EPIC));
    }

    @Override
    protected boolean withCustomModel() {
        return true;
    }

    @Override
    protected ArmorModelProvider createModelProvider() {
        return new SimpleModelProvider(WizardHatModel::createBodyLayer, WizardHatModel::new);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return TEXTURE_LOCATION;
    }
}
