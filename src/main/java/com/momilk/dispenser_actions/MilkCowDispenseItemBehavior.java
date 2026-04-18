package com.momilk.dispenser_actions;

import com.momilk.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.animal.cow.AbstractCow;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.entity.animal.cow.MushroomCow;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;

public class MilkCowDispenseItemBehavior extends OptionalDispenseItemBehavior {


    public static boolean canMilk(final ServerLevel level, final BlockPos pos, final ItemStack dispensed) {
        for (Entity entity : level.getEntitiesOfClass(Entity.class, new AABB(pos), EntitySelector.NO_SPECTATORS)) {

            if (dispensed.is(Items.BUCKET)) {
                //Milk cow/goat with bucket
                if (entity instanceof AbstractCow cow && !cow.isBaby() || entity instanceof Goat goat && !goat.isBaby()) {
                    return true;
                }
            } else if (dispensed.is(Items.GLASS_BOTTLE)) {
                //Milk cow/goat with bottle
                if (entity instanceof AbstractCow cow && !cow.isBaby() || entity instanceof Goat goat && !goat.isBaby()) {
                    return true;
                }
            }
            /*else if (dispensed.is(Items.BOWL)) {
                //Milk cow/goat with bottle
                if (entity instanceof MushroomCow mCow && !mCow.isBaby()) {
                    return true;
                }
            }*/
        }

        return false;
    }

    public static ItemStack milkEntity(final ServerLevel level, final BlockPos pos, final ItemStack dispensed, BlockSource source) {

        for (Entity entity : level.getEntitiesOfClass(Entity.class, new AABB(pos), EntitySelector.NO_SPECTATORS)) {

            if(dispensed.is(Items.BUCKET)) {
                //Milk cow/goat with bucket
                if (entity instanceof AbstractCow cow && !cow.isBaby() || entity instanceof Goat goat && !goat.isBaby()) {
                    dispensed.shrink(1);

                    if (dispensed.isEmpty()) {
                        return new ItemStack(Items.MILK_BUCKET);
                    } else {
                        addToInventoryOrDispense(source, new ItemStack(Items.MILK_BUCKET));
                        return dispensed;
                    }
                }
            }
            else if(dispensed.is(Items.GLASS_BOTTLE))
            {
                //Milk cow/goat with bottle
                if (entity instanceof AbstractCow cow && !cow.isBaby() || entity instanceof Goat goat && !goat.isBaby()) {
                    dispensed.shrink(1);

                    if (dispensed.isEmpty()) {
                        return new ItemStack(ModItems.MILK_BOTTLE);
                    } else {
                        addToInventoryOrDispense(source, new ItemStack(ModItems.MILK_BOTTLE));
                        return dispensed;
                    }
                }
            }
            /*else if(dispensed.is(Items.BOWL))
            {
                //Milk mooshroom with bowl
                if(entity instanceof MushroomCow mooshroom && !mooshroom.isBaby())
                {
                    if(mooshroom.getVariant() == MushroomCow.Variant.BROWN) {
                        dispensed.shrink(1);

                        if (dispensed.isEmpty()) {
                            var stew = new ItemStack(Items.SUSPICIOUS_STEW);

                            var xyz = mooshroom.getEntityData().getNonDefaultValues();


                            return stew;
                        } else {
                            var xyz = mooshroom.getEntityData().getNonDefaultValues();

                            addToInventoryOrDispense(source, new ItemStack(Items.SUSPICIOUS_STEW));
                            return dispensed;
                        }
                    }
                    else
                    {
                        dispensed.shrink(1);

                        if (dispensed.isEmpty()) {
                            return new ItemStack(Items.MUSHROOM_STEW);
                        } else {
                            addToInventoryOrDispense(source, new ItemStack(Items.MUSHROOM_STEW));
                            return dispensed;
                        }
                    }
                }
            }*/
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
