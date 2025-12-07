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

    private final SimpleContainer inventory;
    private final ItemStack owner;

    public BackpackCapability(ItemStack owner) {
        this.owner = owner;
        this.inventory = new SimpleContainer(27);
    }

    public ListTag serialize() {
        return inventory.createTag();
    }

    public void deserialize(ListTag nbt) {
        inventory.fromTag(nbt);
    }

    @Override
    public Component getDisplayName() {
        return owner.getHoverName();
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return ChestMenu.threeRows(pContainerId, pPlayerInventory, this.inventory);
    }
}
