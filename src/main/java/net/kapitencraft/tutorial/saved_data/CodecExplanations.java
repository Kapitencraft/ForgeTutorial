package net.kapitencraft.tutorial.saved_data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class CodecExplanations {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final PrimitiveCodec<UUID> UUID_CODEC = new PrimitiveCodec<>() {
        @Override
        public <T> DataResult<UUID> read(DynamicOps<T> ops, T input) {
            return ops.getStringValue(input).map(UUID::fromString);
        }

        @Override
        public <T> T write(DynamicOps<T> ops, UUID value) {
            return ops.createString(value.toString());
        }
    };
    private static final Codec<CodecExplanations> CODEC = RecordCodecBuilder.create(codecExplanationsInstance ->
            codecExplanationsInstance.group(
                    Codec.INT.fieldOf("a").forGetter(i -> i.a),
                    Codec.STRING.fieldOf("b").forGetter(i -> i.b),
                    UUID_CODEC.fieldOf("c").forGetter(i -> i.c),
                    ForgeRegistries.ITEMS.getCodec().fieldOf("d").forGetter(i -> i.d),
                    DataType.CODEC.fieldOf("e").forGetter(i -> i.e)
            ).apply(codecExplanationsInstance, CodecExplanations::new)
    );

    private static final File DATA = new File(Minecraft.getInstance().gameDirectory, "tutorial/codec_explanation.json");


    private final int a;
    private final String b;
    private final UUID c;
    private final Item d;
    private final DataType e;

    public CodecExplanations(int a, String b, UUID c, Item d, DataType e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }

    private Tag save() {
        return CODEC.encodeStart(NbtOps.INSTANCE, this).result().orElse(null);
    }

    public void saveToData() {
        DATA.getParentFile().mkdirs();
        try {
            DATA.createNewFile();
            FileWriter writer = new FileWriter(DATA);
            writer.write(GSON.toJson(CODEC.encodeStart(JsonOps.INSTANCE, this).result().orElseGet(JsonObject::new)));
            writer.close();
        } catch (IOException e) {
            TutorialMod.LOGGER.warn("failed to load file: " + e.getMessage());
        }
    }
}