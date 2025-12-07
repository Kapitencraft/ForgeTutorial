package net.kapitencraft.tutorial.client;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public interface ModKeyMappings {
    KeyMapping TOGGLE_POST_SHADER = new KeyMapping("toggle_greyscale", GLFW.GLFW_KEY_P, "category");
}
