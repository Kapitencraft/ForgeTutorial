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
    public static final ModBlockEntityWithoutLevelRenderer INSTANCE = new ModBlockEntityWithoutLevelRenderer(
            Minecraft.getInstance().getBlockEntityRenderDispatcher(),
            Minecraft.getInstance().getEntityModels()
    );

    private final BlockEntityRenderDispatcher dispatcher;
    private final EntityModelSet modelSet;

    private PaladinShieldModel paladinShieldModel;

    public ModBlockEntityWithoutLevelRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
        this.dispatcher = pBlockEntityRenderDispatcher;
        this.modelSet = pEntityModelSet;
    }

    @Override
    public void onResourceManagerReload(ResourceManager pResourceManager) {
        paladinShieldModel = new PaladinShieldModel(this.modelSet.bakeLayer(PaladinShieldModel.LAYER_LOCATION));
    }

    @Override
    public void renderByItem(ItemStack pStack, ItemDisplayContext pDisplayContext, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        if (pStack.is(ModItems.PALADIN_SHIELD.get())) {
            pPoseStack.pushPose();
            pPoseStack.scale(1, -1, -1);
            VertexConsumer consumer = ItemRenderer.getFoilBufferDirect(pBuffer, this.paladinShieldModel.renderType(PALADIN_MODEL_TEXTURE), true, pStack.hasFoil());
            this.paladinShieldModel.renderToBuffer(pPoseStack, consumer, pPackedLight, pPackedOverlay, 1, 1, 1, 1);
            pPoseStack.popPose();
        }
    }

    private static final ResourceLocation PALADIN_MODEL_TEXTURE = TutorialMod.res("textures/models/shield/paladin.png");
}
