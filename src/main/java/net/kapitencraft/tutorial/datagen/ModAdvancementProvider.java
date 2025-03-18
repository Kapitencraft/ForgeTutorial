package net.kapitencraft.tutorial.datagen;

import net.kapitencraft.tutorial.TutorialMod;
import net.kapitencraft.tutorial.advancement.ManaConsumedCriterionTrigger;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.FrameType;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends ForgeAdvancementProvider {
    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new ManaGenerator()));
    }

    public static class ManaGenerator implements AdvancementGenerator {

        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            Advancement.Builder.advancement()
                    .display(Items.END_CRYSTAL, Component.translatable("advancement.mana.root.title"), Component.translatable("advancement.mana.root.description"), new ResourceLocation("textures/block/crying_obsidian.png"), FrameType.TASK, false, false, false)
                    .addCriterion("consumed_mana", ManaConsumedCriterionTrigger.any())
                    .save(saver, TutorialMod.res("mana/root"));
        }
    }
}
