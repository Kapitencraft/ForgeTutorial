package net.kapitencraft.tutorial.item;

import net.kapitencraft.tutorial.TutorialMod;
import net.kapitencraft.tutorial.item.component.BackpackContent;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface ModDataComponents {
    DeferredRegister<DataComponentType<?>> REGISTRY = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, TutorialMod.MOD_ID);

    Supplier<DataComponentType<BackpackContent>> BACKPACK_CONTENT = REGISTRY.register("backpack_content",
            () -> DataComponentType.<BackpackContent>builder()
                    .persistent(BackpackContent.CODEC)
                    .networkSynchronized(BackpackContent.STREAM_CODEC)
                    .build()
    );
}