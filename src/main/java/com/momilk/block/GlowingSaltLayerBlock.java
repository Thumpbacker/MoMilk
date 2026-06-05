package com.momilk.block;

import com.momilk.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.Consumer;
import java.util.function.ToIntFunction;

public class GlowingSaltLayerBlock extends SaltLayerBlock {

    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL;
    public static final ToIntFunction<BlockState> LIGHT_EMISSION = state -> (Integer)state.getValue(LEVEL);
    public static final int MAX_LEVEL = 15;
    public static final int MIN_LEVEL = 0;

    public GlowingSaltLayerBlock(Properties properties, Boolean floating, Boolean harmful) {
        super(properties, floating, harmful);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(LEVEL, 15));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if(itemStack.is(ModTags.Items.INCREASES_GLOWING_SALT_LIGHT_LEVEL) && state.getValue(LEVEL) < MAX_LEVEL)
        {
            int newLightLevel = state.getValue(LEVEL) + 1;
            level.setBlock(pos, state.setValue(LEVEL, newLightLevel), 2);
            itemStack.consume(1, player);
            return InteractionResult.SUCCESS;
        }
        else if(itemStack.is(ModTags.Items.DECREASES_GLOWING_SALT_LIGHT_LEVEL) && state.getValue(LEVEL) > MIN_LEVEL)
        {
            int newLightLevel = state.getValue(LEVEL) - 1;
            level.setBlock(pos, state.setValue(LEVEL, newLightLevel), 2);
            itemStack.consume(1, player);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        FluidState replacedFluidState = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, replacedFluidState.is(Fluids.WATER)).setValue(LEVEL, MAX_LEVEL);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
        builder.add(LEVEL);
    }
}
