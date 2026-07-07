package net.kapitencraft.tutorial.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;

public class PaladinShieldItem extends ShieldItem {
    public PaladinShieldItem() {
        super(new Properties().rarity(Rarity.UNCOMMON));
    }
}
