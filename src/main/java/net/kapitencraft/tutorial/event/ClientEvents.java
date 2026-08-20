package net.kapitencraft.tutorial.event;

import net.kapitencraft.tutorial.client.ModBlockEntityWithoutLevelRenderer;
import net.kapitencraft.tutorial.client.ModKeyMappings;
import net.kapitencraft.tutorial.client.model.PaladinShieldModel;
import net.kapitencraft.tutorial.item.CustomItem;
import net.kapitencraft.tutorial.item.ModItems;
import net.kapitencraft.tutorial.item.armor.AbstractArmorItem;
import net.kapitencraft.tutorial.item.armor.client.ArmorClientExtension;
import net.kapitencraft.tutorial.item.armor.client.DyedArmorClientExtension;
import net.kapitencraft.tutorial.item.armor.client.model.FrozenBlazeArmorModel;
import net.kapitencraft.tutorial.item.armor.client.model.VanillaModel;
import net.kapitencraft.tutorial.item.armor.client.model.WizardHatModel;
import net.kapitencraft.tutorial.item.armor.client.provider.ArmorModelProvider;
import net.kapitencraft.tutorial.item.armor.client.provider.SimpleModelProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.ItemLike;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;

@EventBusSubscriber(Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onTickClientTick(ClientTickEvent.Post event) {
        while (ModKeyMappings.TOGGLE_POST_SHADER.consumeClick()) {
            CompoundTag data = Minecraft.getInstance().player.getPersistentData();
            data.putBoolean("isPetrified", !data.getBoolean("isPetrified"));
        }
    }

    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        registerArmorExtension(ModItems.FROZEN_BLAZE_ARMOR, event, new SimpleModelProvider(FrozenBlazeArmorModel::createBodyLayer, FrozenBlazeArmorModel::new));
        event.registerItem(new DyedArmorClientExtension(new SimpleModelProvider(VanillaModel::createBodyLayer, VanillaModel::new)), ModItems.TEST_ARMOR.values().toArray(DeferredItem[]::new));
        event.registerItem(new ArmorClientExtension(new SimpleModelProvider(WizardHatModel::createBodyLayer, WizardHatModel::new)), ModItems.WIZARD_HAT);
        event.registerItem(new IClientItemExtensions() {
                               @Override
                               public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                                   return ModBlockEntityWithoutLevelRenderer.INSTANCE;
                               }
                           },
                ModItems.PALADIN_SHIELD.get()
        );
    }

    @SuppressWarnings("unchecked")
    private static <T extends AbstractArmorItem> void registerArmorExtension(Map<ArmorItem.Type, DeferredItem<T>> map, RegisterClientExtensionsEvent event, ArmorModelProvider provider) {
        event.registerItem(new ArmorClientExtension(provider), map.values().toArray(DeferredItem[]::new));
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register(CustomItem::getColor, ModItems.CUSTOM_ITEM.get());
        event.register((p_329705_, p_329706_) -> p_329706_ > 0 ? -1 : DyedItemColor.getOrDefault(p_329705_, -6265536), ModItems.TEST_ARMOR.values().toArray(ItemLike[]::new));
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
        ItemProperties.register(ModItems.PALADIN_SHIELD.get(), ResourceLocation.withDefaultNamespace("blocking"), (itemStack, level, entity, useDur) ->
                entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F
        );
    }

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(ModKeyMappings.TOGGLE_POST_SHADER);
    }

}
