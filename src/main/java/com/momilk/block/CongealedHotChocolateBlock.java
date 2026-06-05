package com.momilk.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CongealedHotChocolateBlock extends CongealedEdibleBlock {


    public CongealedHotChocolateBlock(Properties properties, Block blockToChangeInto, int food, float saturation) {
        super(properties, blockToChangeInto, food, saturation);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        entity.hurt(level.damageSources().campfire(), 1);
    }
}
