package com.momilk.dispenser_actions;

import com.momilk.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class DispenserModRegistry {

    //Register the dispenser actions
    public static void register()
    {
        DispenserBlock.registerProjectileBehavior(ModItems.MILK_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItems.SPOILED_MILK_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItems.CHOCOLATE_MILK_ARROW);

        DispenserBlock.registerBehavior(Items.BUCKET, new DefaultDispenseItemBehavior() {
            @Override
            public ItemStack execute(final BlockSource source, final ItemStack dispensed) {
                LevelAccessor level = source.level();
                BlockPos target = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                BlockState blockState = level.getBlockState(target);
                if (blockState.getBlock() instanceof BucketPickup bucket) {
                    ItemStack pickup = bucket.pickupBlock(null, level, target, blockState);
                    if (pickup.isEmpty()) {
                        return super.execute(source, dispensed);
                    } else {
                        level.gameEvent(null, GameEvent.FLUID_PICKUP, target);
                        Item targetType = pickup.getItem();
                        return this.consumeWithRemainder(source, dispensed, new ItemStack(targetType));
                    }
                }
                else if(MilkCowDispenseItemBehavior.canMilk(source.level(), target, dispensed))
                {
                    return MilkCowDispenseItemBehavior.milkEntity(source.level(), target, dispensed, source);
                }
                else {
                    return super.execute(source, dispensed);
                }
            }
        });

        DispenserBlock.registerBehavior(
                Items.GLASS_BOTTLE,
                new OptionalDispenseItemBehavior() {
                    private ItemStack takeLiquid(final BlockSource source, final ItemStack dispensed, final ItemStack filledItemStack) {
                        source.level().gameEvent(null, GameEvent.FLUID_PICKUP, source.pos());
                        return this.consumeWithRemainder(source, dispensed, filledItemStack);
                    }

                    @Override
                    public ItemStack execute(final BlockSource source, final ItemStack dispensed) {
                        this.setSuccess(false);
                        ServerLevel level = source.level();
                        BlockPos target = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                        BlockState state = level.getBlockState(target);
                        if (state.is(BlockTags.BEEHIVES, s -> s.hasProperty(BeehiveBlock.HONEY_LEVEL) && s.getBlock() instanceof BeehiveBlock)
                                && (Integer)state.getValue(BeehiveBlock.HONEY_LEVEL) >= 5) {
                            ((BeehiveBlock)state.getBlock()).releaseBeesAndResetHoneyLevel(level, state, target, null, BeehiveBlockEntity.BeeReleaseStatus.BEE_RELEASED);
                            this.setSuccess(true);
                            return this.takeLiquid(source, dispensed, new ItemStack(Items.HONEY_BOTTLE));
                        } else if (level.getFluidState(target).is(FluidTags.WATER)) {
                            this.setSuccess(true);
                            return this.takeLiquid(source, dispensed, PotionContents.createItemStack(Items.POTION, Potions.WATER));
                        }
                        else if(MilkCowDispenseItemBehavior.canMilk(level, target, dispensed))
                        {
                            return MilkCowDispenseItemBehavior.milkEntity(level, target, dispensed, source);
                        }
                        else {
                            return super.execute(source, dispensed);
                        }
                    }
                }
        );

        /*DispenserBlock.registerBehavior(Items.BOWL, new OptionalDispenseItemBehavior() {

            @Override
            public ItemStack execute(final BlockSource source, final ItemStack dispensed) {
                this.setSuccess(false);
                ServerLevel level = source.level();
                BlockPos target = source.pos().relative(source.state().getValue(DispenserBlock.FACING));

                if(MilkCowDispenseItemBehavior.canMilk(level, target, dispensed))
                {
                    this.setSuccess(true);
                    return MilkCowDispenseItemBehavior.milkEntity(level, target, dispensed, source);
                }
                else {
                    return super.execute(source, dispensed);
                }
            }
        });*/
    }

}
