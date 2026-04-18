package com.momilk;

import com.momilk.block.BlockCallbackEvents;
import com.momilk.block.ModBlocks;
import com.momilk.dispenser_actions.DispenserModRegistry;
import com.momilk.effects.ModEffects;
import com.momilk.effects.ModPotions;
import com.momilk.entity.ModEntityTypes;
import com.momilk.item.ModItems;
import com.momilk.ui.ModCreativeInventory;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoMilk implements ModInitializer {
	public static final String MOD_ID = "momilk";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModItems.initialize();
		ModEntityTypes.registerModEntityTypes();
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ModCreativeInventory.MO_MILK_TAB_KEY, ModCreativeInventory.MO_MILK_TAB);
		DispenserModRegistry.register();
		BlockCallbackEvents.register();
		ModEffects.register();
		ModPotions.register();
		ModBlocks.initialize();
	}
}