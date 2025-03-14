package net.kapitencraft.tutorial.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class TutorialMixin {

    @Shadow public abstract void remove(Entity.RemovalReason p_150097_);

    private Player self() {
        return (Player) (Object) this;
    }

    /**
     * @author Kapitencraft
     * @reason don't ask
     */
    @Inject(method = "touch", at = @At("HEAD"))
    private void touch(Entity entity, CallbackInfo ci) {
        self().displayClientMessage(Component.literal("touch!").withStyle(ChatFormatting.GREEN), true);
    }

    @ModifyVariable(method = "drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;", at = @At("HEAD"), argsOnly = true)
    private ItemStack modifyIn(ItemStack stack) {
        return new ItemStack(Items.DIAMOND).copyWithCount(stack.getCount());
    }

    @Redirect(method = "getDigSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getBlockEfficiency(Lnet/minecraft/world/entity/LivingEntity;)I"))
    private int getEffiProxy(LivingEntity living) {
        return EnchantmentHelper.getBlockEfficiency(living) + EnchantmentHelper.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE, living);
    }
}
