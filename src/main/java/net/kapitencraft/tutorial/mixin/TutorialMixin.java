package net.kapitencraft.tutorial.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
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
}
