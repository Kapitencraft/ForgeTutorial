package net.kapitencraft.tutorial.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.kapitencraft.tutorial.TutorialMod;
import net.kapitencraft.tutorial.client.model.PaladinShieldModel;
import net.kapitencraft.tutorial.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class ModBlockEntityWithoutLevelRenderer extends BlockEntityWithoutLevelRenderer {
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    private final EntityModelSet entityModelSet;

    public static final ModBlockEntityWithoutLevelRenderer INSTANCE = new ModBlockEntityWithoutLevelRenderer(
            Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels()
    );

    private PaladinShieldModel paladinShieldModel;

    public ModBlockEntityWithoutLevelRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
        this.blockEntityRenderDispatcher = pBlockEntityRenderDispatcher;
        this.entityModelSet = pEntityModelSet;
    }

    @Override
    public void onResourceManagerReload(ResourceManager pResourceManager) {
        this.paladinShieldModel = new PaladinShieldModel(this.entityModelSet.bakeLayer(PaladinShieldModel.LAYER_LOCATION));
    }

    @Override
    public void renderByItem(ItemStack pStack, ItemDisplayContext pDisplayContext, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        if (pStack.is(ModItems.PALADIN_SHIELD.get())) {
            pPoseStack.pushPose();
            pPoseStack.scale(1, -1, -1);
            VertexConsumer bufferDirect = ItemRenderer.getFoilBufferDirect(pBuffer, this.paladinShieldModel.renderType(PALADIN_SHIELD_LOCATION), true, pStack.hasFoil());
            paladinShieldModel.renderToBuffer(pPoseStack, bufferDirect, pPackedLight, pPackedOverlay, 1, 1, 1, 1);
            pPoseStack.popPose();
        }
    }

    private static final ResourceLocation PALADIN_SHIELD_LOCATION = TutorialMod.res("textures/models/shield/paladin.png");
}
