package com.momilk.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class ModFlameableBlocks {

    public static void initialize()
    {
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CINNAMON_STICK_BALE, 10, 50);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.WRAPPED_BAMBOO_BLOCK, 10, 50);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_WRAPPED_BAMBOO_BLOCK, 10, 50);
    }

}
