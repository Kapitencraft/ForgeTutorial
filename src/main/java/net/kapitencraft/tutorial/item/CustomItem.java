package net.kapitencraft.tutorial.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.kapitencraft.tutorial.attribute.ModAttributes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class CustomItem extends Item {
    public CustomItem() {
        super(new Properties().rarity(Rarity.RARE).durability(100000));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        HashMultimap<Attribute, AttributeModifier> multimap = HashMultimap.create(super.getAttributeModifiers(slot, stack));
        if (slot != EquipmentSlot.MAINHAND) return multimap;
        multimap.put(ModAttributes.HEAL_ATTRIBUTE.get(), new AttributeModifier("Heal Modifier", 100, AttributeModifier.Operation.ADDITION));
        return multimap;
    }

    public static int getColor(ItemStack stack, int index) {
        if (index != 0) return -1;
        LocalPlayer player = Minecraft.getInstance().player;
        int y = (int) player.getY() + 64;
        int color = y * 255 / 384;
        return (color << 8 | color) << 8 | color;
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return stack.getDamageValue() < stack.getMaxDamage() - 1;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack stack = itemStack.copy();
        stack.setDamageValue(stack.getDamageValue() + 1);
        return stack;
    }
}
