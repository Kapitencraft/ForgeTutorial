package net.kapitencraft.tutorial.event;

import net.kapitencraft.tutorial.attribute.ModAttributes;
import net.kapitencraft.tutorial.mob_effect.ModMobEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public static void increaseHealingFromAttribute(LivingHealEvent event) {
        double attrVal = ModAttributes.getAttributeValue(event.getEntity(), ModAttributes.HEAL_ATTRIBUTE.get());
        if (attrVal != -1) event.setAmount((float) (event.getAmount() * (1 + attrVal / 100)));
    }

    @SubscribeEvent
    public static void onMobEffectAdded(MobEffectEvent.Added event) {
        if (event.getEntity() instanceof Player player && event.getEffectInstance().getEffect() == ModMobEffects.FLYING.get()) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
    }

    @SubscribeEvent
    public static void onMobEffectRemove(MobEffectEvent.Remove event) {
        if (event.getEntity() instanceof ServerPlayer player && event.getEffectInstance().getEffect() == ModMobEffects.FLYING.get()) {
            Abilities abilities = player.getAbilities();
            abilities.mayfly = !player.gameMode.isSurvival(); //set may fly to player being in creative / spectator
            if (abilities.flying && !abilities.mayfly)
                abilities.flying = false; //stop flying if no longer permitted
            player.onUpdateAbilities();
        }
    }

}
