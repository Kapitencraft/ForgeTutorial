package net.kapitencraft.tutorial.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class TestScreen extends Screen {
    private static final ResourceLocation NINE_SLICE = TutorialMod.res("textures/gui/nine_slice.png");
    private int tickCount;

    public TestScreen() {
        super(Component.literal("Test Screen"));
    }

    @Override
    public void tick() {
        tickCount++;
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        PoseStack pose = pGuiGraphics.pose();
        pose.pushPose();
        pose.translate(this.width / 2f, this.height / 2f, 0);
        pose.scale(32, 32, 1);
        pose.mulPose(Axis.ZP.rotationDegrees(tickCount + pPartialTick));

        pGuiGraphics.fill(-5, -5, 5, 5, 0xFF00FF00);
        pose.popPose();

        pGuiGraphics.fill(0, 0, 10, 10, 0xFF00FFFF);
    }
}
