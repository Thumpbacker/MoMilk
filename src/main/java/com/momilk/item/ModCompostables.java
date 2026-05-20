package com.momilk.item;

import com.momilk.block.ModBlocks;
import net.fabricmc.fabric.api.registry.CompostableRegistry;

public class ModCompostables {

    public static void initialize()
    {
        CompostableRegistry.INSTANCE.add(ModItems.CINNAMON_STICK, 0.3f);
        CompostableRegistry.INSTANCE.add(ModItems.HYPHAE_STICK, 0.3f);
        CompostableRegistry.INSTANCE.add(ModBlocks.CHEESE, 1f);
        CompostableRegistry.INSTANCE.add(ModBlocks.CINNAMON_STICK_BALE, 0.85f);
        CompostableRegistry.INSTANCE.add(ModBlocks.HYPHAE_STICK_BALE, 0.85f);
        CompostableRegistry.INSTANCE.add(ModBlocks.BRINY_CLOSED_EYEBLOSSOM, 0.65f);
        CompostableRegistry.INSTANCE.add(ModBlocks.BRINY_OPEN_EYEBLOSSOM, 0.65f);
        CompostableRegistry.INSTANCE.add(ModItems.SALTED_POTATO, 0.85f);
    }

}
