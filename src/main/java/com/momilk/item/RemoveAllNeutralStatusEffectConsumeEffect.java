package com.momilk.item;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;

public record RemoveAllNeutralStatusEffectConsumeEffect() implements ConsumeEffect {

    @Override
    public Type<? extends ConsumeEffect> getType() {
        return null;
    }

    @Override
    public boolean apply(Level level, ItemStack stack, LivingEntity user) {
        var eList = user.getActiveEffectsMap().values().stream().toList();
        user.removeAllEffects();

        for (int i = 0; i < eList.size(); i++)
        {
            if(eList.get(i).getEffect().value().getCategory() != MobEffectCategory.NEUTRAL) {
                user.addEffect(eList.get(i));
            }
        }

        return true;
    }
}
