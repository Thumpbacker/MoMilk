package com.momilk.dispenser_actions;

import com.momilk.MoMilk;
import com.momilk.item.ModItems;
import com.momilk.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.cow.AbstractCow;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;

public class MilkDispenseItemBehavior extends OptionalDispenseItemBehavior {


    public static boolean canMilk(final ServerLevel level, final BlockPos pos, final ItemStack dispensed) {
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, new AABB(pos), EntitySelector.NO_SPECTATORS)) {

            if (dispensed.is(Items.BUCKET) || dispensed.is(Items.GLASS_BOTTLE)) {
                //Return true if a mob is in the milked_by_dispensers tag
                return entity.is(ModTags.EntityTypes.MILKABLE_BY_DISPENSERS) && !entity.isBaby();
            }
        }

        return false;
    }

    public static ItemStack milkEntity(final ServerLevel level, final BlockPos pos, final ItemStack dispensed, BlockSource source) {

        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, new AABB(pos), EntitySelector.NO_SPECTATORS)) {

            if(entity.is(ModTags.EntityTypes.MILKABLE_BY_DISPENSERS))
            {
                if(dispensed.is(Items.BUCKET)) {

                    ItemStack stack = new ItemStack(Items.WATER_BUCKET);

                    //Get the milk item
                    if (entity.is(ModTags.EntityTypes.PROVIDES_MILK_FROM_DISPENSERS)) {
                        stack = new ItemStack(Items.MILK_BUCKET);
                    }
                    else if (entity.is(ModTags.EntityTypes.PROVIDES_HOG_MILK_FROM_DISPENSERS)) {
                        stack = new ItemStack(ModItems.HOG_MILK_BUCKET);
                    }
                    else if (entity.is(ModTags.EntityTypes.PROVIDES_SPOILED_HOG_MILK_FROM_DISPENSERS)) {
                        stack = new ItemStack(ModItems.SPOILED_HOG_MILK_BUCKET);
                    }

                    //Dispense it
                    dispensed.shrink(1);

                    if (dispensed.isEmpty()) {
                        return stack;
                    } else {
                        addToInventoryOrDispense(source, stack);
                        return dispensed;
                    }
                }
                else if(dispensed.is(Items.GLASS_BOTTLE))
                {
                    ItemStack stack = new ItemStack(Items.POTION);

                    //Get the milk item
                    if (entity.is(ModTags.EntityTypes.PROVIDES_MILK_FROM_DISPENSERS)) {
                        stack = new ItemStack(ModItems.MILK_BOTTLE);
                    }
                    else if (entity.is(ModTags.EntityTypes.PROVIDES_HOG_MILK_FROM_DISPENSERS)) {
                        stack = new ItemStack(ModItems.HOG_MILK_BOTTLE);
                    }
                    else if (entity.is(ModTags.EntityTypes.PROVIDES_SPOILED_HOG_MILK_FROM_DISPENSERS)) {
                        stack = new ItemStack(ModItems.SPOILED_HOG_MILK_BOTTLE);
                    }

                    //Dispense it
                    dispensed.shrink(1);

                    if (dispensed.isEmpty()) {
                        return stack;
                    } else {
                        addToInventoryOrDispense(source, stack);
                        return dispensed;
                    }
                }
            }
        }

        return dispensed;
    }

    private static void addToInventoryOrDispense(final BlockSource source, final ItemStack itemStack) {
        ItemStack remainder = source.blockEntity().insertItem(itemStack);
        if (!remainder.isEmpty()) {
            Direction direction = source.state().getValue(DispenserBlock.FACING);
            spawnItem(source.level(), remainder, 6, direction, DispenserBlock.getDispensePosition(source));
            playDefaultSound(source);
            playDefaultAnimation(source, direction);
        }
    }

    private static void playDefaultAnimation(final BlockSource source, final Direction direction) {
        source.level().levelEvent(2000, source.pos(), direction.get3DDataValue());
    }

    private static void playDefaultSound(final BlockSource source) {
        source.level().levelEvent(1000, source.pos(), 0);
    }
}
