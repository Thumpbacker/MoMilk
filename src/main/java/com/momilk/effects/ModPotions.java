package com.momilk.effects;

import com.momilk.MoMilk;
import com.momilk.item.ModItems;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

public class ModPotions {
    public static final Holder<Potion> DROWNING_POTION = Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "dehydrated"), new Potion("dehydrated", new MobEffectInstance(ModEffects.DROWNING, 3600, 2)));
    public static final Holder<Potion> STRONG_DROWNING_POTION = Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "strong_dehydrated"), new Potion("dehydrated", new MobEffectInstance(ModEffects.DROWNING, 1800, 4)));
    public static final Holder<Potion> LONG_DROWNING_POTION = Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "long_dehydrated"), new Potion("dehydrated", new MobEffectInstance(ModEffects.DROWNING, 9600, 2)));
    public static final Holder<Potion> PHANTOM_MASTER_POTION = Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "phantom_master"), new Potion("phantom_master", new MobEffectInstance(ModEffects.DROWNING, 3600 / 2, 2), new MobEffectInstance(MobEffects.SLOW_FALLING, 1800 / 2)));
    public static final Holder<Potion> LONG_PHANTOM_MASTER_POTION = Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "long_phantom_master"), new Potion("phantom_master", new MobEffectInstance(ModEffects.DROWNING, 3600, 2), new MobEffectInstance(MobEffects.SLOW_FALLING, 1800)));
    public static final Holder<Potion> OXYGEN_POTION = Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "oxygen"), new Potion("oxygen", new MobEffectInstance(ModEffects.OXYGEN, 3600)));
    public static final Holder<Potion> STRONG_OXYGEN_POTION = Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "strong_oxygen"), new Potion("oxygen", new MobEffectInstance(ModEffects.OXYGEN, 1800, 1)));
    public static final Holder<Potion> LONG_OXYGEN_POTION = Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "long_oxygen"), new Potion("oxygen", new MobEffectInstance(ModEffects.OXYGEN, 9600)));
    public static final Holder<Potion> MELON_MASTER_POTION = Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "melon_master"), new Potion("melon_master", new MobEffectInstance(ModEffects.OXYGEN, 1800), new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1)));
    public static final Holder<Potion> LONG_MELON_MASTER_POTION = Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "long_melon_master_master"), new Potion("melon_master", new MobEffectInstance(ModEffects.OXYGEN, 9600), new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1)));
    public static final Holder<Potion> GLOWING_MASTER_POTION = Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "glowing_master"), new Potion("glowing_master", new MobEffectInstance(ModEffects.OXYGEN, 1800), new MobEffectInstance(MobEffects.GLOWING, 1800 / 2), new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1)));
    public static final Holder<Potion> LONG_GLOWING_MASTER_POTION = Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "long_glowing_master"), new Potion("glowing_master", new MobEffectInstance(ModEffects.OXYGEN, 3600), new MobEffectInstance(MobEffects.GLOWING, 1800), new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1)));

    public static void register()
    {
        FabricPotionBrewingBuilder.BUILD.register(builder -> {builder.addMix(Potions.AWKWARD, ModItems.SALT, DROWNING_POTION);});
        FabricPotionBrewingBuilder.BUILD.register(builder -> {builder.addMix(ModPotions.DROWNING_POTION, Items.GLOWSTONE_DUST, STRONG_DROWNING_POTION);});
        FabricPotionBrewingBuilder.BUILD.register(builder -> {builder.addMix(ModPotions.DROWNING_POTION, Items.REDSTONE, LONG_DROWNING_POTION);});
        FabricPotionBrewingBuilder.BUILD.register(builder -> {builder.addMix(Potions.AWKWARD, ModItems.FLOATING_SALT, PHANTOM_MASTER_POTION);});
        FabricPotionBrewingBuilder.BUILD.register(builder -> {builder.addMix(ModPotions.PHANTOM_MASTER_POTION, Items.REDSTONE, LONG_PHANTOM_MASTER_POTION);});
        FabricPotionBrewingBuilder.BUILD.register(builder -> {builder.addMix(ModPotions.DROWNING_POTION, Items.FERMENTED_SPIDER_EYE, OXYGEN_POTION);});
        FabricPotionBrewingBuilder.BUILD.register(builder -> {builder.addMix(ModPotions.STRONG_DROWNING_POTION, Items.FERMENTED_SPIDER_EYE, STRONG_OXYGEN_POTION);});
        FabricPotionBrewingBuilder.BUILD.register(builder -> {builder.addMix(ModPotions.OXYGEN_POTION, Items.GLOWSTONE_DUST, STRONG_OXYGEN_POTION);});
        FabricPotionBrewingBuilder.BUILD.register(builder -> {builder.addMix(ModPotions.LONG_DROWNING_POTION, Items.FERMENTED_SPIDER_EYE, LONG_OXYGEN_POTION);});
        FabricPotionBrewingBuilder.BUILD.register(builder -> {builder.addMix(ModPotions.OXYGEN_POTION, Items.REDSTONE, LONG_OXYGEN_POTION);});
        FabricPotionBrewingBuilder.BUILD.register(builder -> {builder.addMix(Potions.AWKWARD, ModItems.REJUVENATING_SALT, MELON_MASTER_POTION);});
        FabricPotionBrewingBuilder.BUILD.register(builder -> {builder.addMix(ModPotions.MELON_MASTER_POTION, Items.REDSTONE, LONG_MELON_MASTER_POTION);});
        FabricPotionBrewingBuilder.BUILD.register(builder -> {builder.addMix(Potions.AWKWARD, ModItems.GLOWING_SALT, GLOWING_MASTER_POTION);});
        FabricPotionBrewingBuilder.BUILD.register(builder -> {builder.addMix(ModPotions.LONG_GLOWING_MASTER_POTION, Items.REDSTONE, LONG_GLOWING_MASTER_POTION);});
    }
}
