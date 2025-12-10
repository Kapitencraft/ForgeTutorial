package net.kapitencraft.tutorial.event;

import net.kapitencraft.tutorial.client.ModKeyMappings;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ClientEvents {

    @SubscribeEvent
    public static void onTickClientTick(TickEvent.ClientTickEvent event) {
        while (ModKeyMappings.TOGGLE_POST_SHADER.consumeClick()) {
            CompoundTag data = Minecraft.getInstance().player.getPersistentData();
            data.putBoolean("isPetrified", !data.getBoolean("isPetrified"));
        }
    }
}
