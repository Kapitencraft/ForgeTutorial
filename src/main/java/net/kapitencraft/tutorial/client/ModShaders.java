package net.kapitencraft.tutorial.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModShaders {
    private static ShaderInstance RENDERTYPE_ENTITY_GREYSCALE_CUTOUT_NO_CULL;

    @SubscribeEvent
    public static void onRegisterShaders(RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceProvider(), TutorialMod.res("rendertype_entity_greyscale_cutout_no_cull"), DefaultVertexFormat.NEW_ENTITY), s -> RENDERTYPE_ENTITY_GREYSCALE_CUTOUT_NO_CULL = s);
    }

    public static ShaderInstance getRendertypeEntityGreyscaleCutoutNoCull() {
        return RENDERTYPE_ENTITY_GREYSCALE_CUTOUT_NO_CULL;
    }
}
