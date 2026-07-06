package net.kapitencraft.tutorial.block;

import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface ModBlocks {
    DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(TutorialMod.MOD_ID);
    DeferredBlock<Block> TEST = REGISTRY.register("test", TestBlock::new);
}
