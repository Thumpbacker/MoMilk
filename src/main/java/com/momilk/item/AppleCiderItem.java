package com.momilk.item;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.momilk.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
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

    static Supplier<BiMap<Block, Block>> CIDER_TRANSFORM_BLOCKS = Suppliers.memoize(
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
                    .put(Blocks.INFESTED_COBBLESTONE, Blocks.COBBLESTONE)
                    .put(Blocks.INFESTED_DEEPSLATE, Blocks.DEEPSLATE)
                    .put(Blocks.INFESTED_STONE, Blocks.STONE)
                    .put(Blocks.INFESTED_STONE_BRICKS, Blocks.STONE_BRICKS)
                    .put(Blocks.INFESTED_CHISELED_STONE_BRICKS, Blocks.CHISELED_STONE_BRICKS)
                    .put(Blocks.INFESTED_CRACKED_STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS)
                    .put(Blocks.INFESTED_MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS)
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

        if(CIDER_TRANSFORM_BLOCKS.get().containsKey(block))
        {
            level.setBlockAndUpdate(pos, CIDER_TRANSFORM_BLOCKS.get().get(block).withPropertiesOf(state));
            itemInHand.shrink(1);
            context.getPlayer().addItem(Items.GLASS_BOTTLE.getDefaultInstance());
            level.playSound(null, pos, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS);
            if(!level.isClientSide())
            {
                ServerLevel sl = (ServerLevel) level;
                sl.sendParticles(ParticleTypes.SPLASH, pos.getX() + level.getRandom().nextDouble(), pos.getY() + 1, pos.getZ() + level.getRandom().nextDouble(), 1, 0.0, 0.0, 0.0, 1.0);
            }
            return InteractionResult.SUCCESS;
        }

        return super.useOn(context);
    }
}
