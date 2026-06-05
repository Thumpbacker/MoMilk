package com.momilk.block;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockProperties {
    public static IntegerProperty CHEESE_BITES = IntegerProperty.create("cheese_bites", 0, 3);
    public static BooleanProperty CHEESE_AGES = BooleanProperty.create("cheese_ages");
    public static IntegerProperty SALT_IN_BLOCK = IntegerProperty.create("salt_in_block", 0, 16);
}
