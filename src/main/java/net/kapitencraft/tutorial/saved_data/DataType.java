package net.kapitencraft.tutorial.saved_data;

import net.minecraft.util.StringRepresentable;

public enum DataType implements StringRepresentable {
    JSON("json"),
    NBT("nbt"),
    NETWORK("friendly_byte_buf");

    public static final EnumCodec<DataType> CODEC = StringRepresentable.fromEnum(DataType::values);

    private final String name;

    DataType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
