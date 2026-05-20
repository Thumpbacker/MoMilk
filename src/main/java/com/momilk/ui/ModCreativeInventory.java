package com.momilk.ui;

import com.momilk.MoMilk;
import com.momilk.block.ModBlocks;
import com.momilk.effects.ModEffects;
import com.momilk.effects.ModPotions;
import com.momilk.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackLinkedSet;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.block.SuspiciousEffectHolder;

import java.util.List;
import java.util.Set;

public class ModCreativeInventory {

    public static final ResourceKey<CreativeModeTab> MO_MILK_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "creative_tab")
    );

    public static final ResourceKey<CreativeModeTab> MO_MILK_BUILDING_BLOCKS_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "creative_tab_building")
    );

    public static final ResourceKey<CreativeModeTab> MO_MILK_NATURAL_BLOCKS_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "creative_tab_natural")
    );

    public static final ResourceKey<CreativeModeTab> MO_MILK_FUNCTIONAL_BLOCKS_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "creative_tab_functional")
    );

    public static final ResourceKey<CreativeModeTab> MO_MILK_REDSTONE_BLOCKS_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "creative_tab_redstone")
    );

    public static final ResourceKey<CreativeModeTab> MO_MILK_COMBAT_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "creative_tab_combat")
    );

    public static final ResourceKey<CreativeModeTab> MO_MILK_FOOD_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "creative_tab_food")
    );

    public static final ResourceKey<CreativeModeTab> MO_MILK_INGREDIENTS_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, "creative_tab_ingredients")
    );

    public static final CreativeModeTab MO_MILK_BUILDING_BLOCKS_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.SALT_BLOCK.asItem()))
            .title(Component.translatable("itemGroup.momilk.buliding_blocks"))
            .displayItems((params, output) ->{
                output.accept(ModBlocks.WRAPPED_BAMBOO_BLOCK);
                output.accept(ModBlocks.STRIPPED_WRAPPED_BAMBOO_BLOCK);
                output.accept(ModBlocks.SALT_BLOCK);
                output.accept(ModBlocks.MEDIUM_SALT_BLOCK);
                output.accept(ModBlocks.LARGE_SALT_BLOCK);
                output.accept(ModBlocks.SALT_SLAB);
                output.accept(ModBlocks.SALT_STAIRS);
                output.accept(ModBlocks.SALT_WALL);
            }).build();

    public static final CreativeModeTab MO_MILK_NATURAL_BLOCKS_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.CONGEALED_MILK.asItem()))
            .title(Component.translatable("itemGroup.momilk.natural_blocks"))
            .displayItems((params, output) ->{
                output.accept(ModBlocks.SALT_ORE);
                output.accept(ModBlocks.DEEPSLATE_SALT_ORE);
                output.accept(ModBlocks.BRINY_CLOSED_EYEBLOSSOM);
                output.accept(ModBlocks.BRINY_OPEN_EYEBLOSSOM);
                output.accept(ModBlocks.BRINY_TUBE_CORAL_BLOCK);
                output.accept(ModBlocks.BRINY_BRAIN_CORAL_BLOCK);
                output.accept(ModBlocks.BRINY_BUBBLE_CORAL_BLOCK);
                output.accept(ModBlocks.BRINY_FIRE_CORAL_BLOCK);
                output.accept(ModBlocks.BRINY_HORN_CORAL_BLOCK);
                output.accept(ModBlocks.BRINY_TUBE_CORAL);
                output.accept(ModBlocks.BRINY_BRAIN_CORAL);
                output.accept(ModBlocks.BRINY_BUBBLE_CORAL);
                output.accept(ModBlocks.BRINY_FIRE_CORAL);
                output.accept(ModBlocks.BRINY_HORN_CORAL);
                output.accept(ModItems.BRINY_TUBE_CORAL_FAN);
                output.accept(ModItems.BRINY_BRAIN_CORAL_FAN);
                output.accept(ModItems.BRINY_BUBBLE_CORAL_FAN);
                output.accept(ModItems.BRINY_FIRE_CORAL_FAN);
                output.accept(ModItems.BRINY_HORN_CORAL_FAN);
                output.accept(ModBlocks.CONGEALED_MILK);
                output.accept(ModBlocks.CONGEALED_CEREAL);
                output.accept(ModBlocks.CONGEALED_CHOCOLATE_MILK);
                output.accept(ModBlocks.CONGEALED_HOT_CHOCOLATE);
                output.accept(ModBlocks.CONGEALED_SPOILED_MILK);
                output.accept(ModBlocks.CONGEALED_SPOILED_CEREAL);
                output.accept(ModBlocks.CONGEALED_HOG_MILK);
                output.accept(ModBlocks.CONGEALED_SPOILED_HOG_MILK);
            }).build();

    public static final CreativeModeTab MO_MILK_FUNCTIONAL_BLOCKS_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.LARGE_SALT_LAMP.asItem()))
            .title(Component.translatable("itemGroup.momilk.functional_blocks"))
            .displayItems((params, output) ->{
                output.accept(ModBlocks.SALT_LAMP);
                output.accept(ModBlocks.SOUL_SALT_LAMP);
                output.accept(ModBlocks.COPPER_SALT_LAMP);
                output.accept(ModBlocks.REDSTONE_SALT_LAMP);
                output.accept(ModBlocks.MEDIUM_SALT_LAMP);
                output.accept(ModBlocks.MEDIUM_SOUL_SALT_LAMP);
                output.accept(ModBlocks.MEDIUM_COPPER_SALT_LAMP);
                output.accept(ModBlocks.MEDIUM_REDSTONE_SALT_LAMP);
                output.accept(ModBlocks.LARGE_SALT_LAMP);
                output.accept(ModBlocks.LARGE_SOUL_SALT_LAMP);
                output.accept(ModBlocks.LARGE_COPPER_SALT_LAMP);
                output.accept(ModBlocks.LARGE_REDSTONE_SALT_LAMP);
            }).build();

    public static final CreativeModeTab MO_MILK_REDSTONE_BLOCKS_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.LARGE_REDSTONE_SALT_LAMP.asItem()))
            .title(Component.translatable("itemGroup.momilk.redstone_blocks"))
            .displayItems((params, output) ->{
                output.accept(ModBlocks.REDSTONE_SALT_LAMP);
                output.accept(ModBlocks.MEDIUM_REDSTONE_SALT_LAMP);
                output.accept(ModBlocks.LARGE_REDSTONE_SALT_LAMP);
                output.accept(ModBlocks.CONGEALED_CEREAL);
                output.accept(ModBlocks.CHEESE);
            }).build();

    public static final CreativeModeTab MO_MILK_COMBAT_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.MILK_ARROW))
            .title(Component.translatable("itemGroup.momilk.combat"))
            .displayItems((params, output) ->{
                output.accept(ModItems.MILK_ARROW);
                output.accept(ModItems.CEREAL_ARROW);
                output.accept(ModItems.CHOCOLATE_MILK_ARROW);
                output.accept(ModItems.HOT_CHOCOLATE_ARROW);
                output.accept(ModItems.SPOILED_MILK_ARROW);
                output.accept(ModItems.SPOILED_CEREAL_ARROW);
                output.accept(ModItems.HOG_MILK_ARROW);
                output.accept(ModItems.SPOILED_HOG_MILK_ARROW);
            }).build();

    public static final CreativeModeTab MO_MILK_FOOD_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.CHOCOLATE_MILK_BUCKET))
            .title(Component.translatable("itemGroup.momilk.foods"))
            .displayItems((params, output) ->{
                output.accept(ModItems.SALT);
                output.accept(ModItems.CINNAMON_STICK);
                output.accept(ModItems.HYPHAE_STICK);
                output.accept(ModBlocks.CHEESE);
                output.accept(ModBlocks.CONGEALED_CEREAL);
                output.accept(ModBlocks.CONGEALED_HOT_CHOCOLATE);
                output.accept(ModBlocks.CONGEALED_SPOILED_CEREAL);
                output.accept(ModItems.CEREAL);
                output.accept(ModItems.CHOCOLATE_MILK_BUCKET);
                output.accept(ModItems.HOT_CHOCOLATE);
                output.accept(ModItems.SPOILED_MILK_BUCKET);
                output.accept(ModItems.SPOILED_CEREAL);
                output.accept(ModItems.HOG_MILK_BUCKET);
                output.accept(ModItems.SPOILED_HOG_MILK_BUCKET);
                output.accept(ModItems.MILK_BOTTLE);
                output.accept(ModItems.CEREAL_BOTTLE);
                output.accept(ModItems.CHOCOLATE_MILK_BOTTLE);
                output.accept(ModItems.HOT_CHOCOLATE_BOTTLE);
                output.accept(ModItems.SPOILED_MILK_BOTTLE);
                output.accept(ModItems.SPOILED_CEREAL_BOTTLE);
                output.accept(ModItems.HOG_MILK_BOTTLE);
                output.accept(ModItems.SPOILED_HOG_MILK_BOTTLE);

                //Suspicious Milk
                ItemStack stack = new ItemStack(ModItems.SUSPICIOUS_MILK);
                stack.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, SuspiciousEffectHolder.getAllEffectHolders().getFirst().getSuspiciousEffects());
                output.accept(stack);

                output.accept(ModItems.APPLE_CIDER);

            }).build();

    public static final CreativeModeTab MO_MILK_INGREDIENT_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.REJUVENATING_SALT))
            .title(Component.translatable("itemGroup.momilk.ingredient"))
            .displayItems((params, output) ->{
                output.accept(ModItems.SALT);
                output.accept(ModItems.FLOATING_SALT);
                output.accept(ModItems.REJUVENATING_SALT);
            }).build();


    public static final CreativeModeTab MO_MILK_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.CHOCOLATE_MILK_BUCKET))
            .title(Component.translatable("itemGroup.momilk"))
            .displayItems((params, output) -> {
                //Items
                output.accept(ModItems.MILK_BOTTLE);
                output.accept(ModItems.CEREAL_BOTTLE);
                output.accept(ModItems.CHOCOLATE_MILK_BOTTLE);
                output.accept(ModItems.HOT_CHOCOLATE_BOTTLE);
                output.accept(ModItems.SPOILED_MILK_BOTTLE);
                output.accept(ModItems.SPOILED_CEREAL_BOTTLE);
                output.accept(ModItems.HOG_MILK_BOTTLE);
                output.accept(ModItems.SPOILED_HOG_MILK_BOTTLE);
                output.accept(ModItems.CEREAL);
                output.accept(ModItems.CHOCOLATE_MILK_BUCKET);
                output.accept(ModItems.HOT_CHOCOLATE);
                output.accept(ModItems.SPOILED_MILK_BUCKET);
                output.accept(ModItems.SPOILED_CEREAL);
                output.accept(ModItems.HOG_MILK_BUCKET);
                output.accept(ModItems.SPOILED_HOG_MILK_BUCKET);
                output.accept(ModItems.MILK_ARROW);
                output.accept(ModItems.CEREAL_ARROW);
                output.accept(ModItems.CHOCOLATE_MILK_ARROW);
                output.accept(ModItems.HOT_CHOCOLATE_ARROW);
                output.accept(ModItems.SPOILED_MILK_ARROW);
                output.accept(ModItems.SPOILED_CEREAL_ARROW);
                output.accept(ModItems.HOG_MILK_ARROW);
                output.accept(ModItems.SPOILED_HOG_MILK_ARROW);
                output.accept(ModItems.APPLE_CIDER);
                output.accept(ModItems.CINNAMON_STICK);
                output.accept(ModItems.HYPHAE_STICK);
                output.accept(ModItems.SALT);
                output.accept(ModItems.FLOATING_SALT);
                output.accept(ModItems.REJUVENATING_SALT);
                output.accept(ModItems.GLOWING_SALT);
                output.accept(ModItems.SALTED_COD);
                output.accept(ModItems.SALTED_SALMON);
                output.accept(ModItems.SALTED_TROPICAL_FISH);
                output.accept(ModItems.SALTED_POTATO);
                output.accept(ModItems.SALTED_ROTTEN_FLESH);
                output.accept(ModItems.SALTED_SPIDER_EYE);
                ItemStack stack = new ItemStack(ModItems.SUSPICIOUS_MILK);
                stack.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, SuspiciousEffectHolder.getAllEffectHolders().getFirst().getSuspiciousEffects());
                output.accept(stack);
                //Blocks
                output.accept(ModBlocks.CONGEALED_MILK.asItem());
                output.accept(ModBlocks.CONGEALED_CHOCOLATE_MILK.asItem());
                output.accept(ModBlocks.CONGEALED_SPOILED_MILK.asItem());
                output.accept(ModBlocks.CONGEALED_CEREAL.asItem());
                output.accept(ModBlocks.CONGEALED_SPOILED_CEREAL.asItem());
                output.accept(ModBlocks.CONGEALED_HOT_CHOCOLATE.asItem());
                output.accept(ModBlocks.CONGEALED_HOG_MILK.asItem());
                output.accept(ModBlocks.CONGEALED_SPOILED_HOG_MILK.asItem());
                output.accept(ModBlocks.SALT_ORE.asItem());
                output.accept(ModBlocks.DEEPSLATE_SALT_ORE.asItem());
                output.accept(ModBlocks.SALT_BLOCK.asItem());
                output.accept(ModBlocks.SALT_LAMP.asItem());
                output.accept(ModBlocks.SOUL_SALT_LAMP.asItem());
                output.accept(ModBlocks.COPPER_SALT_LAMP.asItem());
                output.accept(ModBlocks.REDSTONE_SALT_LAMP.asItem());
                output.accept(ModBlocks.MEDIUM_SALT_BLOCK.asItem());
                output.accept(ModBlocks.MEDIUM_SALT_LAMP.asItem());
                output.accept(ModBlocks.MEDIUM_SOUL_SALT_LAMP.asItem());
                output.accept(ModBlocks.MEDIUM_COPPER_SALT_LAMP.asItem());
                output.accept(ModBlocks.MEDIUM_REDSTONE_SALT_LAMP.asItem());
                output.accept(ModBlocks.LARGE_SALT_BLOCK.asItem());
                output.accept(ModBlocks.LARGE_SALT_LAMP.asItem());
                output.accept(ModBlocks.LARGE_SOUL_SALT_LAMP.asItem());
                output.accept(ModBlocks.LARGE_COPPER_SALT_LAMP.asItem());
                output.accept(ModBlocks.LARGE_REDSTONE_SALT_LAMP.asItem());
                output.accept(ModBlocks.SALT_SLAB.asItem());
                output.accept(ModBlocks.SALT_STAIRS.asItem());
                output.accept(ModBlocks.SALT_WALL.asItem());
                output.accept(ModBlocks.CHEESE.asItem());
                output.accept(ModBlocks.BRINY_OPEN_EYEBLOSSOM.asItem());
                output.accept(ModBlocks.BRINY_CLOSED_EYEBLOSSOM.asItem());
                output.accept(ModBlocks.BRINY_TUBE_CORAL.asItem());
                output.accept(ModBlocks.BRINY_BRAIN_CORAL.asItem());
                output.accept(ModBlocks.BRINY_BUBBLE_CORAL.asItem());
                output.accept(ModBlocks.BRINY_FIRE_CORAL.asItem());
                output.accept(ModBlocks.BRINY_HORN_CORAL.asItem());
                output.accept(ModBlocks.BRINY_TUBE_CORAL_BLOCK.asItem());
                output.accept(ModBlocks.BRINY_BRAIN_CORAL_BLOCK.asItem());
                output.accept(ModBlocks.BRINY_BUBBLE_CORAL_BLOCK.asItem());
                output.accept(ModBlocks.BRINY_FIRE_CORAL_BLOCK.asItem());
                output.accept(ModBlocks.BRINY_HORN_CORAL_BLOCK.asItem());
                output.accept(ModItems.BRINY_TUBE_CORAL_FAN);
                output.accept(ModItems.BRINY_BRAIN_CORAL_FAN);
                output.accept(ModItems.BRINY_BUBBLE_CORAL_FAN);
                output.accept(ModItems.BRINY_FIRE_CORAL_FAN);
                output.accept(ModItems.BRINY_HORN_CORAL_FAN);
                output.accept(ModBlocks.CINNAMON_STICK_BALE);
                output.accept(ModBlocks.HYPHAE_STICK_BALE);
                output.accept(ModBlocks.WRAPPED_BAMBOO_BLOCK);
                output.accept(ModBlocks.STRIPPED_WRAPPED_BAMBOO_BLOCK);

                /*
                List<SuspiciousEffectHolder> effectHolders = SuspiciousEffectHolder.getAllEffectHolders();

                for (SuspiciousEffectHolder effectHolder : effectHolders) {
                    ItemStack stack = new ItemStack(ModItems.SUSPICIOUS_MILK);
                    stack.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, effectHolder.getSuspiciousEffects());
                    output.accept(stack);
                }*/
            })
            .build();

    public static void generateSuspiciousMilks(final CreativeModeTab.Output output, final CreativeModeTab.TabVisibility tabVisibility) {
        List<SuspiciousEffectHolder> effectHolders = SuspiciousEffectHolder.getAllEffectHolders();
        Set<ItemStack> stewItems = ItemStackLinkedSet.createTypeAndComponentsSet();

        for (SuspiciousEffectHolder effectHolder : effectHolders) {
            ItemStack stack = new ItemStack(Items.SUSPICIOUS_STEW);
            stack.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, effectHolder.getSuspiciousEffects());
            stewItems.add(stack);
        }

        output.acceptAll(stewItems, tabVisibility);
    }

    public static void register()
    {
        //Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ModCreativeInventory.MO_MILK_BUILDING_BLOCKS_TAB_KEY, ModCreativeInventory.MO_MILK_BUILDING_BLOCKS_TAB);
        //Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ModCreativeInventory.MO_MILK_NATURAL_BLOCKS_TAB_KEY, ModCreativeInventory.MO_MILK_NATURAL_BLOCKS_TAB);
        //Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ModCreativeInventory.MO_MILK_FUNCTIONAL_BLOCKS_TAB_KEY, ModCreativeInventory.MO_MILK_FUNCTIONAL_BLOCKS_TAB);
        //Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ModCreativeInventory.MO_MILK_REDSTONE_BLOCKS_TAB_KEY, ModCreativeInventory.MO_MILK_REDSTONE_BLOCKS_TAB);
        //Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ModCreativeInventory.MO_MILK_COMBAT_TAB_KEY, ModCreativeInventory.MO_MILK_COMBAT_TAB);
        //Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ModCreativeInventory.MO_MILK_FOOD_TAB_KEY, ModCreativeInventory.MO_MILK_FOOD_TAB);
        //Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ModCreativeInventory.MO_MILK_INGREDIENTS_TAB_KEY, ModCreativeInventory.MO_MILK_INGREDIENT_TAB);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ModCreativeInventory.MO_MILK_TAB_KEY, ModCreativeInventory.MO_MILK_TAB);
    }

}
