package net.kapitencraft.tutorial.datagen;

import net.kapitencraft.tutorial.TutorialMod;
import net.kapitencraft.tutorial.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import javax.annotation.Nullable;

public class ModItemModelsProvider extends ItemModelProvider {
    public ModItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.WIZARD_HAT, ResourceLocation.withDefaultNamespace("item/diamond_helmet"));
    }

    private ItemModelBuilder simpleItem(DeferredItem<? extends Item> item, @Nullable ResourceLocation texture) {
        return parentItem(item, mcLoc("item/generated"), texture);
    }

    private ItemModelBuilder parentItem(DeferredItem<? extends Item> item, ResourceLocation parent, @Nullable ResourceLocation texture) {
        return withExistingParent(item.getId().getPath(),
                parent).texture("layer0",
                (texture == null ? modLoc("item/" + item.getId().getPath()) : texture));
    }
}
