package net.kapitencraft.tutorial.saved_data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

public class PlayerCounter extends SavedData {
    private static PlayerCounter clientInstance;
    private static final String COUNTER_TAG_ID = "Counter";

    private int counter;

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putInt(COUNTER_TAG_ID, counter);
        return tag;
    }

    public PlayerCounter getCounter(Level level) {
        if (level.isClientSide()) {
            return clientInstance;
        } else {
            ServerLevel serverLevel = (ServerLevel) level;
            return serverLevel.getDataStorage().computeIfAbsent(
                    PlayerCounter::load,
                    PlayerCounter::new
            , "player_counter");
        }
    }

    private static PlayerCounter load(CompoundTag tag) {
        PlayerCounter counter = new PlayerCounter();
        counter.counter = tag.getInt(COUNTER_TAG_ID);
        return counter;
    }
}
