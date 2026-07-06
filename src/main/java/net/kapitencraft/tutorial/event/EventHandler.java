package net.kapitencraft.tutorial.event;

import net.kapitencraft.tutorial.attribute.ModAttributes;
import net.kapitencraft.tutorial.mob_effect.ModMobEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

@EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public static void increaseHealingFromAttribute(LivingHealEvent event) {
        double attrVal = ModAttributes.getAttributeValue(event.getEntity(), ModAttributes.HEAL_ATTRIBUTE);
        if (attrVal != -1) event.setAmount((float) (event.getAmount() * (1 + attrVal / 100)));
    }

    @SubscribeEvent
    public static void onMobEffectAdded(MobEffectEvent.Added event) {
        if (event.getEntity() instanceof Player player && event.getEffectInstance().getEffect() == ModMobEffects.FLYING) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
    }

    @SubscribeEvent
    public static void onMobEffectRemove(MobEffectEvent.Remove event) {
        if (event.getEntity() instanceof ServerPlayer player && event.getEffectInstance().getEffect() == ModMobEffects.FLYING) {
            Abilities abilities = player.getAbilities();
            abilities.mayfly = !player.gameMode.isSurvival(); //set may fly to player being in creative / spectator
            if (abilities.flying && !abilities.mayfly)
                abilities.flying = false; //stop flying if no longer permitted
            player.onUpdateAbilities();
        }
    }

    @SubscribeEvent
    public static void onMobEffectRemove(MobEffectEvent.Expired event) {
        if (event.getEntity() instanceof ServerPlayer player && event.getEffectInstance().getEffect() == ModMobEffects.FLYING) {
            Abilities abilities = player.getAbilities();
            abilities.mayfly = !player.gameMode.isSurvival(); //set may fly to player being in creative / spectator
            if (abilities.flying && !abilities.mayfly)
                abilities.flying = false; //stop flying if no longer permitted
            player.onUpdateAbilities();
        }
    }

    @SubscribeEvent
    public static void addAttributesToEntities(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, ModAttributes.HEAL_ATTRIBUTE);
        event.add(EntityType.PLAYER, ModAttributes.MANA);
    }
}
