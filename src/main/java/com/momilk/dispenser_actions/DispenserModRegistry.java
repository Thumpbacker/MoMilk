package com.momilk.dispenser_actions;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.momilk.block.ModBlocks;
import com.momilk.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.core.dispenser.ShulkerBoxDispenseBehavior;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.awt.*;
import java.util.function.Supplier;

public class DispenserModRegistry {

    static Supplier<BiMap<Block, Block>> SALTABLE_BLOCKS = Suppliers.memoize(
            () -> ImmutableBiMap.<Block, Block>builder()
                    .put(Blocks.TUBE_CORAL_BLOCK, ModBlocks.BRINY_TUBE_CORAL_BLOCK)
                    .put(Blocks.FIRE_CORAL_BLOCK, ModBlocks.BRINY_FIRE_CORAL_BLOCK)
                    .put(Blocks.BUBBLE_CORAL_BLOCK, ModBlocks.BRINY_BUBBLE_CORAL_BLOCK)
                    .put(Blocks.HORN_CORAL_BLOCK, ModBlocks.BRINY_HORN_CORAL_BLOCK)
                    .put(Blocks.BRAIN_CORAL_BLOCK, ModBlocks.BRINY_BRAIN_CORAL_BLOCK)
                    .put(Blocks.TUBE_CORAL_FAN, ModBlocks.BRINY_TUBE_CORAL_FAN)
                    .put(Blocks.BUBBLE_CORAL_FAN, ModBlocks.BRINY_BUBBLE_CORAL_FAN)
                    .put(Blocks.BRAIN_CORAL_FAN, ModBlocks.BRINY_BRAIN_CORAL_FAN)
                    .put(Blocks.FIRE_CORAL_FAN, ModBlocks.BRINY_FIRE_CORAL_FAN)
                    .put(Blocks.HORN_CORAL_FAN, ModBlocks.BRINY_HORN_CORAL_FAN)
                    .put(Blocks.TUBE_CORAL_WALL_FAN, ModBlocks.BRINY_TUBE_CORAL_WALL_FAN)
                    .put(Blocks.BUBBLE_CORAL_WALL_FAN, ModBlocks.BRINY_BUBBLE_CORAL_WALL_FAN)
                    .put(Blocks.BRAIN_CORAL_WALL_FAN, ModBlocks.BRINY_BRAIN_CORAL_WALL_FAN)
                    .put(Blocks.FIRE_CORAL_WALL_FAN, ModBlocks.BRINY_FIRE_CORAL_WALL_FAN)
                    .put(Blocks.HORN_CORAL_WALL_FAN, ModBlocks.BRINY_HORN_CORAL_WALL_FAN)
                    .put(Blocks.TUBE_CORAL, ModBlocks.BRINY_TUBE_CORAL)
                    .put(Blocks.BRAIN_CORAL, ModBlocks.BRINY_BRAIN_CORAL)
                    .put(Blocks.BUBBLE_CORAL, ModBlocks.BRINY_BUBBLE_CORAL)
                    .put(Blocks.FIRE_CORAL, ModBlocks.BRINY_FIRE_CORAL)
                    .put(Blocks.HORN_CORAL, ModBlocks.BRINY_HORN_CORAL)
                    .put(Blocks.CLOSED_EYEBLOSSOM, ModBlocks.BRINY_CLOSED_EYEBLOSSOM)
                    .put(Blocks.OPEN_EYEBLOSSOM, ModBlocks.BRINY_OPEN_EYEBLOSSOM)
                    .put(Blocks.POTTED_CLOSED_EYEBLOSSOM, ModBlocks.POTTED_BRINY_CLOSED_EYEBLOSSOM)
                    .put(Blocks.POTTED_OPEN_EYEBLOSSOM, ModBlocks.POTTED_BRINY_OPEN_EYEBLOSSOM)
                    .build()
    );

