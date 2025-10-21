package net.kapitencraft.tutorial.event;

import net.kapitencraft.tutorial.client.ModBlockEntityWithoutLevelRenderer;
import net.kapitencraft.tutorial.client.model.PaladinShieldModel;
import net.kapitencraft.tutorial.item.CustomItem;
import net.kapitencraft.tutorial.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientBusEvents {

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register(CustomItem::getColor, ModItems.CUSTOM_ITEM.get());
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        //event.register(TestBlock::getColor, ModBlocks.TEST.get());
    }

    @SubscribeEvent
    public static void onEntityRenderersRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PaladinShieldModel.LAYER_LOCATION, PaladinShieldModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(ModBlockEntityWithoutLevelRenderer.INSTANCE);
    }

    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        ItemProperties.register(ModItems.PALADIN_SHIELD.get(), new ResourceLocation("blocking"), (p_174575_, p_174576_, p_174577_, p_174578_) ->
                p_174577_ != null && p_174577_.isUsingItem() && p_174577_.getUseItem() == p_174575_ ? 1.0F : 0.0F
        );
    }
}
