package com.momilk.item;

import com.momilk.MoMilk;
import com.momilk.block.GlowingSaltLayerBlock;
import com.momilk.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CopperGolemStatueBlock;

import java.util.function.BiFunction;
import java.util.function.Function;

import static net.minecraft.world.item.Items.BUCKET;
import static net.minecraft.world.item.Items.GLASS_BOTTLE;

public class ModItems {

    public static final Item CHOCOLATE_MILK_BUCKET = register("chocolate_milk_bucket", Item::new, new Item.Properties().craftRemainder(BUCKET).component(DataComponents.CONSUMABLE, ModConsumables.CHOCOLATE_MILK_CONSUMABLE).usingConvertsTo(BUCKET).stacksTo(1));
    public static final Item SPOILED_MILK_BUCKET = register("spoiled_milk_bucket", Item::new, new Item.Properties().craftRemainder(BUCKET).component(DataComponents.CONSUMABLE, ModConsumables.SPOILED_MILK_CONSUMABLE).usingConvertsTo(BUCKET).stacksTo(1));
    public static final Item MILK_BOTTLE = register("milk_bottle", Item::new, new Item.Properties().craftRemainder(GLASS_BOTTLE).component(DataComponents.CONSUMABLE, ModConsumables.MILK_BOTTLE).usingConvertsTo(GLASS_BOTTLE).stacksTo(16));
    public static final Item CHOCOLATE_MILK_BOTTLE = register("chocolate_milk_bottle", Item::new, new Item.Properties().craftRemainder(GLASS_BOTTLE).component(DataComponents.CONSUMABLE, ModConsumables.CHOCOLATE_MILK_BOTTLE).usingConvertsTo(GLASS_BOTTLE).stacksTo(16));
    public static final Item SPOILED_MILK_BOTTLE = register("spoiled_milk_bottle", Item::new, new Item.Properties().craftRemainder(GLASS_BOTTLE).component(DataComponents.CONSUMABLE, ModConsumables.SPOILED_MILK_BOTTLE).usingConvertsTo(GLASS_BOTTLE).stacksTo(16));
    public static final Item MILK_ARROW = register("milk_arrow", MilkArrowItem::new, new Item.Properties());
    public static final Item CHOCOLATE_MILK_ARROW = register("chocolate_milk_arrow", ChocolateMilkArrowItem::new, new Item.Properties());
    public static final Item SPOILED_MILK_ARROW = register("spoiled_milk_arrow", SpoiledMilkArrowItem::new, new Item.Properties());
    public static final Item SUSPICIOUS_MILK = register("suspicious_milk", Item::new, new Item.Properties().craftRemainder(GLASS_BOTTLE).component(DataComponents.CONSUMABLE, ModConsumables.SUSPICIOUS_MILK).usingConvertsTo(GLASS_BOTTLE).stacksTo(16).component(DataComponents.SUSPICIOUS_STEW_EFFECTS, SuspiciousStewEffects.EMPTY));
    public static final Item APPLE_CIDER = register("apple_cider", AppleCiderItem::new, new Item.Properties().food(ModFoods.APPLE_CIDER).craftRemainder(GLASS_BOTTLE).usingConvertsTo(GLASS_BOTTLE).component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK));
    public static final Item CINNAMON_STICK = register("cinnamon_stick", Item::new, new Item.Properties().food(ModFoods.CINNAMON_STICK));
    public static final Item HYPHAE_STICK = register("hyphae_stick", Item::new, new Item.Properties().food(ModFoods.CINNAMON_STICK));
    public static final Item SALT = register("salt", createSaltItemWithCustomItemName(ModBlocks.SALT_LAYER), new Item.Properties().food(ModFoods.SALT, ModConsumables.SALT));
    public static final Item FLOATING_SALT = register("floating_salt", createBlockItemWithCustomItemName(ModBlocks.FLOATING_SALT_LAYER), new Item.Properties());
    public static final Item REJUVENATING_SALT = register("rejuvenating_salt", createBlockItemWithCustomItemName(ModBlocks.REJUVENATING_SALT_LAYER), new Item.Properties());
    public static final Item GLOWING_SALT = register("glowing_salt", createGlowingSaltItemWithCustomItemName(ModBlocks.GLOWING_SALT_LAYER), new Item.Properties().component(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY.with(GlowingSaltLayerBlock.LEVEL, 15)));
    public static final Item BRINY_TUBE_CORAL_FAN = register("briny_tube_coral_fan", createWallBlockItem(ModBlocks.BRINY_TUBE_CORAL_FAN, ModBlocks.BRINY_TUBE_CORAL_WALL_FAN), new Item.Properties());
    public static final Item BRINY_BUBBLE_CORAL_FAN = register("briny_bubble_coral_fan", createWallBlockItem(ModBlocks.BRINY_BUBBLE_CORAL_FAN, ModBlocks.BRINY_BUBBLE_CORAL_WALL_FAN), new Item.Properties());
    public static final Item BRINY_HORN_CORAL_FAN = register("briny_horn_coral_fan", createWallBlockItem(ModBlocks.BRINY_HORN_CORAL_FAN, ModBlocks.BRINY_HORN_CORAL_WALL_FAN), new Item.Properties());
    public static final Item BRINY_BRAIN_CORAL_FAN = register("briny_brain_coral_fan", createWallBlockItem(ModBlocks.BRINY_BRAIN_CORAL_FAN, ModBlocks.BRINY_BRAIN_CORAL_WALL_FAN), new Item.Properties());
    public static final Item BRINY_FIRE_CORAL_FAN = register("briny_fire_coral_fan", createWallBlockItem(ModBlocks.BRINY_FIRE_CORAL_FAN, ModBlocks.BRINY_FIRE_CORAL_WALL_FAN), new Item.Properties());
    public static final Item HOT_CHOCOLATE = register("hot_chocolate", Item::new ,new Item.Properties().food(ModFoods.HOT_CHOCOLATE, ModConsumables.HOT_CHOCOLATE).stacksTo(1).craftRemainder(BUCKET).usingConvertsTo(BUCKET));
    public static final Item CEREAL = register("cereal", Item::new ,new Item.Properties().food(ModFoods.CEREAL).usingConvertsTo(BUCKET).stacksTo(1).craftRemainder(BUCKET));
    public static final Item CEREAL_BOTTLE = register("cereal_bottle", Item::new ,new Item.Properties().food(ModFoods.CEREAL_BOTTLE).usingConvertsTo(GLASS_BOTTLE).stacksTo(16).craftRemainder(GLASS_BOTTLE));
    public static final Item CEREAL_ARROW = register("cereal_arrow", CerealArrowItem::new, new Item.Properties());
    public static final Item HOT_CHOCOLATE_BOTTLE = register("hot_chocolate_bottle", Item::new ,new Item.Properties().food(ModFoods.HOT_CHOCOLATE_BOTTLE, ModConsumables.HOT_CHOCOLATE_BOTTLE).stacksTo(16).usingConvertsTo(GLASS_BOTTLE).craftRemainder(GLASS_BOTTLE));
    public static final Item HOT_CHOCOLATE_ARROW = register("hot_chocolate_arrow", HotChocolateArrowItem::new, new Item.Properties());
    public static final Item SPOILED_CEREAL = register("spoiled_cereal", Item::new ,new Item.Properties().food(ModFoods.SPOILED_CEREAL).usingConvertsTo(BUCKET).stacksTo(1).craftRemainder(BUCKET));
    public static final Item SPOILED_CEREAL_BOTTLE = register("spoiled_cereal_bottle", Item::new ,new Item.Properties().food(ModFoods.SPOILED_CEREAL_BOTTLE).usingConvertsTo(GLASS_BOTTLE).stacksTo(16).craftRemainder(GLASS_BOTTLE));
    public static final Item SPOILED_CEREAL_ARROW = register("spoiled_cereal_arrow", SpoiledCerealArrowItem::new, new Item.Properties());
    public static final Item HOG_MILK_BUCKET = register("hog_milk_bucket", Item::new ,new Item.Properties().component(DataComponents.CONSUMABLE, ModConsumables.HOG_MILK).usingConvertsTo(BUCKET).stacksTo(1).craftRemainder(BUCKET));
    public static final Item HOG_MILK_BOTTLE = register("hog_milk_bottle", Item::new ,new Item.Properties().component(DataComponents.CONSUMABLE, ModConsumables.HOG_MILK_BOTTLE).usingConvertsTo(GLASS_BOTTLE).stacksTo(16).craftRemainder(GLASS_BOTTLE));
    public static final Item HOG_MILK_ARROW = register("hog_milk_arrow", HogMilkArrowItem::new, new Item.Properties());
    public static final Item SPOILED_HOG_MILK_BUCKET = register("spoiled_hog_milk_bucket", Item::new ,new Item.Properties().component(DataComponents.CONSUMABLE, ModConsumables.SPOILED_HOG_MILK_BUCKET).usingConvertsTo(BUCKET).stacksTo(1).craftRemainder(BUCKET));
    public static final Item SPOILED_HOG_MILK_BOTTLE = register("spoiled_hog_milk_bottle", Item::new ,new Item.Properties().component(DataComponents.CONSUMABLE, ModConsumables.SPOILED_HOG_MILK_BOTTLE).usingConvertsTo(GLASS_BOTTLE).stacksTo(16).craftRemainder(GLASS_BOTTLE));
    public static final Item SPOILED_HOG_MILK_ARROW = register("spoiled_hog_milk_arrow", SpoiledHogMilkArrowItem::new, new Item.Properties());
    public static final Item SALTED_COD = register("salted_cod", Item::new ,new Item.Properties().food(ModFoods.SALTED_COD));
    public static final Item SALTED_SALMON = register("salted_salmon", Item::new ,new Item.Properties().food(ModFoods.SALTED_SALMON));
    public static final Item SALTED_TROPICAL_FISH = register("salted_tropical_fish", Item::new ,new Item.Properties().food(ModFoods.SALTED_TROPICAL_FISH));
    public static final Item SALTED_POTATO = register("salted_potato", Item::new ,new Item.Properties().food(ModFoods.SALTED_POTATO));
    public static final Item SALTED_ROTTEN_FLESH = register("salted_rotten_flesh", Item::new ,new Item.Properties().food(Foods.ROTTEN_FLESH, ModConsumables.SALTED_ROTTEN_FLESH));
    public static final Item SALTED_SPIDER_EYE = register("salted_spider_eye", Item::new ,new Item.Properties().food(Foods.SPIDER_EYE, ModConsumables.SALTED_SPIDER_EYE));

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, name));

        // Create the item instance.
        T item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    private static Item registerBlock(final Block block, final BiFunction<Block, Item.Properties, Item> itemFactory) {
        return registerBlock(block, itemFactory, new Item.Properties());
    }

    private static Item registerBlock(final Block block, final BiFunction<Block, Item.Properties, Item> itemFactory, final Item.Properties properties) {
        return register(block.getDescriptionId(), p -> (Item)itemFactory.apply(block, p), properties.useBlockDescriptionPrefix().requiredFeatures(block.requiredFeatures()));
    }

    public static void initialize() {
    }

    private static Function<Item.Properties, Item> createBlockItemWithCustomItemName(final Block block) {
        return p -> new BlockItem(block, p.useItemDescriptionPrefix());
    }

    private static Function<Item.Properties, Item> createGlowingSaltItemWithCustomItemName(final Block block) {
        return p -> new GlowingSaltItem(block, p.useItemDescriptionPrefix());
    }

    private static Function<Item.Properties, Item> createSaltItemWithCustomItemName(final Block block) {
        return p -> new SaltItem(block, p.useItemDescriptionPrefix());
    }

    private static Function<Item.Properties, Item> createWallBlockItem(final Block block, final  Block wallBlock)
    {
        return p -> new StandingAndWallBlockItem(block, wallBlock, Direction.DOWN, p.useItemDescriptionPrefix());
    }
}
