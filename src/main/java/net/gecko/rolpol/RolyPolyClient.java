package net.gecko.rolpol;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.gecko.rolpol.entity.ModEntities;
import net.gecko.rolpol.entity.client.IsopodModel;
import net.gecko.rolpol.entity.client.IsopodRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class RolyPolyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityModelLayerRegistry.registerModelLayer(IsopodModel.ISOPOD, IsopodModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.ISOPOD, IsopodRenderer::new);
    }
}
