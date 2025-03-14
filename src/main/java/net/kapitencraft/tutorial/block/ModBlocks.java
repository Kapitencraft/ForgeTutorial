package net.kapitencraft.tutorial.block;

import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface ModBlocks {
    DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);
    RegistryObject<Block> TEST = REGISTRY.register("test", TestBlock::new);
}
