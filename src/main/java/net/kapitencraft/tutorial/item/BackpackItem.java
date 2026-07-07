package net.kapitencraft.tutorial.item;

import net.kapitencraft.tutorial.item.component.BackpackContent;
import net.kapitencraft.tutorial.item.component.ItemBoundContainer;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BackpackItem extends Item {
    public BackpackItem(Properties properties, int size) {
        super(properties
                .component(ModDataComponents.BACKPACK_CONTENT,
                        new BackpackContent(NonNullList.createWithCapacity(size))));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {
            ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
            pPlayer.openMenu(new ItemBoundContainer<>(27, itemInHand, ModDataComponents.BACKPACK_CONTENT, BackpackContent::new));
            return InteractionResultHolder.sidedSuccess(itemInHand, pLevel.isClientSide);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
