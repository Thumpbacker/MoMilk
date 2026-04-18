package com.momilk;

import com.momilk.entity.ChocolateMilkArrowRenderer;
import com.momilk.entity.MilkArrowRenderer;
import com.momilk.entity.ModEntityTypes;
import com.momilk.entity.SpoiledMilkArrowRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class MoMilkClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityRenderers.register(ModEntityTypes.MILK_ARROW, MilkArrowRenderer::new);
		EntityRenderers.register(ModEntityTypes.CHOCOLATE_MILK_ARROW, ChocolateMilkArrowRenderer::new);
		EntityRenderers.register(ModEntityTypes.SPOILED_MILK_ARROW, SpoiledMilkArrowRenderer::new);
	}
}