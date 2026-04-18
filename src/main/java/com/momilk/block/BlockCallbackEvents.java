package com.momilk.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.momilk.item.ModItems;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class BlockCallbackEvents {

    private static void getCinnamonFromStrippingWood()
    {
        UseBlockCallback.EVENT.register(((player, level, interactionHand, blockHitResult) ->
        {
            Random random = new Random();
            ItemStack heldItem = player.getItemInHand(interactionHand);
            BlockState block = level.getBlockState(blockHitResult.getBlockPos());

            if(heldItem.is(ItemTags.AXES))
            {
                if(block.is(BlockTags.LOGS_THAT_BURN) && !block.getBlock().getDescriptionId().contains("stripped") && random.nextInt(10) <= 3)
                {
                    ItemEntity entity = new ItemEntity(level, blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY(), blockHitResult.getBlockPos().getZ(), new ItemStack(ModItems.CINNAMON_STICK));
                    level.addFreshEntity(entity);
                    heldItem.setDamageValue(heldItem.getDamageValue() - 1);
                    return InteractionResult.PASS;
                }
                if(block.is(BlockTags.CRIMSON_STEMS) || block.is(BlockTags.WARPED_STEMS))
                {
                    if(!block.getBlock().getDescriptionId().contains("stripped") && random.nextInt(10) <= 3) {
                        ItemEntity entity = new ItemEntity(level, blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY(), blockHitResult.getBlockPos().getZ(), new ItemStack(ModItems.HYPHAE_STICK));
                        level.addFreshEntity(entity);
                        heldItem.setDamageValue(heldItem.getDamageValue() - 1);
                        return InteractionResult.PASS;
                    }
                }

                if(block.is(Blocks.BAMBOO_BLOCK))
                {
                    if(random.nextInt(10) <= 3) {
                        ItemEntity entity = new ItemEntity(level, blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY(), blockHitResult.getBlockPos().getZ(), new ItemStack(Items.BAMBOO));
                        level.addFreshEntity(entity);
                        heldItem.setDamageValue(heldItem.getDamageValue() - 1);
                        return InteractionResult.PASS;
                    }
                }

                if(block.is(ModBlocks.WRAPPED_BAMBOO_BLOCK))
                {
                    level.setBlockAndUpdate(blockHitResult.getBlockPos(), ModBlocks.STRIPPED_WRAPPED_BAMBOO_BLOCK.withPropertiesOf(block));
                    level.playSound(null, blockHitResult.getBlockPos(), SoundEvents.AXE_STRIP, SoundSource.BLOCKS);
                    if(random.nextInt(10) <= 3) {
                        ItemEntity entity = new ItemEntity(level, blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY(), blockHitResult.getBlockPos().getZ(), new ItemStack(Items.BAMBOO));
                        level.addFreshEntity(entity);
                        heldItem.setDamageValue(heldItem.getDamageValue() - 1);
                    }

                    return InteractionResult.SUCCESS;
                }
            }

            return InteractionResult.PASS;
        }));
    }

    public static void register()
    {
        getCinnamonFromStrippingWood();
    }
}
