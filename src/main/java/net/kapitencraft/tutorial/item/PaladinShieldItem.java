package net.kapitencraft.tutorial.item;

import net.kapitencraft.tutorial.client.ModBlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class PaladinShieldItem extends ShieldItem {
    public PaladinShieldItem() {
        super(new Properties().rarity(Rarity.UNCOMMON).durability(598));
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return ModBlockEntityWithoutLevelRenderer.INSTANCE;
            }
        });
        super.initializeClient(consumer);
    }
}
