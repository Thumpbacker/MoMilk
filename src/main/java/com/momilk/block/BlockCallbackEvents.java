package com.momilk.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.momilk.MoMilk;
import com.momilk.item.ModItems;
import com.momilk.util.ModLootTables;
import com.momilk.util.ModTags;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

public class BlockCallbackEvents {

    private void getCinnamonFromStrippingWood()
    {
        UseBlockCallback.EVENT.register(((player, level, interactionHand, blockHitResult) ->
        {
            Random random = new Random();
            ItemStack heldItem = player.getItemInHand(interactionHand);
            BlockState block = level.getBlockState(blockHitResult.getBlockPos());

            if(heldItem.is(ItemTags.AXES))
            {
                if(block.is(ModTags.Blocks.STRIP_FOR_CINNAMON_STICKS) && random.nextInt(10) <= 3)
                {
                    return dropStickItem(level, blockHitResult, heldItem, ModLootTables.STRIP_CINNAMON_STICK, block);
                }
                if(block.is(ModTags.Blocks.STRIP_FOR_HYPHAE_STICKS) && random.nextInt(10) <= 3)
                {

                    return dropStickItem(level, blockHitResult, heldItem, ModLootTables.STRIP_HYPHAE_STICK, block);
                }

                if(block.is(ModTags.Blocks.STRIP_FOR_BAMBOO))
                {
                    if(block.is(ModBlocks.WRAPPED_BAMBOO_BLOCK))
                    {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), ModBlocks.STRIPPED_WRAPPED_BAMBOO_BLOCK.withPropertiesOf(block));
                        level.playSound(null, blockHitResult.getBlockPos(), SoundEvents.AXE_STRIP, SoundSource.BLOCKS);
                        if(random.nextInt(10) <= 3) {

                            return dropStickItem(level, blockHitResult, heldItem, ModLootTables.STRIP_BAMBOO, block);
                        }
                        return InteractionResult.SUCCESS;
                    }

                    if(random.nextInt(10) <= 3) {

                        return dropStickItem(level, blockHitResult, heldItem, ModLootTables.STRIP_BAMBOO, block);
                    }
                }
            }

            return InteractionResult.PASS;
        }));
    }

    private InteractionResult dropStickItem(Level level, BlockHitResult blockHitResult, ItemStack heldItem, ResourceKey<LootTable> key, BlockState state)
    {
        ItemEntity entity = new ItemEntity(level, blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY(), blockHitResult.getBlockPos().getZ(), ModLootTables.getItemFromLootTable(key, level, heldItem, state, blockHitResult.getLocation()));
        level.addFreshEntity(entity);
        heldItem.setDamageValue(heldItem.getDamageValue() - 1);
        return InteractionResult.PASS;
    }

    public static void register()
    {
        BlockCallbackEvents bce = new BlockCallbackEvents();
        bce.getCinnamonFromStrippingWood();
    }
}
