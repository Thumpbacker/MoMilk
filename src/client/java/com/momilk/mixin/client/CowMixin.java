package com.momilk.mixin.client;

import com.momilk.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.cow.AbstractCow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractCow.class)
public class CowMixin {

    @Inject(at = @At("INVOKE"), method = "mobInteract", cancellable = true)
    public void mobInteract(final Player player, final InteractionHand hand, final CallbackInfoReturnable<InteractionResult> info)
    {
        AbstractCow cow = (AbstractCow) (Object) this;
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.GLASS_BOTTLE) && !cow.isBaby()) {
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack milkBottle = new ItemStack(ModItems.MILK_BOTTLE);
            itemStack.consume(1, player);
            player.addItem(milkBottle);
            info.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