    static Supplier<BiMap<Block, Block>> CIDER_TRANSFORM_BLOCKS = Suppliers.memoize(
            () -> ImmutableBiMap.<Block, Block>builder()
                    .put(ModBlocks.BRINY_TUBE_CORAL_BLOCK, Blocks.TUBE_CORAL_BLOCK)
                    .put(ModBlocks.BRINY_FIRE_CORAL_BLOCK, Blocks.FIRE_CORAL_BLOCK)
                    .put(ModBlocks.BRINY_BUBBLE_CORAL_BLOCK, Blocks.BUBBLE_CORAL_BLOCK)
                    .put(ModBlocks.BRINY_HORN_CORAL_BLOCK, Blocks.HORN_CORAL_BLOCK)
                    .put(ModBlocks.BRINY_BRAIN_CORAL_BLOCK, Blocks.BRAIN_CORAL_BLOCK)
                    .put(ModBlocks.BRINY_TUBE_CORAL_FAN, Blocks.TUBE_CORAL_FAN)
                    .put(ModBlocks.BRINY_BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_FAN)
                    .put(ModBlocks.BRINY_BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_FAN)
                    .put(ModBlocks.BRINY_FIRE_CORAL_FAN, Blocks.FIRE_CORAL_FAN)
                    .put(ModBlocks.BRINY_HORN_CORAL_FAN, Blocks.HORN_CORAL_FAN)
                    .put(ModBlocks.BRINY_TUBE_CORAL_WALL_FAN, Blocks.TUBE_CORAL_WALL_FAN)
                    .put(ModBlocks.BRINY_BUBBLE_CORAL_WALL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN)
                    .put(ModBlocks.BRINY_BRAIN_CORAL_WALL_FAN, Blocks.BRAIN_CORAL_WALL_FAN)
                    .put(ModBlocks.BRINY_FIRE_CORAL_WALL_FAN, Blocks.FIRE_CORAL_WALL_FAN)
                    .put(ModBlocks.BRINY_HORN_CORAL_WALL_FAN, Blocks.HORN_CORAL_WALL_FAN)
                    .put(ModBlocks.BRINY_TUBE_CORAL, Blocks.TUBE_CORAL)
                    .put(ModBlocks.BRINY_BRAIN_CORAL, Blocks.BRAIN_CORAL)
                    .put(ModBlocks.BRINY_BUBBLE_CORAL, Blocks.BUBBLE_CORAL)
                    .put(ModBlocks.BRINY_FIRE_CORAL, Blocks.FIRE_CORAL)
                    .put(ModBlocks.BRINY_HORN_CORAL, Blocks.HORN_CORAL)
                    .put(ModBlocks.BRINY_CLOSED_EYEBLOSSOM, Blocks.CLOSED_EYEBLOSSOM)
                    .put(ModBlocks.BRINY_OPEN_EYEBLOSSOM, Blocks.OPEN_EYEBLOSSOM)
                    .put(ModBlocks.POTTED_BRINY_CLOSED_EYEBLOSSOM, Blocks.POTTED_CLOSED_EYEBLOSSOM)
                    .put(ModBlocks.POTTED_BRINY_OPEN_EYEBLOSSOM, Blocks.POTTED_OPEN_EYEBLOSSOM)
                    .put(Blocks.INFESTED_COBBLESTONE, Blocks.COBBLESTONE)
                    .put(Blocks.INFESTED_DEEPSLATE, Blocks.DEEPSLATE)
                    .put(Blocks.INFESTED_STONE, Blocks.STONE)
                    .put(Blocks.INFESTED_STONE_BRICKS, Blocks.STONE_BRICKS)
                    .put(Blocks.INFESTED_CHISELED_STONE_BRICKS, Blocks.CHISELED_STONE_BRICKS)
                    .put(Blocks.INFESTED_CRACKED_STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS)
                    .put(Blocks.INFESTED_MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS)
                    .build()
    );

