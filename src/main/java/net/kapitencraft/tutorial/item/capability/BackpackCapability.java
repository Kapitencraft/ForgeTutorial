package net.kapitencraft.tutorial.item.capability;

import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import org.jetbrains.annotations.Nullable;

@AutoRegisterCapability
public class BackpackCapability implements MenuProvider {
    public static final Capability<BackpackCapability> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    private final ItemStack stack;

    private final SimpleContainer container;

    public BackpackCapability(ItemStack stack) {
        this.stack = stack;
        this.container = new SimpleContainer(27);
    }

    @Override
    public Component getDisplayName() {
        return stack.getHoverName();
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return ChestMenu.threeRows(i, inventory, container);
    }

    public ListTag serializeNBT() {
        return container.createTag();
    }

    public void deserializeNBT(ListTag tags) {
        container.fromTag(tags);
    }
}
