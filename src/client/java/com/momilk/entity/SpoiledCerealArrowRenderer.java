package com.momilk.entity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;

public class SpoiledCerealArrowRenderer extends ArrowRenderer<SpoiledCerealArrow, ArrowRenderState> {
    public static final Identifier NORMAL_ARROW_LOCATION = Identifier.withDefaultNamespace("textures/entity/projectiles/arrow.png");

    public SpoiledCerealArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Identifier getTextureLocation(ArrowRenderState state) {
        return NORMAL_ARROW_LOCATION;
    }

    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }
}
