package com.momilk.block;

import com.momilk.MoMilk;
import com.momilk.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.PoweredBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.redstone.ExperimentalRedstoneUtils;
import net.minecraft.world.level.redstone.Orientation;
import org.jspecify.annotations.Nullable;

public class FallingPoweredBlock extends PoweredBlock implements Fallable {
    protected static final IntegerProperty POWER = BlockStateProperties.POWER;
    protected static final BooleanProperty LIT = BlockStateProperties.LIT;

    public FallingPoweredBlock(Properties properties, int power) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWER, power).setValue(LIT, true));
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.getValue(POWER);
    }

    @Override
    protected void onPlace(final BlockState state, final Level level, final BlockPos pos, final BlockState oldState, final boolean movedByPiston) {
        this.notifyNeighbors(level, pos, state);
        level.scheduleTick(pos, this, this.getDelayAfterPlace());
    }

    @Override
    protected BlockState updateShape(
            final BlockState state,
            final LevelReader level,
            final ScheduledTickAccess ticks,
            final BlockPos pos,
            final Direction directionToNeighbour,
            final BlockPos neighbourPos,
            final BlockState neighbourState,
            final RandomSource random
    ) {
        ticks.scheduleTick(pos, this, this.getDelayAfterPlace());
        return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    @Override
    protected boolean isSignalSource(final BlockState state) {
        return true;
    }

    @Override
    protected void neighborChanged(final BlockState state, final Level level, final BlockPos pos, final Block block, @Nullable final Orientation orientation, final boolean movedByPiston) {
        if ((Boolean)state.getValue(LIT) == this.hasNeighborSignal(level, pos) && !level.getBlockTicks().willTickThisTick(pos, this)) {
            level.scheduleTick(pos, this, 2);
        }
    }

    private void notifyNeighbors(final Level level, final BlockPos pos, final BlockState state) {
        Orientation orientation = this.randomOrientation(level, state);

        for (Direction direction : Direction.values()) {
            level.updateNeighborsAt(pos.relative(direction), this, ExperimentalRedstoneUtils.withFront(orientation, direction));
        }
    }

    @Nullable
    protected Orientation randomOrientation(final Level level, final BlockState state) {
        return ExperimentalRedstoneUtils.initialOrientation(level, null, Direction.UP);
    }

    @Override
    protected void affectNeighborsAfterRemoval(final BlockState state, final ServerLevel level, final BlockPos pos, final boolean movedByPiston) {
        if (!movedByPiston) {
            this.notifyNeighbors(level, pos, state);
        }
    }

    protected boolean hasNeighborSignal(final Level level, final BlockPos pos) {
        return false;
    }

    private int getNewPowerState(final Level level, final BlockPos pos)
    {
        return !hasNeighborSignal(level, pos) ? this.defaultBlockState().getValue(POWER) : 0;
    }

    @Override
    protected void tick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        if (isFree(level.getBlockState(pos.below())) && pos.getY() >= level.getMinY()) {
            FallingBlockEntity entity = FallingBlockEntity.fall(level, pos, state);
            this.falling(entity);
        }
        else
        {
            level.setBlock(pos, state.setValue(LIT, !hasNeighborSignal(level, pos)).setValue(POWER, getNewPowerState(level, pos)), 3);
        }
    }

    protected void falling(final FallingBlockEntity entity) {
    }

    protected int getDelayAfterPlace() {
        return 2;
    }

    public static boolean isFree(final BlockState state) {
        return state.isAir() || state.is(BlockTags.FIRE) || state.liquid() || state.canBeReplaced();
    }

    @Override
    public void animateTick(final BlockState state, final Level level, final BlockPos pos, final RandomSource random) {
        if (random.nextInt(16) == 0) {
            BlockPos below = pos.below();
            if (isFree(level.getBlockState(below))) {
                ParticleUtils.spawnParticleBelow(level, pos, random, new BlockParticleOption(ParticleTypes.FALLING_DUST, state));
            }
        }
    }

    public int getDustColor(BlockState blockState, BlockGetter level, BlockPos pos) {
        return 0;
    }
}
