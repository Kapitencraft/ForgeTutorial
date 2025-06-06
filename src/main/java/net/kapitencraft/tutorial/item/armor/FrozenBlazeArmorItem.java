package net.kapitencraft.tutorial.item.armor;

import net.kapitencraft.tutorial.TutorialMod;
import net.kapitencraft.tutorial.item.armor.client.model.FrozenBlazeArmorModel;
import net.kapitencraft.tutorial.item.armor.client.provider.ArmorModelProvider;
import net.kapitencraft.tutorial.item.armor.client.provider.SimpleModelProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.Nullable;

public class FrozenBlazeArmorItem extends AbstractArmorItem {
    private static final String TEXTURE_LOCATION = makeCustomTextureLocation(TutorialMod.MOD_ID, "frozen_blaze");

    public FrozenBlazeArmorItem(Type pType) {
        super(ArmorMaterials.DIAMOND, pType, new Properties().rarity(Rarity.RARE));
    }

    @Override
    protected boolean withCustomModel() {
        return true;
    }

    @Override
    protected ArmorModelProvider createModelProvider() {
        return new SimpleModelProvider(FrozenBlazeArmorModel::createBodyLayer, FrozenBlazeArmorModel::new);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return TEXTURE_LOCATION;
    }
}
