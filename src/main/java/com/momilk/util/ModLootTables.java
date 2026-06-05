package com.momilk.util;

import com.momilk.MoMilk;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class ModLootTables {

    public static final ResourceKey<LootTable> STRIP_BAMBOO = register("blocks/strip/bamboo");
    public static final ResourceKey<LootTable> STRIP_CINNAMON_STICK = register("blocks/strip/cinnamon_stick");
    public static final ResourceKey<LootTable> STRIP_HYPHAE_STICK = register("blocks/strip/hyphae_stick");
    public static final ResourceKey<LootTable> BRUSH_LARGE_SALT_BLOCK = register("blocks/brush/large_salt_block");
    public static final ResourceKey<LootTable> BRUSH_MEDIUM_SALT_BLOCK = register("blocks/brush/medium_salt_block");
    public static final ResourceKey<LootTable> BRUSH_LARGE_SALT_LAMP = register("blocks/brush/large_salt_lamp");
    public static final ResourceKey<LootTable> BRUSH_MEDIUM_SALT_LAMP = register("blocks/brush/medium_salt_lamp");
    public static final ResourceKey<LootTable> BRUSH_LARGE_SOUL_SALT_LAMP = register("blocks/brush/large_soul_salt_lamp");
    public static final ResourceKey<LootTable> BRUSH_MEDIUM_SOUL_SALT_LAMP = register("blocks/brush/medium_soul_salt_lamp");
    public static final ResourceKey<LootTable> BRUSH_LARGE_COPPER_SALT_LAMP = register("blocks/brush/large_copper_salt_lamp");
    public static final ResourceKey<LootTable> BRUSH_MEDIUM_COPPER_SALT_LAMP = register("blocks/brush/medium_copper_salt_lamp");
    public static final ResourceKey<LootTable> BRUSH_LARGE_REDSTONE_SALT_LAMP = register("blocks/brush/large_redstone_salt_lamp");
    public static final ResourceKey<LootTable> BRUSH_MEDIUM_REDSTONE_SALT_LAMP = register("blocks/brush/medium_redstone_salt_lamp");
    public static final ResourceKey<LootTable> BRUSH_GRAVEL = register("blocks/brush/gravel");
    public static final ResourceKey<LootTable> BRUSH_GILDED_BLACKSTONE = register("blocks/brush/gilded_blackstone");
    public static final ResourceKey<LootTable> BRUSH_SALT_BLOCK = register("blocks/brush/salt_block");
    public static final ResourceKey<LootTable> BRUSH_REDSTONE_SALT_LAMP = register("blocks/brush/redstone_salt_lamp");
    public static final ResourceKey<LootTable> BRUSH_SALT_LAMP = register("blocks/brush/salt_lamp");
    public static final ResourceKey<LootTable> BRUSH_COPPER_SALT_LAMP = register("blocks/brush/copper_salt_lamp");
    public static final ResourceKey<LootTable> BRUSH_SOUL_SALT_LAMP = register("blocks/brush/soul_salt_lamp");


    public static ResourceKey<LootTable> register(String path)
    {
        return ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, path));
    }

    public static ItemStack getItemFromLootTable(ResourceKey<LootTable> key, Level level, ItemStack itemUsed, BlockState state, Vec3 pos)
    {
        if(level instanceof ServerLevel serverLevel) {
            Function<LootParams.Builder, LootParams> paramsBuilder = params -> params
                    .withParameter(LootContextParams.TOOL, itemUsed)
                    .withParameter(LootContextParams.ORIGIN, pos)
                    .withParameter(LootContextParams.BLOCK_STATE, state)
                    .create(LootContextParamSets.BLOCK);
            LootParams params = (LootParams) paramsBuilder.apply(new LootParams.Builder(serverLevel));
            LootTable lootTable = level.getServer().reloadableRegistries().getLootTable(key);
            List<ItemStack> drops = lootTable.getRandomItems(params);

            if(!drops.isEmpty())
            {
                Random random = new Random();
                return drops.get(random.nextInt(drops.size()));
            }
            else
            {
                return null;
            }
        }

        return null;
    }
}
