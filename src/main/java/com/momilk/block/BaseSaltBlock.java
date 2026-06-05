package com.momilk.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.momilk.MoMilk;
import com.momilk.util.ModLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Random;
import java.util.function.Supplier;

public class BaseSaltBlock extends ColoredFallingBlock implements BrushableSaltBlock {

    public BaseSaltBlock(ColorRGBA dustColor, int saltInBlock, Properties properties) {
        super(dustColor, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(SALT_IN_BLOCK, saltInBlock));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SALT_IN_BLOCK);
    }

    @Override
    public BlockState getBrushedIntoBlockState(BlockState blockState) {
        return BRUSHABLE_SALT_BLOCKS.get().get(blockState.getBlock()).defaultBlockState();
    }
}
