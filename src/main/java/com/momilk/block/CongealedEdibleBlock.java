package com.momilk.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class CongealedEdibleBlock extends CongealedMilkBlock{
    private Block blockToChangeInto;
    public CongealedEdibleBlock(Properties properties, Block blockToChangeInto) {
        super(properties);
        this.blockToChangeInto = blockToChangeInto;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(player.getActiveItem().getItem() instanceof BlockItem || !player.canEat(false)) {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
        else
        {
            level.setBlockAndUpdate(pos, getReturnBlock().defaultBlockState());
            player.getFoodData().eat(6, 0.2F);
            level.playSound(player, pos, SoundEvents.PLAYER_BURP, SoundSource.PLAYERS);
            level.gameEvent(player, GameEvent.EAT, pos);
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected int getAnalogOutputSignal(final BlockState state, final Level level, final BlockPos pos, final Direction direction) {
        return 15;
    }

    @Override
    protected boolean hasAnalogOutputSignal(final BlockState state) {
        return true;
    }

    public Block getReturnBlock()
    {
        return blockToChangeInto;
    }
}
