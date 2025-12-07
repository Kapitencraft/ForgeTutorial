package net.kapitencraft.tutorial.event;

import net.kapitencraft.tutorial.TutorialMod;
import net.kapitencraft.tutorial.client.ModKeyMappings;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ClientEvents {

    private static boolean postShaderActive = false;

    private static final ResourceLocation EFFECT_LOCATION = TutorialMod.res("shaders/post/greyscale.json");

    @SubscribeEvent
    public static void onTickClientTick(TickEvent.ClientTickEvent event) {
        while (ModKeyMappings.TOGGLE_POST_SHADER.consumeClick()) {
            postShaderActive = !postShaderActive;
            if (postShaderActive) {
                Minecraft.getInstance().gameRenderer.loadEffect(EFFECT_LOCATION);
            } else
                Minecraft.getInstance().gameRenderer.shutdownEffect();
        }
    }

}
