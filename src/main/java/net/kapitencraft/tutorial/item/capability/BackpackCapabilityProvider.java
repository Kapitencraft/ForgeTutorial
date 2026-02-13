package net.kapitencraft.tutorial.item.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BackpackCapabilityProvider implements ICapabilitySerializable<ListTag> {
    private final BackpackCapability capability;
    private final LazyOptional<BackpackCapability> opt;

    public BackpackCapabilityProvider(ItemStack stack) {
        this.capability = new BackpackCapability(stack);
        this.opt = LazyOptional.of(() -> this.capability);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        return BackpackCapability.CAPABILITY.orEmpty(capability, opt);
    }

    @Override
    public ListTag serializeNBT() {
        return capability.serializeNBT();
    }

    @Override
    public void deserializeNBT(ListTag tags) {
        this.capability.deserializeNBT(tags);
    }
}
