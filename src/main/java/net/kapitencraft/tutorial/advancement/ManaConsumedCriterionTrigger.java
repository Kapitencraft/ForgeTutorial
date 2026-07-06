package net.kapitencraft.tutorial.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;


public class ManaConsumedCriterionTrigger extends SimpleCriterionTrigger<ManaConsumedCriterionTrigger.Instance> {
    public static final Codec<Instance> CODEC = RecordCodecBuilder.create(i -> i.group(
            ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(Instance::player),
            Type.CODEC.fieldOf("type").forGetter(inst -> inst.type),
            Codec.DOUBLE.fieldOf("value").forGetter(inst -> inst.value)
    ).apply(i, Instance::new));

    @Override
    public Codec<Instance> codec() {
        return CODEC;
    }

    public void trigger(ServerPlayer pPlayer, double amount) {
        this.trigger(pPlayer, instance -> instance.enough(amount));
    }

    public Criterion<Instance> exactly(double value) {
        return new Criterion<>(this, new Instance(Optional.empty(), Type.EXACTLY, value));
    }

    public Criterion<Instance> above(double value) {
        return new Criterion<>(this, new Instance(Optional.empty(), Type.MORE, value));
    }

    public Criterion<Instance> below(double value) {
        return new Criterion<>(this, new Instance(Optional.empty(), Type.LESS, value));
    }

    public Criterion<Instance> any() {
        return above(0);
    }

    private enum Type implements StringRepresentable {
        EXACTLY,
        LESS,
        MORE;

        private static final EnumCodec<Type> CODEC = StringRepresentable.fromEnum(Type::values);

        @Override
        public @NotNull String getSerializedName() {
            return name().toLowerCase();
        }
    }

    public static class Instance implements SimpleInstance {
        private final ContextAwarePredicate player;
        private final Type type;
        private final double value;

        @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
        private Instance(Optional<ContextAwarePredicate> player, Type type, double value) {
            this.player = player.orElse(null);
            this.type = type;
            this.value = value;
        }

        @Override
        public @NotNull Optional<ContextAwarePredicate> player() {
            return Optional.ofNullable(player);
        }

        public boolean enough(double mana) {
            return switch (type) {
                case LESS -> mana <= value;
                case EXACTLY -> mana == value;
                case MORE -> mana >= value;
            };
        }
    }
}
