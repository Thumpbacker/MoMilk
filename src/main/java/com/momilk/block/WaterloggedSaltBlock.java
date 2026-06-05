package com.momilk.block;

import com.momilk.MoMilk;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class WaterloggedSaltBlock extends BaseSaltBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final VoxelShape SHAPE;
    private final Block blockToTurnWhileWaterlogged;

    public WaterloggedSaltBlock(ColorRGBA dustColor, int saltInBlock, Properties properties, VoxelShape shape, Block blockToTurnWhileWaterlogged) {
        super(dustColor, saltInBlock, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(SALT_IN_BLOCK, saltInBlock));
        SHAPE = shape;
        this.blockToTurnWhileWaterlogged = blockToTurnWhileWaterlogged;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
        builder.add(SALT_IN_BLOCK);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(state.getValue(WATERLOGGED))
        {
            level.setBlockAndUpdate(pos, blockToTurnWhileWaterlogged.defaultBlockState());
        }
    }

    @Override
    protected BlockState updateShape(final BlockState state, final LevelReader level, final ScheduledTickAccess ticks, final BlockPos pos, final Direction directionToNeighbour, final BlockPos neighbourPos, final BlockState neighbourState, final RandomSource random) {
        if ((Boolean)state.getValue(WATERLOGGED)) {
            ticks.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    @Override
    protected FluidState getFluidState(final BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        FluidState replacedFluidState = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, replacedFluidState.is(Fluids.WATER));
    }

    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockState getBrushedIntoBlockState(BlockState blockState) {
        if(BRUSHABLE_SALT_BLOCKS.get().get(blockState.getBlock()).defaultBlockState().hasProperty(WATERLOGGED)) {
            return BRUSHABLE_SALT_BLOCKS.get().get(blockState.getBlock()).defaultBlockState().setValue(WATERLOGGED, blockState.getValue(WATERLOGGED));
        }
        else
        {
            return BRUSHABLE_SALT_BLOCKS.get().get(blockState.getBlock()).defaultBlockState();
        }
    }
}
