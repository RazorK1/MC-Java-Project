package com.example.ultracreeper;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class UltraCreeperClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(UltraCreeperMod.ULTRA_CREEPER, (context) ->
            new MobEntityRenderer<>(context, new CreeperEntityModel<>(context.getPart(EntityModelLayers.CREEPER)), 0.5f) {
                @Override
                public Identifier getTexture(UltraCreeperEntity entity) {
                    return new Identifier("ultracreeper", "textures/entity/ultra_creeper.png");
                }
            }
        );
    }
}
