package com.momilk.ui;

import com.momilk.MoMilk;
import com.momilk.block.ModBlocks;
import com.momilk.effects.ModEffects;
import com.momilk.effects.ModPotions;
import com.momilk.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
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
    public static final CreativeModeTab MO_MILK_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.CHOCOLATE_MILK_BUCKET))
            .title(Component.translatable("itemGroup.momilk"))
            .displayItems((params, output) -> {
                //Items
                output.accept(ModItems.MILK_BOTTLE);
                output.accept(ModItems.CHOCOLATE_MILK_BOTTLE);
                output.accept(ModItems.SPOILED_MILK_BOTTLE);
                output.accept(ModItems.CHOCOLATE_MILK_BUCKET);
                output.accept(ModItems.SPOILED_MILK_BUCKET);
                output.accept(ModItems.MILK_ARROW);
                output.accept(ModItems.CHOCOLATE_MILK_ARROW);
                output.accept(ModItems.SPOILED_MILK_ARROW);
                output.accept(ModItems.APPLE_CIDER);
                output.accept(ModItems.CINNAMON_STICK);
                output.accept(ModItems.HYPHAE_STICK);
                output.accept(ModItems.SALT);
                output.accept(ModItems.FLOATING_SALT);
                output.accept(ModItems.REJUVENATING_SALT);
                ItemStack stack = new ItemStack(ModItems.SUSPICIOUS_MILK);
                stack.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, SuspiciousEffectHolder.getAllEffectHolders().getFirst().getSuspiciousEffects());
                output.accept(stack);
                //Blocks
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

}
