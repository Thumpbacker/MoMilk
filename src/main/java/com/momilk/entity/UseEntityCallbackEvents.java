package com.momilk.entity;

import com.momilk.MoMilk;
import com.momilk.item.ModItems;
import com.momilk.util.ModTags;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.packs.LootData;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.cow.AbstractCow;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.entity.animal.cow.MushroomCow;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class UseEntityCallbackEvents {

    public void milkEntityEvent()
    {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {

            if (entity instanceof LivingEntity livingEntity && !livingEntity.isBaby() && livingEntity.is(ModTags.EntityTypes.MILKABLE)) {

                ItemStack itemUsed = player.getItemInHand(hand);
                MoMilk.LOGGER.info(getLootTable(livingEntity).toString());
                ItemStack itemFromInteraction = getItemFromInteraction(livingEntity, world, itemUsed, getLootTable(livingEntity));
                if(!itemUsed.is(ModTags.Items.MILKING_TOOLS))
                {
                    return InteractionResult.PASS;
                }

                if(itemFromInteraction != null)
                {
                    itemUsed.consume(1, player);
                    player.addItem(itemFromInteraction);
                    entity.playSound(getMilkingNoises(entity), 1.0F, 1.0F);
                }

                return InteractionResult.SUCCESS;
            }

            return InteractionResult.PASS;
        });
    }

    public ItemStack getItemFromInteraction(LivingEntity entity, Level level, ItemStack itemUsed, ResourceKey<LootTable> key){

        if(level instanceof ServerLevel serverLevel) {
            Function<LootParams.Builder, LootParams> paramsBuilder = params -> params.withParameter(LootContextParams.TARGET_ENTITY, entity)
                    .withOptionalParameter(LootContextParams.INTERACTING_ENTITY, entity)
                    .withParameter(LootContextParams.TOOL, itemUsed)
                    .create(LootContextParamSets.ENTITY_INTERACT);

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

    private ResourceKey<LootTable> getLootTable(LivingEntity entity)
    {
        String path = "milk/" + entity.getName().getString().toLowerCase();
        return ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, path));
    }

    private SoundEvent getMilkingNoises(Entity entity)
    {
        if(entity instanceof AbstractCow)
        {
            return SoundEvents.COW_MILK;
        }
        else if(entity instanceof Goat goat)
        {
            return goat.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_MILK : SoundEvents.GOAT_MILK;
        }
        else if(entity instanceof Zoglin)
        {
            return SoundEvents.ZOGLIN_ANGRY;
        }
        else if(entity instanceof Hoglin)
        {
            return SoundEvents.HOGLIN_ANGRY;
        }

        return SoundEvents.HOSTILE_SPLASH;
    }

    public static void register()
    {
        UseEntityCallbackEvents useEntityCallbackEvents = new UseEntityCallbackEvents();
        useEntityCallbackEvents.milkEntityEvent();
    }
}
