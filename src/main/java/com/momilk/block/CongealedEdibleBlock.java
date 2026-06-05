package com.momilk.block;

import com.momilk.MoMilk;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class CongealedEdibleBlock extends CongealedMilkBlock{
    private Block blockToChangeInto;
    private int food;
    private float saturation;
    public CongealedEdibleBlock(Properties properties, Block blockToChangeInto, int food, float saturation) {
        super(properties);
        this.blockToChangeInto = blockToChangeInto;
        this.saturation = saturation;
        this.food = food;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if(itemStack.getItem() instanceof BlockItem) {
            return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
        }
        else
        {
            return eatBlock(level, player, pos);
        }
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        super.fallOn(level, state, pos, entity, fallDistance * 0.5);
    }

    private InteractionResult eatBlock(Level level, Player player, BlockPos pos)
    {
        if(player.canEat(false)) {
            level.setBlockAndUpdate(pos, getReturnBlock().defaultBlockState());
            player.getFoodData().eat(food, saturation);
            level.playSound(player, pos, SoundEvents.PLAYER_BURP, SoundSource.PLAYERS);
            level.gameEvent(player, GameEvent.EAT, pos);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
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
