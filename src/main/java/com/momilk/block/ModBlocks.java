package com.momilk.block;

import com.momilk.MoMilk;
import com.momilk.effects.ModEffects;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.awt.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class ModBlocks {

    public static final Block LARGE_SALT_BLOCK = register("large_salt_block", p -> new ColoredFallingBlock(new ColorRGBA(-Color.pink.getRGB()), p), BlockBehaviour.Properties.of().sound(SoundType.SAND).mapColor(MapColor.COLOR_PINK).strength(0.5F), true);
    public static final Block MEDIUM_SALT_BLOCK = register("medium_salt_block", p -> new SaltBlock(new ColorRGBA(-Color.pink.getRGB()), Block.column(14.0, 0.0, 14.0), LARGE_SALT_BLOCK, p), BlockBehaviour.Properties.of().sound(SoundType.SAND).mapColor(MapColor.COLOR_PINK).randomTicks().strength(0.5F), true);
    public static final Block SALT_BLOCK = register("salt_block", p -> new SaltBlock(new ColorRGBA(Color.pink.getRGB()), Block.column(8.0, 0.0, 8.0), MEDIUM_SALT_BLOCK, p), BlockBehaviour.Properties.of().sound(SoundType.SAND).mapColor(MapColor.COLOR_PINK).strength(0.5F).randomTicks(), true);
    public static final Block SALT_ORE = register("salt_ore", p -> new DropExperienceBlock(UniformInt.of(0, 2), p), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F), true);
    public static final Block DEEPSLATE_SALT_ORE = register("deepslate_salt_ore", p -> new DropExperienceBlock(UniformInt.of(0, 2), p), BlockBehaviour.Properties.ofFullCopy(SALT_ORE).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE), true);
    public static final Block LARGE_SALT_LAMP = register("large_salt_lamp", p -> new ColoredFallingBlock(new ColorRGBA(Color.pink.getRGB()), p), BlockBehaviour.Properties.of().sound(SoundType.SAND).mapColor(MapColor.COLOR_PINK).lightLevel(statex -> 15).strength(0.5F), true);
    public static final Block MEDIUM_SALT_LAMP = register("medium_salt_lamp", p -> new SaltBlock(new ColorRGBA(Color.pink.getRGB()), Block.column(8.0, 0.0, 8.0), LARGE_SALT_LAMP, p), BlockBehaviour.Properties.of().sound(SoundType.SAND).mapColor(MapColor.COLOR_PINK).randomTicks().lightLevel(statex -> 8).strength(0.5F), true);
    public static final Block SALT_LAMP = register("salt_lamp", p -> new SaltBlock(new ColorRGBA(Color.pink.getRGB()), Block.column(8.0, 0.0, 8.0), MEDIUM_SALT_LAMP, p), BlockBehaviour.Properties.of().sound(SoundType.SAND).mapColor(MapColor.COLOR_PINK).randomTicks().lightLevel(statex -> 5).strength(0.5F), true);
    public static final Block LARGE_REDSTONE_SALT_LAMP = register("large_redstone_salt_lamp", p -> new FallingPoweredBlock(p, 15), BlockBehaviour.Properties.of().sound(SoundType.SAND).mapColor(MapColor.COLOR_RED).randomTicks().lightLevel(statex -> 7).strength(0.5F), true);
    public static final Block MEDIUM_REDSTONE_SALT_LAMP = register("medium_redstone_salt_lamp", p -> new RedstoneSaltBlock(p, 8, Block.column(14.0, 0.0, 14.0), LARGE_REDSTONE_SALT_LAMP), BlockBehaviour.Properties.of().sound(SoundType.SAND).mapColor(MapColor.COLOR_RED).randomTicks().lightLevel(statex -> 4).strength(0.5F), true);
    public static final Block REDSTONE_SALT_LAMP = register("redstone_salt_lamp", p -> new RedstoneSaltBlock(p, 5, Block.column(8.0, 0.0, 8.0), MEDIUM_REDSTONE_SALT_LAMP), BlockBehaviour.Properties.of().sound(SoundType.SAND).mapColor(MapColor.COLOR_RED).randomTicks().lightLevel(statex -> 2).strength(0.5F), true);
    public static final Block LARGE_SOUL_SALT_LAMP = register("large_soul_salt_lamp", p -> new ColoredFallingBlock(new ColorRGBA(Color.pink.getRGB()), p), BlockBehaviour.Properties.of().sound(SoundType.SAND).mapColor(MapColor.COLOR_LIGHT_BLUE).lightLevel(statex -> 10).strength(0.5F), true);
    public static final Block LARGE_COPPER_SALT_LAMP = register("large_copper_salt_lamp", p -> new ColoredFallingBlock(new ColorRGBA(Color.pink.getRGB()), p), BlockBehaviour.Properties.of().sound(SoundType.SAND).mapColor(MapColor.COLOR_LIGHT_GREEN).lightLevel(statex -> 15).strength(0.5F), true);
    public static final Block MEDIUM_COPPER_SALT_LAMP = register("medium_copper_salt_lamp", p -> new SaltBlock(new ColorRGBA(Color.pink.getRGB()), Block.column(14.0, 0.0, 14.0), LARGE_COPPER_SALT_LAMP, p), BlockBehaviour.Properties.of().sound(SoundType.SAND).mapColor(MapColor.COLOR_LIGHT_GREEN).randomTicks().lightLevel(statex -> 8).strength(0.5F), true);
    public static final Block MEDIUM_SOUL_SALT_LAMP = register("medium_soul_salt_lamp", p -> new SaltBlock(new ColorRGBA(Color.pink.getRGB()), Block.column(14.0, 0.0, 14.0), LARGE_SOUL_SALT_LAMP, p), BlockBehaviour.Properties.of().sound(SoundType.SAND).mapColor(MapColor.COLOR_LIGHT_BLUE).randomTicks().lightLevel(statex -> 5).strength(0.5F), true);
    public static final Block COPPER_SALT_LAMP = register("copper_salt_lamp", p -> new SaltBlock(new ColorRGBA(Color.pink.getRGB()), Block.column(8.0, 0.0, 8.0), MEDIUM_COPPER_SALT_LAMP, p), BlockBehaviour.Properties.of().sound(SoundType.SAND).mapColor(MapColor.COLOR_LIGHT_GREEN).randomTicks().lightLevel(statex -> 5).strength(0.5F), true);
    public static final Block SOUL_SALT_LAMP = register("soul_salt_lamp", p -> new SaltBlock(new ColorRGBA(Color.pink.getRGB()), Block.column(8.0, 0.0, 8.0), MEDIUM_SOUL_SALT_LAMP, p), BlockBehaviour.Properties.of().sound(SoundType.SAND).mapColor(MapColor.COLOR_LIGHT_BLUE).randomTicks().lightLevel(statex -> 3).strength(0.5F), true);
    public static final Block SALT_LAYER = register("salt_layer", p -> new SaltLayerBlock(p, false, true), BlockBehaviour.Properties.of().sound(SoundType.SAND).instabreak().noCollision().pushReaction(PushReaction.DESTROY), false);
    public static final Block FLOATING_SALT_LAYER = register("floating_salt_layer", p -> new SaltLayerBlock(p, true, true), BlockBehaviour.Properties.of().sound(SoundType.SAND).instabreak().noCollision().pushReaction(PushReaction.DESTROY), false);
    public static final Block REJUVENATING_SALT_LAYER = register("rejuvenating_salt_layer", p -> new SaltLayerBlock(p, false, false), BlockBehaviour.Properties.of().sound(SoundType.SAND).instabreak().noCollision().pushReaction(PushReaction.DESTROY), false);
    public static final Block GLOWING_SALT_LAYER = register("glowing_salt_layer", p -> new SaltLayerBlock(p, false, false), BlockBehaviour.Properties.of().sound(SoundType.SAND).instabreak().noCollision().pushReaction(PushReaction.DESTROY).lightLevel(statex -> 15), false);
    public static final Block CHEESE = register("cheese", CheeseBlock::new, BlockBehaviour.Properties.of().sound(SoundType.WOOL).randomTicks(), true);
    public static final Block BRINY_TUBE_CORAL_BLOCK = register("briny_tube_coral_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TUBE_CORAL_BLOCK), true);
    public static final Block BRINY_BRAIN_CORAL_BLOCK = register("briny_brain_coral_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRAIN_CORAL_BLOCK), true);
    public static final Block BRINY_BUBBLE_CORAL_BLOCK = register("briny_bubble_coral_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BUBBLE_CORAL_BLOCK), true);
    public static final Block BRINY_FIRE_CORAL_BLOCK = register("briny_fire_coral_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.FIRE_CORAL_BLOCK), true);
    public static final Block BRINY_HORN_CORAL_BLOCK = register("briny_horn_coral_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.HORN_CORAL_BLOCK), true);
    public static final Block BRINY_TUBE_CORAL_FAN = register("briny_tube_coral_fan", BaseCoralFanBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TUBE_CORAL_FAN), false);
    public static final Block BRINY_FIRE_CORAL_FAN = register("briny_fire_coral_fan", BaseCoralFanBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.FIRE_CORAL_FAN), false);
    public static final Block BRINY_BRAIN_CORAL_FAN = register("briny_brain_coral_fan", BaseCoralFanBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRAIN_CORAL_FAN), false);
    public static final Block BRINY_HORN_CORAL_FAN = register("briny_horn_coral_fan", BaseCoralFanBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.HORN_CORAL_FAN), false);
    public static final Block BRINY_BUBBLE_CORAL_FAN = register("briny_bubble_coral_fan", BaseCoralFanBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BUBBLE_CORAL_FAN), false);
    public static final Block BRINY_TUBE_CORAL_WALL_FAN = register("briny_tube_coral_wall_fan", BaseCoralWallFanBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TUBE_CORAL_WALL_FAN).overrideLootTable(BRINY_TUBE_CORAL_FAN.getLootTable()), false);
    public static final Block BRINY_FIRE_CORAL_WALL_FAN = register("briny_fire_coral_wall_fan", BaseCoralWallFanBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.FIRE_CORAL_WALL_FAN).overrideLootTable(BRINY_FIRE_CORAL_FAN.getLootTable()), false);
    public static final Block BRINY_BRAIN_CORAL_WALL_FAN = register("briny_brain_coral_wall_fan", BaseCoralWallFanBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRAIN_CORAL_WALL_FAN).overrideLootTable(BRINY_BRAIN_CORAL_FAN.getLootTable()), false);
    public static final Block BRINY_HORN_CORAL_WALL_FAN = register("briny_horn_coral_wall_fan", BaseCoralWallFanBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.HORN_CORAL_WALL_FAN).overrideLootTable(BRINY_HORN_CORAL_FAN.getLootTable()), false);
    public static final Block BRINY_BUBBLE_CORAL_WALL_FAN = register("briny_bubble_coral_wall_fan", BaseCoralWallFanBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BUBBLE_CORAL_WALL_FAN).overrideLootTable(BRINY_BUBBLE_CORAL_FAN.getLootTable()), false);
    public static final Block BRINY_TUBE_CORAL = register("briny_tube_coral", BaseCoralPlantBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TUBE_CORAL), true);
    public static final Block BRINY_BUBBLE_CORAL = register("briny_bubble_coral", BaseCoralPlantBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BUBBLE_CORAL), true);
    public static final Block BRINY_BRAIN_CORAL = register("briny_brain_coral", BaseCoralPlantBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRAIN_CORAL), true);
    public static final Block BRINY_FIRE_CORAL = register("briny_fire_coral", BaseCoralPlantBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.FIRE_CORAL), true);
    public static final Block BRINY_HORN_CORAL = register("briny_horn_coral", BaseCoralPlantBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.HORN_CORAL), true);
    public static final Block BRINY_CLOSED_EYEBLOSSOM = register("briny_closed_eyeblossom", p -> new FlowerBlock(ModEffects.DROWNING, 7.0F, p), BlockBehaviour.Properties.ofFullCopy(Blocks.CLOSED_EYEBLOSSOM), true);
    public static final Block BRINY_OPEN_EYEBLOSSOM = register("briny_open_eyeblossom", p -> new FlowerBlock(ModEffects.DROWNING, 7.0F, p), BlockBehaviour.Properties.ofFullCopy(Blocks.OPEN_EYEBLOSSOM), true);
    public static final Block POTTED_BRINY_OPEN_EYEBLOSSOM = register("potted_briny_open_eyeblossom", p -> new FlowerPotBlock(BRINY_OPEN_EYEBLOSSOM, p), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY), false);
    public static final Block POTTED_BRINY_CLOSED_EYEBLOSSOM = register("potted_briny_closed_eyeblossom", p -> new FlowerPotBlock(BRINY_CLOSED_EYEBLOSSOM, p), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY), false);
    public static final Block WRAPPED_BAMBOO_BLOCK = register("wrapped_bamboo_block", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_BLOCK), true);
    public static final Block STRIPPED_WRAPPED_BAMBOO_BLOCK = register("stripped_wrapped_bamboo_block", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_BAMBOO_BLOCK), true);
    public static final Block SALT_STAIRS = register("salt_stairs", p -> new FallingStairBlock(ModBlocks.LARGE_SALT_BLOCK.defaultBlockState(), p), BlockBehaviour.Properties.ofFullCopy(ModBlocks.SALT_BLOCK), true);
    public static final Block SALT_SLAB = register("salt_slab", FallingSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(ModBlocks.LARGE_SALT_BLOCK), true);
    public static final Block SALT_WALL = register("salt_wall", FallingWallBlock::new, BlockBehaviour.Properties.ofFullCopy(ModBlocks.LARGE_SALT_BLOCK), true);
    public static final Block CONGEALED_MILK = register("congealed_milk", CongealedMilkBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MUD).mapColor(MapColor.WOOL), true);
    public static final Block CONGEALED_SPOILED_MILK = register("congealed_spoiled_milk", CongealedMilkBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MUD).mapColor(MapColor.TERRACOTTA_YELLOW), true);
    public static final Block CONGEALED_CHOCOLATE_MILK = register("congealed_chocolate_milk", CongealedMilkBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MUD).mapColor(MapColor.COLOR_BROWN), true);
    public static final Block CONGEALED_HOG_MILK = register("congealed_hog_milk", CongealedMilkBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MUD).mapColor(MapColor.TERRACOTTA_PINK), true);
    public static final Block CONGEALED_SPOILED_HOG_MILK = register("congealed_spoiled_hog_milk", CongealedMilkBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MUD).mapColor(MapColor.COLOR_RED), true);
    public static final Block CONGEALED_CEREAL = register("congealed_cereal", p -> new CongealedEdibleBlock(p, ModBlocks.CONGEALED_MILK), BlockBehaviour.Properties.ofFullCopy(Blocks.MUD).mapColor(MapColor.WOOL), true);
    public static final Block CONGEALED_HOT_CHOCOLATE = register("congealed_hot_chocolate", p -> new CongealedHotChocolateBlock(p, ModBlocks.CONGEALED_CHOCOLATE_MILK), BlockBehaviour.Properties.ofFullCopy(Blocks.MUD).mapColor(MapColor.COLOR_BROWN), true);
    public static final Block CONGEALED_SPOILED_CEREAL = register("congealed_spoiled_cereal", p -> new CongealedEdibleBlock(p, ModBlocks.CONGEALED_SPOILED_MILK), BlockBehaviour.Properties.ofFullCopy(Blocks.MUD).mapColor(MapColor.TERRACOTTA_YELLOW), true);
    public static final Block CINNAMON_STICK_BALE = register("cinnamon_stick_bale", RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOD).ignitedByLava().strength(2.0F), true);
    public static final Block HYPHAE_STICK_BALE = register("hyphae_stick_bale", RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.CRIMSON_NYLIUM).sound(SoundType.WOOD).strength(2.0F), true);

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
        // Create a registry key for the block
        ResourceKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(settings.setId(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, name));
    }

    private static BlockBehaviour.Properties wallVariant(final Block standingBlock, final boolean copyName) {
        BlockBehaviour.Properties wallProperties = BlockBehaviour.Properties.of().overrideLootTable(standingBlock.getLootTable());
        if (copyName) {
            wallProperties = wallProperties.overrideDescription(standingBlock.getDescriptionId());
        }

        return wallProperties;
    }

    public static void initialize() {}
}
