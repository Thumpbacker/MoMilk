package com.momilk.item;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.momilk.block.ModBlocks;
import com.momilk.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;
import java.util.function.Supplier;

public class SaltItem extends BlockItem {
    static Supplier<BiMap<Block, Block>> SALTABLE_BLOCKS = Suppliers.memoize(
            () -> ImmutableBiMap.<Block, Block>builder()
                    .put(Blocks.TUBE_CORAL_BLOCK, ModBlocks.BRINY_TUBE_CORAL_BLOCK)
                    .put(Blocks.FIRE_CORAL_BLOCK, ModBlocks.BRINY_FIRE_CORAL_BLOCK)
                    .put(Blocks.BUBBLE_CORAL_BLOCK, ModBlocks.BRINY_BUBBLE_CORAL_BLOCK)
                    .put(Blocks.HORN_CORAL_BLOCK, ModBlocks.BRINY_HORN_CORAL_BLOCK)
                    .put(Blocks.BRAIN_CORAL_BLOCK, ModBlocks.BRINY_BRAIN_CORAL_BLOCK)
                    .put(Blocks.TUBE_CORAL_FAN, ModBlocks.BRINY_TUBE_CORAL_FAN)
                    .put(Blocks.BUBBLE_CORAL_FAN, ModBlocks.BRINY_BUBBLE_CORAL_FAN)
                    .put(Blocks.BRAIN_CORAL_FAN, ModBlocks.BRINY_BRAIN_CORAL_FAN)
                    .put(Blocks.FIRE_CORAL_FAN, ModBlocks.BRINY_FIRE_CORAL_FAN)
                    .put(Blocks.HORN_CORAL_FAN, ModBlocks.BRINY_HORN_CORAL_FAN)
                    .put(Blocks.TUBE_CORAL_WALL_FAN, ModBlocks.BRINY_TUBE_CORAL_WALL_FAN)
                    .put(Blocks.BUBBLE_CORAL_WALL_FAN, ModBlocks.BRINY_BUBBLE_CORAL_WALL_FAN)
                    .put(Blocks.BRAIN_CORAL_WALL_FAN, ModBlocks.BRINY_BRAIN_CORAL_WALL_FAN)
                    .put(Blocks.FIRE_CORAL_WALL_FAN, ModBlocks.BRINY_FIRE_CORAL_WALL_FAN)
                    .put(Blocks.HORN_CORAL_WALL_FAN, ModBlocks.BRINY_HORN_CORAL_WALL_FAN)
                    .put(Blocks.TUBE_CORAL, ModBlocks.BRINY_TUBE_CORAL)
                    .put(Blocks.BRAIN_CORAL, ModBlocks.BRINY_BRAIN_CORAL)
                    .put(Blocks.BUBBLE_CORAL, ModBlocks.BRINY_BUBBLE_CORAL)
                    .put(Blocks.FIRE_CORAL, ModBlocks.BRINY_FIRE_CORAL)
                    .put(Blocks.HORN_CORAL, ModBlocks.BRINY_HORN_CORAL)
                    .put(Blocks.CLOSED_EYEBLOSSOM, ModBlocks.BRINY_CLOSED_EYEBLOSSOM)
                    .put(Blocks.OPEN_EYEBLOSSOM, ModBlocks.BRINY_OPEN_EYEBLOSSOM)
                    .put(Blocks.POTTED_CLOSED_EYEBLOSSOM, ModBlocks.POTTED_BRINY_CLOSED_EYEBLOSSOM)
                    .put(Blocks.POTTED_OPEN_EYEBLOSSOM, ModBlocks.POTTED_BRINY_OPEN_EYEBLOSSOM)
                    .build()
    );

    public SaltItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Block block = level.getBlockState(pos).getBlock();
        BlockState state = level.getBlockState(pos);
        ItemStack itemInHand = context.getItemInHand();

        if(SALTABLE_BLOCKS.get().containsKey(block))
        {
            level.setBlockAndUpdate(pos, SALTABLE_BLOCKS.get().get(block).withPropertiesOf(state));
            itemInHand.shrink(1);
            level.playSound(null, pos, SoundEvents.SAND_BREAK, SoundSource.BLOCKS);
            DustParticleOptions dpo = new DustParticleOptions(Color.pink.getRGB(), 3f);
            level.addParticle(dpo, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);
            return InteractionResult.SUCCESS;
        }

        return super.useOn(context);
    }
}
