package net.kapitencraft.tutorial.mixin.client;

import net.kapitencraft.tutorial.client.ModRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin {
    @SuppressWarnings("unchecked")
    private LivingEntityRenderer<LivingEntity, ?> self() {
        return (LivingEntityRenderer<LivingEntity, ?>) (Object) this;
    }

    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    private void redirectRenderTypeIfPetrified(LivingEntity pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing, CallbackInfoReturnable<RenderType> cir) {
        if (pLivingEntity.getPersistentData().getBoolean("isPetrified"))
            cir.setReturnValue(ModRenderTypes.entityGreyscaleCutoutNoCull(self().getTextureLocation(pLivingEntity), pGlowing));
    }
}
