package com.momilk.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;

public record RemoveOneNegativeStatusEffectConsumeEffect() implements ConsumeEffect {

    @Override
    public Type<? extends ConsumeEffect> getType() {
        return null;
    }

    @Override
    public boolean apply(Level level, ItemStack stack, LivingEntity user) {

        for (int i = 0; i < user.getActiveEffectsMap().size(); i++) {
            var effect = user.getActiveEffectsMap().values().stream().toList().get(i).getEffect();
            
            if(!effect.value().isBeneficial())
            {
                return user.removeEffect(effect);
            }
        }

        return false;
    }
}
