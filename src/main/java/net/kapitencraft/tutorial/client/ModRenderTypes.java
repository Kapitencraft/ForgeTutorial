package net.kapitencraft.tutorial.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiFunction;

public class ModRenderTypes extends RenderType {
    protected static final RenderStateShard.ShaderStateShard RENDERTYPE_ENTITY_GREYSCALE_CUTOUT_NO_CULL_SHADER = new RenderStateShard.ShaderStateShard(ModShaders::getRendertypeEntityGreyscaleCutoutNoCull);

    public ModRenderTypes(String pName, VertexFormat pFormat, VertexFormat.Mode pMode, int pBufferSize, boolean pAffectsCrumbling, boolean pSortOnUpload, Runnable pSetupState, Runnable pClearState) {
        super(pName, pFormat, pMode, pBufferSize, pAffectsCrumbling, pSortOnUpload, pSetupState, pClearState);
    }

    private static final BiFunction<ResourceLocation, Boolean, RenderType> ENTITY_CUTOUT_NO_CULL = Util.memoize((textureLocation, isOutline) -> {
        RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                .setShaderState(RENDERTYPE_ENTITY_GREYSCALE_CUTOUT_NO_CULL_SHADER)
                .setTextureState(new RenderStateShard.TextureStateShard(textureLocation, false, false))
                .setTransparencyState(NO_TRANSPARENCY)
                .setCullState(NO_CULL)
                .setLightmapState(LIGHTMAP)
                .setOverlayState(OVERLAY)
                .createCompositeState(isOutline);
        return create(TutorialMod.MOD_ID + ":entity_greyscale_cutout_no_cull", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, false, rendertype$compositestate);
    });

    public static RenderType entityGreyscaleCutoutNoCull(ResourceLocation texture, boolean isOutline) {
        return ENTITY_CUTOUT_NO_CULL.apply(texture, isOutline);
    }
}
