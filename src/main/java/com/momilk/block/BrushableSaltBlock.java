package com.momilk.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.momilk.util.ModLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Random;
import java.util.function.Supplier;

public interface BrushableSaltBlock {
    static final IntegerProperty SALT_IN_BLOCK = ModBlockProperties.SALT_IN_BLOCK;

    static Supplier<BiMap<Block, Block>> BRUSHABLE_SALT_BLOCKS = Suppliers.memoize(
            () -> ImmutableBiMap.<Block, Block>builder()
                    .put(ModBlocks.LARGE_SALT_BLOCK, ModBlocks.MEDIUM_SALT_BLOCK)
                    .put(ModBlocks.MEDIUM_SALT_BLOCK, ModBlocks.SALT_BLOCK)
                    .put(ModBlocks.LARGE_SALT_LAMP, ModBlocks.MEDIUM_SALT_LAMP)
                    .put(ModBlocks.MEDIUM_SALT_LAMP, ModBlocks.SALT_LAMP)
                    .put(ModBlocks.LARGE_SOUL_SALT_LAMP, ModBlocks.MEDIUM_SOUL_SALT_LAMP)
                    .put(ModBlocks.MEDIUM_SOUL_SALT_LAMP, ModBlocks.SOUL_SALT_LAMP)
                    .put(ModBlocks.LARGE_COPPER_SALT_LAMP, ModBlocks.MEDIUM_COPPER_SALT_LAMP)
                    .put(ModBlocks.MEDIUM_COPPER_SALT_LAMP, ModBlocks.COPPER_SALT_LAMP)
                    .put(ModBlocks.LARGE_REDSTONE_SALT_LAMP, ModBlocks.MEDIUM_REDSTONE_SALT_LAMP)
                    .put(ModBlocks.MEDIUM_REDSTONE_SALT_LAMP, ModBlocks.REDSTONE_SALT_LAMP)
                    .put(ModBlocks.SALT_BLOCK, ModBlocks.SALT_LAYER)
                    .put(ModBlocks.COPPER_SALT_LAMP, Blocks.COPPER_TORCH)
                    .put(ModBlocks.SOUL_SALT_LAMP, Blocks.SOUL_TORCH)
                    .put(ModBlocks.REDSTONE_SALT_LAMP, Blocks.REDSTONE_TORCH)
                    .put(ModBlocks.SALT_LAMP, Blocks.TORCH)
                    .build()
    );

    static Supplier<BiMap<Block, ResourceKey<LootTable>>> SALT_BLOCK_LOOT_TABLES = Suppliers.memoize(
            () -> ImmutableBiMap.<Block, ResourceKey<LootTable>>builder()
                    .put(ModBlocks.LARGE_SALT_BLOCK, ModLootTables.BRUSH_LARGE_SALT_BLOCK)
                    .put(ModBlocks.MEDIUM_SALT_BLOCK, ModLootTables.BRUSH_MEDIUM_SALT_BLOCK)
                    .put(ModBlocks.LARGE_SALT_LAMP, ModLootTables.BRUSH_LARGE_SALT_LAMP)
                    .put(ModBlocks.MEDIUM_SALT_LAMP, ModLootTables.BRUSH_MEDIUM_SALT_LAMP)
                    .put(ModBlocks.LARGE_COPPER_SALT_LAMP, ModLootTables.BRUSH_LARGE_COPPER_SALT_LAMP)
                    .put(ModBlocks.MEDIUM_COPPER_SALT_LAMP, ModLootTables.BRUSH_MEDIUM_COPPER_SALT_LAMP)
                    .put(ModBlocks.LARGE_SOUL_SALT_LAMP, ModLootTables.BRUSH_LARGE_SOUL_SALT_LAMP)
                    .put(ModBlocks.MEDIUM_SOUL_SALT_LAMP, ModLootTables.BRUSH_MEDIUM_SOUL_SALT_LAMP)
                    .put(ModBlocks.LARGE_REDSTONE_SALT_LAMP, ModLootTables.BRUSH_LARGE_REDSTONE_SALT_LAMP)
                    .put(ModBlocks.MEDIUM_REDSTONE_SALT_LAMP, ModLootTables.BRUSH_MEDIUM_REDSTONE_SALT_LAMP)
                    .put(ModBlocks.SALT_LAMP, ModLootTables.BRUSH_SALT_LAMP)
                    .put(ModBlocks.SOUL_SALT_LAMP, ModLootTables.BRUSH_SOUL_SALT_LAMP)
                    .put(ModBlocks.REDSTONE_SALT_LAMP, ModLootTables.BRUSH_REDSTONE_SALT_LAMP)
                    .put(ModBlocks.COPPER_SALT_LAMP, ModLootTables.BRUSH_COPPER_SALT_LAMP)
                    .put(ModBlocks.SALT_BLOCK, ModLootTables.BRUSH_SALT_BLOCK)
                    .build()
    );

    default boolean brushed(BlockState state, Level level, BlockPos pos)
    {
        Random random = new Random();
        int newSaltLevel = state.getValue(SALT_IN_BLOCK) - 1;
        if(random.nextInt(50) == 0 && BRUSHABLE_SALT_BLOCKS.get().containsKey(state.getBlock()) && !level.isClientSide())
        {
            level.scheduleTick(pos, state.getBlock(), 2);
            if(newSaltLevel > 0) {
                BlockState newState = state.setValue(ModBlockProperties.SALT_IN_BLOCK, newSaltLevel);
                level.setBlock(pos, newState, 3);
            }
            else
            {
                level.setBlock(pos, getBrushedIntoBlockState(state), 3);
            }
            return true;
        }

        return false;
    }

    BlockState getBrushedIntoBlockState(BlockState blockState);

    default ResourceKey<LootTable> getLootTable(BlockState state)
    {
        if(SALT_BLOCK_LOOT_TABLES.get().containsKey(state.getBlock()))
        {
            return SALT_BLOCK_LOOT_TABLES.get().get(state.getBlock());
        }

        return null;
    }
}
