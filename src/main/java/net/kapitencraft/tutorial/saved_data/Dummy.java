package net.kapitencraft.tutorial.saved_data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;

public class Dummy {
    private static final String AMOUNT_TAG_ID = "Amount";

    private static void doStuff(Entity entity) {
        CompoundTag tag = entity.getPersistentData();
        int amount = tag.getInt(AMOUNT_TAG_ID);
        tag.putInt(AMOUNT_TAG_ID, amount);
    }
}
