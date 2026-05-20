package com.momilk;

import com.momilk.entity.*;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class MoMilkClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityRenderers.register(ModEntityTypes.MILK_ARROW, MilkArrowRenderer::new);
		EntityRenderers.register(ModEntityTypes.CHOCOLATE_MILK_ARROW, ChocolateMilkArrowRenderer::new);
		EntityRenderers.register(ModEntityTypes.SPOILED_MILK_ARROW, SpoiledMilkArrowRenderer::new);
		EntityRenderers.register(ModEntityTypes.CEREAL_ARROW, CerealArrowRenderer::new);
		EntityRenderers.register(ModEntityTypes.HOT_CHOCOLATE_ARROW, HotChocolateArrowRenderer::new);
		EntityRenderers.register(ModEntityTypes.SPOILED_CEREAL_ARROW, SpoiledCerealArrowRenderer::new);
		EntityRenderers.register(ModEntityTypes.HOG_MILK_ARROW, HogMilkArrowRenderer::new);
		EntityRenderers.register(ModEntityTypes.SPOILED_HOG_MILK_ARROW, SpoiledHogMilkArrowRenderer::new);

	}
}