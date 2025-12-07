package net.kapitencraft.tutorial.item;

import net.kapitencraft.tutorial.item.capability.BackpackCapability;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BackpackItem extends Item {
    public BackpackItem() {
        super(new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
        itemInHand.getCapability(BackpackCapability.CAPABILITY).ifPresent(pPlayer::openMenu);
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
