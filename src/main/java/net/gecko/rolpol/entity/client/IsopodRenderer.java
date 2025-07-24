package net.gecko.rolpol.entity.client;

import net.gecko.rolpol.RolyPoly;
import net.gecko.rolpol.entity.custom.IsopodEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class IsopodRenderer extends MobEntityRenderer<IsopodEntity, IsopodModel<IsopodEntity>> {
    public IsopodRenderer(EntityRendererFactory.Context context){
        super(context, new IsopodModel<>(context.getPart(IsopodModel.ISOPOD)), 0.5f);
    }

    @Override
    public Identifier getTexture(IsopodEntity entity) {
        return Identifier.of(RolyPoly.MOD_ID, "textures/entity/isopod/isopod.png");
    }

    @Override
    public void render(IsopodEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (livingEntity.isBaby()) {
            matrixStack.scale(0.5f,0.5f,0.5f);
        } else{
            matrixStack.scale(1f,1f,1f);
        }


        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
