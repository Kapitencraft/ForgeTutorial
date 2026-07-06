package net.kapitencraft.tutorial.saved_data;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

public class PlayerCounter extends SavedData {
    private static PlayerCounter clientInstance;
    private static final String COUNTER_TAG_ID = "Counter";

    private int counter;

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt(COUNTER_TAG_ID, counter);
        return tag;
    }

    public PlayerCounter getCounter(Level level) {
        if (level.isClientSide()) {
            return clientInstance;
        } else {
            ServerLevel serverLevel = (ServerLevel) level;
            return serverLevel.getDataStorage().computeIfAbsent(
                    new Factory<>(PlayerCounter::new, PlayerCounter::load),
                    "player_counter"
            );
        }
    }

    private static PlayerCounter load(CompoundTag tag, HolderLookup.Provider provider) {
        PlayerCounter counter = new PlayerCounter();
        counter.counter = tag.getInt(COUNTER_TAG_ID);
        return counter;
    }
}
