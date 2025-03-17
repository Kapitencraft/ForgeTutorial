package net.kapitencraft.tutorial.datagen;

import net.kapitencraft.tutorial.TutorialMod;
import net.kapitencraft.tutorial.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TutorialMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModelFile modelFile = models().getBuilder("test").element()
                        .allFaces((direction, faceBuilder) -> faceBuilder.texture("#texture").uvs(0, 0, 16, 16).emissivity(15, 15))

                .end().texture("texture", "minecraft:block/diamond_block");

        simpleBlock(ModBlocks.TEST.get(), modelFile);
    }
}
