package com.momilk.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class ModFlameableBlocks {

    public static void initialize()
    {
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CINNAMON_STICK_BALE, 10, 50);
    }

}
