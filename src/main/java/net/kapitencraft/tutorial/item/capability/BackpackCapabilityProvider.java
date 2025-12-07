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
    private final LazyOptional<BackpackCapability> optional;

    public BackpackCapabilityProvider(ItemStack owner) {
        this.capability = new BackpackCapability(owner);
        this.optional = LazyOptional.of(() -> capability);
    }

    @Override
    public ListTag serializeNBT() {
        return capability.serialize();
    }

    @Override
    public void deserializeNBT(ListTag nbt) {
        this.capability.deserialize(nbt);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return BackpackCapability.CAPABILITY.orEmpty(cap, optional);
    }
}
