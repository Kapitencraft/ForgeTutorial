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
import org.jetbrains.annotations.NotNull;

public class BackpackItem extends Item {
    public BackpackItem(Properties properties, int size) {
        super(properties.component(
                ModDataComponents.BACKPACK_CONTENT,
                new BackpackContent(NonNullList.createWithCapacity(size))
        ));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!level.isClientSide) {
            ItemStack useItem = player.getItemInHand(usedHand);
            player.openMenu(new ItemBoundContainer<>(27, useItem, ModDataComponents.BACKPACK_CONTENT, BackpackContent::new));
            return InteractionResultHolder.success(useItem);
        }
        return super.use(level, player, usedHand);
    }
}
