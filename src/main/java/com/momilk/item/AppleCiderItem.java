package com.momilk.item;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.momilk.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;
import java.util.function.Supplier;

public class AppleCiderItem extends Item {

    static Supplier<BiMap<Block, Block>> SALTED_BLOCKS = Suppliers.memoize(
            () -> ImmutableBiMap.<Block, Block>builder()
                    .put(ModBlocks.BRINY_TUBE_CORAL_BLOCK, Blocks.TUBE_CORAL_BLOCK)
                    .put(ModBlocks.BRINY_FIRE_CORAL_BLOCK, Blocks.FIRE_CORAL_BLOCK)
                    .put(ModBlocks.BRINY_BUBBLE_CORAL_BLOCK, Blocks.BUBBLE_CORAL_BLOCK)
                    .put(ModBlocks.BRINY_HORN_CORAL_BLOCK, Blocks.HORN_CORAL_BLOCK)
                    .put(ModBlocks.BRINY_BRAIN_CORAL_BLOCK, Blocks.BRAIN_CORAL_BLOCK)
                    .put(ModBlocks.BRINY_TUBE_CORAL_FAN, Blocks.TUBE_CORAL_FAN)
                    .put(ModBlocks.BRINY_BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_FAN)
                    .put(ModBlocks.BRINY_BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_FAN)
                    .put(ModBlocks.BRINY_FIRE_CORAL_FAN, Blocks.FIRE_CORAL_FAN)
                    .put(ModBlocks.BRINY_HORN_CORAL_FAN, Blocks.HORN_CORAL_FAN)
                    .put(ModBlocks.BRINY_TUBE_CORAL_WALL_FAN, Blocks.TUBE_CORAL_WALL_FAN)
                    .put(ModBlocks.BRINY_BUBBLE_CORAL_WALL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN)
                    .put(ModBlocks.BRINY_BRAIN_CORAL_WALL_FAN, Blocks.BRAIN_CORAL_WALL_FAN)
                    .put(ModBlocks.BRINY_FIRE_CORAL_WALL_FAN, Blocks.FIRE_CORAL_WALL_FAN)
                    .put(ModBlocks.BRINY_HORN_CORAL_WALL_FAN, Blocks.HORN_CORAL_WALL_FAN)
                    .put(ModBlocks.BRINY_TUBE_CORAL, Blocks.TUBE_CORAL)
                    .put(ModBlocks.BRINY_BRAIN_CORAL, Blocks.BRAIN_CORAL)
                    .put(ModBlocks.BRINY_BUBBLE_CORAL, Blocks.BUBBLE_CORAL)
                    .put(ModBlocks.BRINY_FIRE_CORAL, Blocks.FIRE_CORAL)
                    .put(ModBlocks.BRINY_HORN_CORAL, Blocks.HORN_CORAL)
                    .put(ModBlocks.BRINY_CLOSED_EYEBLOSSOM, Blocks.CLOSED_EYEBLOSSOM)
                    .put(ModBlocks.BRINY_OPEN_EYEBLOSSOM, Blocks.OPEN_EYEBLOSSOM)
                    .put(ModBlocks.POTTED_BRINY_CLOSED_EYEBLOSSOM, Blocks.POTTED_CLOSED_EYEBLOSSOM)
                    .put(ModBlocks.POTTED_BRINY_OPEN_EYEBLOSSOM, Blocks.POTTED_OPEN_EYEBLOSSOM)
                    .build()
    );

    public AppleCiderItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Block block = level.getBlockState(pos).getBlock();
        BlockState state = level.getBlockState(pos);
        ItemStack itemInHand = context.getItemInHand();

        if(SALTED_BLOCKS.get().containsKey(block))
        {
            level.setBlockAndUpdate(pos, SALTED_BLOCKS.get().get(block).withPropertiesOf(state));
            itemInHand.shrink(1);
            context.getPlayer().addItem(Items.GLASS_BOTTLE.getDefaultInstance());
            level.playSound(null, pos, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS);
            DustParticleOptions dpo = new DustParticleOptions(Color.pink.getRGB(), 3f);
            level.addParticle(dpo, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);
            return InteractionResult.SUCCESS;
        }

        return super.useOn(context);
    }
}
