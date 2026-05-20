package com.momilk.util;

import com.momilk.MoMilk;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks{ ;
        public static final TagKey<Block> CONGEALED_MILKS = createTag("congealed_milks");
        public static final TagKey<Block> NON_STONE_LIKE_WALLS = createTag("non_stone_like_walls");

        private static TagKey<Block> createTag(String name)
        {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, name));
        }
    }

    public static class Items{

        public static final TagKey<Item> CINNAMON_STICKS = createTag("cinnamon_sticks");
        public static final TagKey<Item> SUSPICIOUS_EFFECT_BLINDNESS = createTag("suspicious_effect_blindness");
        public static final TagKey<Item> SUSPICIOUS_EFFECT_DROWNING = createTag("suspicious_effect_drowning");
        public static final TagKey<Item> SUSPICIOUS_EFFECT_INSTANT_DAMAGE = createTag("suspicious_effect_instant_damage");
        public static final TagKey<Item> SUSPICIOUS_EFFECT_NIGHT_VISION = createTag("suspicious_effect_night_vision");
        public static final TagKey<Item> SUSPICIOUS_EFFECT_POISON = createTag("suspicious_effect_poison");
        public static final TagKey<Item> SUSPICIOUS_EFFECT_SATURATION = createTag("suspicious_effect_saturation");
        public static final TagKey<Item> SUSPICIOUS_EFFECT_WEAKNESS = createTag("suspicious_effect_weakness");
        public static final TagKey<Item> TULIPS = createTag("tulips");
        public static final TagKey<Item> CONGEALED_MILKS = createTag("congealed_milks");
        public static final TagKey<Item> NON_STONE_LIKE_WALLS = createTag("non_stone_like_walls");
        public static final TagKey<Item> MILKING_TOOLS = createTag("milking_tools");

        private static TagKey<Item> createTag(String name)
        {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, name));
        }
    }

    public static class EntityTypes{

        public static final TagKey<EntityType<?>> HURT_BY_SALT = createTag("hurt_by_salt");
        public static final TagKey<EntityType<?>> MILKABLE = createTag("milkable");
        public static final TagKey<EntityType<?>> MILKABLE_BY_DISPENSERS = createTag("milkable_by_dispensers");
        public static final TagKey<EntityType<?>> PROVIDES_HOG_MILK_FROM_DISPENSERS = createTag("provides_hog_milk_from_dispensers");
        public static final TagKey<EntityType<?>> PROVIDES_SPOILED_HOG_MILK_FROM_DISPENSERS = createTag("provides_spoiled_hog_milk_from_dispensers");
        public static final TagKey<EntityType<?>> PROVIDES_MILK_FROM_DISPENSERS = createTag("provides_milk_from_dispensers");


        private static TagKey<EntityType<?>> createTag(String name)
        {
            return TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, name));
        }
    }
}
