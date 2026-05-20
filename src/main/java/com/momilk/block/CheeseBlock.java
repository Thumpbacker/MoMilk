package com.momilk.block;

import com.mojang.serialization.MapCodec;
import com.momilk.MoMilk;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CheeseBlock extends HorizontalDirectionalBlock {
    public static final IntegerProperty CHEESE_BITES = ModBlockProperties.CHEESE_BITES;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    public static final BooleanProperty CHEESE_AGES = ModBlockProperties.CHEESE_AGES;

    protected CheeseBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(CHEESE_BITES, 0));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }


    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return Block.column(13.0, 0.0, 4.0);
    }


    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int age = (Integer)state.getValue(AGE);
        boolean canAge = state.getValue(CHEESE_AGES);
        if (random.nextInt(5) == 0 && age < 5 && canAge)
        {
            level.setBlock(pos, state.setValue(AGE, age + 1), 3);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            int age = (Integer)state.getValue(AGE);
            player.getFoodData().eat(8 + age, 0.8F);
            int bites = (Integer)state.getValue(CHEESE_BITES);
            level.playSound(player, pos, SoundEvents.PLAYER_BURP, SoundSource.PLAYERS);
            level.gameEvent(player, GameEvent.EAT, pos);
            if (bites < 3) {
                level.setBlock(pos, state.setValue(CHEESE_BITES, bites + 1), 3);
            } else {
                level.removeBlock(pos, false);
                level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if(itemStack.is(Items.GOLDEN_DANDELION))
        {
            var canAge = state.getValue(CHEESE_AGES);
            level.setBlock(pos, state.setValue(CHEESE_AGES, !canAge), 3);
            level.playSound(player, pos, SoundEvents.GOLDEN_DANDELION_UNUSE, SoundSource.BLOCKS);
            itemStack.consume(1, player);
            level.playSound(null, pos, canAge ? SoundEvents.GOLDEN_DANDELION_USE : SoundEvents.GOLDEN_DANDELION_UNUSE, SoundSource.PLAYERS, 1.0F, 1.0F);
            level.addParticle(canAge ? ParticleTypes.PAUSE_MOB_GROWTH : ParticleTypes.RESET_MOB_GROWTH, pos.getX(), pos.getY(), pos.getZ(), 0.0, 0.0, 0.0);
            return InteractionResult.SUCCESS;
        }

        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected BlockState updateShape(final BlockState state, final LevelReader level, final ScheduledTickAccess ticks, final BlockPos pos, final Direction directionToNeighbour, final BlockPos neighbourPos, final BlockState neighbourState, final RandomSource random) {
        return directionToNeighbour == Direction.DOWN && !state.canSurvive(level, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    @Override
    protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }

    @Override
    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(CHEESE_BITES);
        builder.add(AGE);
        builder.add(CHEESE_AGES);
    }

    @Override
    protected int getAnalogOutputSignal(final BlockState state, final Level level, final BlockPos pos, final Direction direction) {
        return getOutputSignal((Integer)state.getValue(CHEESE_BITES), (Integer)state.getValue(AGE));
    }

    public static int getOutputSignal(final int bitesTaken, final int age) {
        return ((5 - bitesTaken) + (age - 1)) * 2;
    }

    @Override
    protected boolean hasAnalogOutputSignal(final BlockState state) {
        return true;
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return null;
    }
}
