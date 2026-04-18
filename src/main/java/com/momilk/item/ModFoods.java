package com.momilk.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties APPLE_CIDER = new FoodProperties.Builder().nutrition(5).saturationModifier(0.4F).build();
    public static final FoodProperties CINNAMON_STICK = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).build();
    public static final FoodProperties SALT = new FoodProperties.Builder().nutrition(1).saturationModifier(0.5F).alwaysEdible().build();
}