    //Register the dispenser actions
    public static void register()
    {
        DispenserBlock.registerProjectileBehavior(ModItems.MILK_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItems.SPOILED_MILK_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItems.CHOCOLATE_MILK_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItems.CEREAL_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItems.HOT_CHOCOLATE_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItems.SPOILED_CEREAL_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItems.HOG_MILK_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItems.SPOILED_HOG_MILK_ARROW);

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
                else if(MilkDispenseItemBehavior.canMilk(source.level(), target, dispensed))
                {
                    return MilkDispenseItemBehavior.milkEntity(source.level(), target, dispensed, source);
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
                        else if(MilkDispenseItemBehavior.canMilk(level, target, dispensed))
                        {
                            return MilkDispenseItemBehavior.milkEntity(level, target, dispensed, source);
                        }
                        else {
                            return super.execute(source, dispensed);
                        }
                    }
                }
        );

        DispenserBlock.registerBehavior(ModItems.SALT, new OptionalDispenseItemBehavior() {

                    @Override
                    public ItemStack execute(final BlockSource source, final ItemStack dispensed) {
                        this.setSuccess(false);
                        ServerLevel level = source.level();
                        BlockPos target = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                        BlockState state = level.getBlockState(target);
                        if (SALTABLE_BLOCKS.get().containsKey(state.getBlock()))
                        {
                            level.setBlockAndUpdate(target, SALTABLE_BLOCKS.get().get(state.getBlock()).defaultBlockState());
                            level.playSound(null, target, SoundEvents.SAND_BREAK, SoundSource.BLOCKS);
                            DustParticleOptions dpo = new DustParticleOptions(Color.pink.getRGB(), 3f);
                            level.addParticle(dpo, target.getX(), target.getY(), target.getZ(), 0, 0, 0);;
                            this.setSuccess(true);
                            return dispensed;
                        }
                        else {
                            return new ShulkerBoxDispenseBehavior().dispense(source, dispensed);
                        }
                    }
                }
        );

        DispenserBlock.registerBehavior(ModItems.APPLE_CIDER, new OptionalDispenseItemBehavior() {

                    @Override
                    public ItemStack execute(final BlockSource source, final ItemStack dispensed) {
                        this.setSuccess(false);
                        ServerLevel level = source.level();
                        BlockPos target = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                        BlockState state = level.getBlockState(target);
                        if (CIDER_TRANSFORM_BLOCKS.get().containsKey(state.getBlock()))
                        {
                            level.setBlockAndUpdate(target, CIDER_TRANSFORM_BLOCKS.get().get(state.getBlock()).withPropertiesOf(state));
                            level.playSound(null, target, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS);
                            level.sendParticles(ParticleTypes.SPLASH, target.getX() + level.getRandom().nextDouble(), target.getY() + 1, target.getZ() + level.getRandom().nextDouble(), 1, 0.0, 0.0, 0.0, 1.0);
                            this.setSuccess(true);
                            return consumeWithRemainder(source, dispensed, new ItemStack(Items.GLASS_BOTTLE));
                        }
                        else {
                            return super.execute(source, dispensed);
                        }
                    }
                }
        );

        DispenserBlock.registerBehavior(ModBlocks.SALT_BLOCK, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.MEDIUM_SALT_BLOCK, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.LARGE_SALT_BLOCK, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.SALT_LAMP, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.MEDIUM_SALT_LAMP, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.LARGE_SALT_LAMP, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.SOUL_SALT_LAMP, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.MEDIUM_SOUL_SALT_LAMP, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.LARGE_SOUL_SALT_LAMP, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.REDSTONE_SALT_LAMP, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.MEDIUM_REDSTONE_SALT_LAMP, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.LARGE_REDSTONE_SALT_LAMP, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.COPPER_SALT_LAMP, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.MEDIUM_COPPER_SALT_LAMP, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.LARGE_COPPER_SALT_LAMP, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(Blocks.ANVIL, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(Blocks.DAMAGED_ANVIL, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(Blocks.CHIPPED_ANVIL, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(Blocks.SAND, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(Blocks.RED_SAND, new ShulkerBoxDispenseBehavior());
        Items.CONCRETE_POWDER.forEach((item) -> DispenserBlock.registerBehavior(item, new ShulkerBoxDispenseBehavior()));
        DispenserBlock.registerBehavior(Blocks.GRAVEL, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(Blocks.SUSPICIOUS_GRAVEL, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(Blocks.SUSPICIOUS_SAND, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(Blocks.DRAGON_EGG, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModItems.REJUVENATING_SALT, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.SALT_SLAB, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.SALT_STAIRS, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModBlocks.SALT_WALL, new ShulkerBoxDispenseBehavior());
        DispenserBlock.registerBehavior(ModItems.GLOWING_SALT, new ShulkerBoxDispenseBehavior());
    }

}
