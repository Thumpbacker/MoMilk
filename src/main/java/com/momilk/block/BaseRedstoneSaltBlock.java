package com.momilk.block;

import com.momilk.MoMilk;
import com.momilk.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BaseRedstoneSaltBlock extends FallingPoweredBlock implements BrushableSaltBlock{

    public BaseRedstoneSaltBlock(Properties properties, int power, int saltInBlock) {
        super(properties, power);
        this.registerDefaultState(this.stateDefinition.any().setValue(SALT_IN_BLOCK, saltInBlock).setValue(POWER, power).setValue(LIT, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SALT_IN_BLOCK);
        builder.add(LIT);
        builder.add(POWER);
    }

    @Override
    protected boolean hasNeighborSignal(final Level level, final BlockPos pos) {
        return hasSignalSource(level, pos);
    }

    private boolean hasSignalSource(Level level, BlockPos pos)
    {
        return level.hasSignal(pos.below(), Direction.DOWN) && !level.getBlockState(pos.below()).is(ModTags.Blocks.PREVENTS_DEPOWERING_REDSTONE_SALT_LAMPS) || level.hasSignal(pos.above(), Direction.UP) && !level.getBlockState(pos.above()).is(ModTags.Blocks.PREVENTS_DEPOWERING_REDSTONE_SALT_LAMPS)  || level.hasSignal(pos.north(), Direction.NORTH) && !level.getBlockState(pos.north()).is(ModTags.Blocks.PREVENTS_DEPOWERING_REDSTONE_SALT_LAMPS) || level.hasSignal(pos.east(), Direction.EAST) && !level.getBlockState(pos.east()).is(ModTags.Blocks.PREVENTS_DEPOWERING_REDSTONE_SALT_LAMPS) || level.hasSignal(pos.west(), Direction.WEST) && !level.getBlockState(pos.west()).is(ModTags.Blocks.PREVENTS_DEPOWERING_REDSTONE_SALT_LAMPS) || level.hasSignal(pos.south(), Direction.SOUTH) && !level.getBlockState(pos.south()).is(ModTags.Blocks.PREVENTS_DEPOWERING_REDSTONE_SALT_LAMPS);
    }

    @Override
    public BlockState getBrushedIntoBlockState(BlockState blockState) {
        return BRUSHABLE_SALT_BLOCKS.get().get(blockState.getBlock()).defaultBlockState();
    }
}
