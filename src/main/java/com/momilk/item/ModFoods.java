package com.momilk.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties APPLE_CIDER = new FoodProperties.Builder().nutrition(5).saturationModifier(0.4F).build();
    public static final FoodProperties CINNAMON_STICK = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).build();
    public static final FoodProperties SALT = new FoodProperties.Builder().nutrition(1).saturationModifier(0.5F).alwaysEdible().build();
    public static final FoodProperties HOT_CHOCOLATE = new FoodProperties.Builder().nutrition(5).saturationModifier(0.5F).alwaysEdible().build();
    public static final FoodProperties CEREAL = new FoodProperties.Builder().nutrition(20).saturationModifier(2F).build();
    public static final FoodProperties CEREAL_BOTTLE = new FoodProperties.Builder().nutrition(10).saturationModifier(1F).build();
    public static final FoodProperties HOT_CHOCOLATE_BOTTLE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).alwaysEdible().build();
    public static final FoodProperties SPOILED_CEREAL = new FoodProperties.Builder().nutrition(-20).saturationModifier(-2F).alwaysEdible().build();
    public static final FoodProperties SPOILED_CEREAL_BOTTLE = new FoodProperties.Builder().nutrition(-10).saturationModifier(-1F).alwaysEdible().build();
    public static final FoodProperties SALTED_COD = new FoodProperties.Builder().nutrition(7).saturationModifier(0.8F).build();
    public static final FoodProperties SALTED_SALMON = new FoodProperties.Builder().nutrition(9).saturationModifier(0.8F).build();
    public static final FoodProperties SALTED_TROPICAL_FISH = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3F).build();
    public static final FoodProperties SALTED_POTATO = new FoodProperties.Builder().nutrition(8).saturationModifier(0.7F).build();

}
