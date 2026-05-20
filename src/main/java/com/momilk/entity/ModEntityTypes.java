package com.momilk.entity;

import com.momilk.MoMilk;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntityTypes {

    public static final EntityType<MilkArrow> MILK_ARROW = register(
            "milk_arrow",
            EntityType.Builder.<MilkArrow>of(MilkArrow::new, MobCategory.MISC)
                    .sized(0.75f, 1.75f)
    );

    public static final EntityType<ChocolateMilkArrow> CHOCOLATE_MILK_ARROW = register(
            "chocolate_milk_arrow",
            EntityType.Builder.<ChocolateMilkArrow>of(ChocolateMilkArrow::new, MobCategory.MISC)
                    .sized(0.75f, 1.75f)
    );

    public static final EntityType<SpoiledMilkArrow> SPOILED_MILK_ARROW = register(
            "spoiled_milk_arrow",
            EntityType.Builder.<SpoiledMilkArrow>of(SpoiledMilkArrow::new, MobCategory.MISC)
                    .sized(0.75f, 1.75f)
    );

    public static final EntityType<CerealArrow> CEREAL_ARROW = register(
            "cereal_arrow",
            EntityType.Builder.<CerealArrow>of(CerealArrow::new, MobCategory.MISC)
                    .sized(0.75f, 1.75f)
    );

    public static final EntityType<HotChocolateArrow> HOT_CHOCOLATE_ARROW = register(
            "hot_chocolate_arrow",
            EntityType.Builder.<HotChocolateArrow>of(HotChocolateArrow::new, MobCategory.MISC)
                    .sized(0.75f, 1.75f)
    );

    public static final EntityType<SpoiledCerealArrow> SPOILED_CEREAL_ARROW = register(
            "spoiled_cereal_arrow",
            EntityType.Builder.<SpoiledCerealArrow>of(SpoiledCerealArrow::new, MobCategory.MISC)
                    .sized(0.75f, 1.75f)
    );

    public static final EntityType<HogMilkArrow> HOG_MILK_ARROW = register(
            "hog_milk_arrow",
            EntityType.Builder.<HogMilkArrow>of(HogMilkArrow::new, MobCategory.MISC)
                    .sized(0.75f, 1.75f)
    );

    public static final EntityType<SpoiledHogMilkArrow> SPOILED_HOG_MILK_ARROW = register(
            "spoiled_hog_milk_arrow",
            EntityType.Builder.<SpoiledHogMilkArrow>of(SpoiledHogMilkArrow::new, MobCategory.MISC)
                    .sized(0.75f, 1.75f)
    );

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MoMilk.MOD_ID, name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void registerModEntityTypes() {
        MoMilk.LOGGER.info("Registering EntityTypes for " + MoMilk.MOD_ID);
    }

    public static void registerAttributes() {
        //FabricDefaultAttributeRegistry.register(MILK_ARROW, MiniGolemEntity.createCubeAttributes());
    }
}
