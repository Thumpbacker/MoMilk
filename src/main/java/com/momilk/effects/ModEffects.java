package com.momilk.effects;

import com.momilk.MoMilk;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.awt.*;

public class ModEffects {

    public static final Holder<MobEffect> DROWNING = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "drowning"), new DrowningEffect());
    public static final Holder<MobEffect> OXYGEN = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "oxygen"), new OxygenEffect().addAttributeModifier(Attributes.OXYGEN_BONUS, Identifier.withDefaultNamespace("effect.oxygen"), 4.0, AttributeModifier.Operation.ADD_VALUE));

    public static void register() {
        // ...
    }

}
