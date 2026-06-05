package com.momilk;

import com.momilk.block.BlockCallbackEvents;
import com.momilk.block.ModFlameableBlocks;
import com.momilk.block.ModBlocks;
import com.momilk.dispenser_actions.DispenserModRegistry;
import com.momilk.effects.ModEffects;
import com.momilk.effects.ModPotions;
import com.momilk.entity.ModEntityTypes;
import com.momilk.entity.UseEntityCallbackEvents;
import com.momilk.item.ModCompostables;
import com.momilk.item.ModFuelItems;
import com.momilk.item.ModItems;
import com.momilk.ui.ModCreativeInventory;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
		ModCreativeInventory.register();
		DispenserModRegistry.register();
		BlockCallbackEvents.register();
		ModEffects.register();
		ModPotions.register();
		ModBlocks.initialize();
		UseEntityCallbackEvents.register();
		ModCompostables.initialize();
		ModFuelItems.initialize();
		ModFlameableBlocks.initialize();
	}
}