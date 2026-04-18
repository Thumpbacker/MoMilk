package com.momilk.mixin.client;

import com.momilk.item.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.cow.AbstractCow;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Goat.class)
public class GoatMixin {

    @Inject(at = @At("INVOKE"), method = "mobInteract", cancellable = true)
    public void mobInteract(final Player player, final InteractionHand hand, final CallbackInfoReturnable<InteractionResult> info)
    {
        Goat goat = (Goat) (Object) this;
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.GLASS_BOTTLE) && !goat.isBaby()) {
            player.playSound(getMilkingSound(goat), 1.0F, 1.0F);
            ItemStack milkBottle = new ItemStack(ModItems.MILK_BOTTLE);
            itemStack.consume(1, player);
            player.addItem(milkBottle);
            info.setReturnValue(InteractionResult.SUCCESS);
        }
    }

    private SoundEvent getMilkingSound(Goat goat) {
        return goat.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_MILK : SoundEvents.GOAT_MILK;
    }
}
