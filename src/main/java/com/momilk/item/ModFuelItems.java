package com.momilk.item;

import com.momilk.block.ModBlocks;
import net.fabricmc.fabric.api.registry.FuelValueEvents;

public class ModFuelItems {

    public static void initialize(){
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.CINNAMON_STICK, 50);
        });
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ModBlocks.CINNAMON_STICK_BALE, 300);
        });
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ModBlocks.WRAPPED_BAMBOO_BLOCK, 300);
        });
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ModBlocks.STRIPPED_WRAPPED_BAMBOO_BLOCK, 300);
        });
    }

}
