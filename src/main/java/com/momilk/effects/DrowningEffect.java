package com.momilk.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.awt.*;
import java.util.Random;

public class DrowningEffect extends MobEffect {

    protected DrowningEffect()
    {
        super(MobEffectCategory.HARMFUL, Color.PINK.getRGB());
    }

    // Called every tick to check if the effect can be applied or not
    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the effect every tick
        return true;
    }

    // Called when the effect is applied.
    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {

        if(entity.isUnderWater())
        {
            AttributeInstance respiration = entity.getAttribute(Attributes.OXYGEN_BONUS);
            double oxygenBonus;

            if (respiration != null) {
                oxygenBonus = respiration.getValue();
            } else {
                oxygenBonus = 0.0;
            }

            Random random = new Random();
            int bonus = oxygenBonus > 0.0 && random.nextDouble() >= 1.0 / (oxygenBonus + 1.0) ? entity.getAirSupply() : entity.getAirSupply() - amplifier;

            entity.setAirSupply(bonus);
        }

        return super.applyEffectTick(level, entity, amplifier);
    }
}
