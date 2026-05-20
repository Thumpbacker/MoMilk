package com.momilk.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CongealedMilkBlock extends Block {
    private static final VoxelShape SHAPE = Block.column(16.0, 0.0, 12.0);

    public CongealedMilkBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected VoxelShape getBlockSupportShape(final BlockState state, final BlockGetter level, final BlockPos pos) {
        return Shapes.block();
    }

    @Override
    protected VoxelShape getVisualShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return Shapes.block();
    }

    @Override
    protected boolean isPathfindable(final BlockState state, final PathComputationType type) {
        return false;
    }
}
