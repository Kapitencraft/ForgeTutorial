package net.kapitencraft.tutorial.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class TestBlock extends Block {
    public TestBlock() {
        super(Properties.copy(Blocks.AMETHYST_BLOCK));
    }


    public static int getColor(BlockState state, BlockAndTintGetter blockAndTintGetter, BlockPos blockPos, int i) {
        if (i != 0) return -1;
        int y = blockPos.getY() + 64;
        int color = y * 255 / 384;
        return (color << 8 | color) << 8 | color;
    }
}
