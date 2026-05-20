package com.momilk.item;

import com.momilk.effects.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ClearAllStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.RemoveStatusEffectsConsumeEffect;

public class ModConsumables {
    public static final Consumable CHOCOLATE_MILK_CONSUMABLE = Consumables.defaultDrink().onConsume(new RemoveAllNegativeStatusEffectConsumeEffect()).build();
    public static final Consumable SPOILED_MILK_CONSUMABLE = Consumables.defaultDrink().onConsume(new RemoveAllPositiveStatusEffectConsumeEffect()).build();
    public static final Consumable MILK_BOTTLE = Consumables.defaultDrink().onConsume(new RemoveOneStatusEffectConsumeEffect()).build();
    public static final Consumable CHOCOLATE_MILK_BOTTLE = Consumables.defaultDrink().onConsume(new RemoveOneNegativeStatusEffectConsumeEffect()).build();
    public static final Consumable SPOILED_MILK_BOTTLE = Consumables.defaultDrink().onConsume(new RemoveOnePositiveStatusEffectConsumeEffect()).build();
    public static final Consumable SUSPICIOUS_MILK = Consumables.defaultDrink().onConsume(new RemoveSuspiciousStatusEffectConsumeEffect()).build();
    public static final Consumable SALT = Consumables.defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.DROWNING, 600, 2), 1)).build();
    public static final Consumable HOT_CHOCOLATE = Consumables.defaultDrink().onConsume(new RemoveAllNeutralStatusEffectConsumeEffect()).build();
    public static final Consumable HOT_CHOCOLATE_BOTTLE = Consumables.defaultDrink().onConsume(new RemoveOneNeutralStatusEffectConsumeEffect()).build();
    public static final Consumable HOG_MILK = Consumables.defaultDrink().onConsume(new RemoveFireConsumeEffect()).build();
    public static final Consumable HOG_MILK_BOTTLE = Consumables.defaultDrink().onConsume(new DecreaseFireTimeConsumeEffect()).build();
    public static final Consumable SPOILED_HOG_MILK_BUCKET = Consumables.defaultDrink().onConsume(new IgniteConsumeEffect(10)).build();
    public static final Consumable SPOILED_HOG_MILK_BOTTLE = Consumables.defaultDrink().onConsume(new IgniteConsumeEffect(5)).build();
    public static final Consumable SALTED_ROTTEN_FLESH = Consumables.defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.4F)).build();
    public static final Consumable SALTED_SPIDER_EYE = Consumables.defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 100, 0), 0.8F)).build();
}
