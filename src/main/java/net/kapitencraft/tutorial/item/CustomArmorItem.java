package net.kapitencraft.tutorial.item;

import net.kapitencraft.tutorial.TutorialMod;
import net.kapitencraft.tutorial.item.client.ArmorRenderer;
import net.kapitencraft.tutorial.item.client.custom.ArmorTemplate;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class CustomArmorItem extends Item {


    public CustomArmorItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                HumanoidModel<?> armorModel =new HumanoidModel<>(new ArmorRenderer<>(ArmorTemplate::createBodyLayer, ArmorTemplate::new).makeArmorParts(equipmentSlot));
                armorModel.crouching = original.crouching;
                armorModel.riding = original.riding;
                armorModel.young = original.young;
                return armorModel;
            }
        });
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return new ResourceLocation(TutorialMod.MOD_ID, "textures/models/armor/example.png").toString();
    }
}
