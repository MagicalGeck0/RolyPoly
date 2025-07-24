package net.gecko.rolpol.entity.client;

import net.gecko.rolpol.RolyPoly;
import net.gecko.rolpol.entity.custom.IsopodEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class IsopodModel<T extends IsopodEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer ISOPOD = new EntityModelLayer(Identifier.of(RolyPoly.MOD_ID, "isopod"), "main");

    private final ModelPart base;


    public IsopodModel(ModelPart root) {
        this.base = root.getChild("base");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData base = modelPartData.addChild("base", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData armor = base.addChild("armor", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

        ModelPartData seg1 = armor.addChild("seg1", ModelPartBuilder.create().uv(0, 3).cuboid(-2.5F, -2.25F, -1.0F, 5.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(12, 14).cuboid(-1.0F, -2.0F, -1.75F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, -3.0F));

        ModelPartData top1_r1 = seg1.addChild("top1_r1", ModelPartBuilder.create().uv(1, 1).cuboid(-3.0F, 0.0F, 0.0F, 5.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -2.25F, -0.25F, -0.2182F, 0.0F, 0.0F));

        ModelPartData sideL1_r1 = seg1.addChild("sideL1_r1", ModelPartBuilder.create().uv(0, 16).cuboid(0.0F, -0.75F, -1.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -1.25F, 0.0F, 0.0F, 0.0F, 0.0436F));

        ModelPartData sideR1_r1 = seg1.addChild("sideR1_r1", ModelPartBuilder.create().uv(0, 16).cuboid(0.0F, -0.75F, -1.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -1.25F, 0.0F, 0.0F, 0.0F, -0.0436F));

        ModelPartData antenna = seg1.addChild("antenna", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, -1.0F));

        ModelPartData antennaR = antenna.addChild("antennaR", ModelPartBuilder.create(), ModelTransform.pivot(-1.5F, 0.0F, 1.0F));

        ModelPartData antRB_r1 = antennaR.addChild("antRB_r1", ModelPartBuilder.create().uv(2, 17).cuboid(0.0F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -0.25F, 0.0F, 0.1745F, 0.0F, 0.0F));

        ModelPartData antTopR = antennaR.addChild("antTopR", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -1.5F));

        ModelPartData antRT_r1 = antTopR.addChild("antRT_r1", ModelPartBuilder.create().uv(2, 17).cuboid(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.75F, 0.0F, 0.25F, -0.1309F, 1.2217F, 0.0F));

        ModelPartData antennaL = antenna.addChild("antennaL", ModelPartBuilder.create(), ModelTransform.pivot(1.5F, 0.0F, 1.0F));

        ModelPartData antLB_r1 = antennaL.addChild("antLB_r1", ModelPartBuilder.create().uv(2, 17).cuboid(0.0F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -0.25F, 0.0F, 0.1745F, 0.0F, 0.0F));

        ModelPartData antTopL = antennaL.addChild("antTopL", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -1.5F));

        ModelPartData antLT_r1 = antTopL.addChild("antLT_r1", ModelPartBuilder.create().uv(2, 17).cuboid(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 0.0F, -0.75F, -0.1309F, -1.2217F, 0.0F));

        ModelPartData legs1 = seg1.addChild("legs1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, 0.5F));

        ModelPartData right1 = legs1.addChild("right1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.25F));

        ModelPartData legR_r1 = right1.addChild("legR_r1", ModelPartBuilder.create().uv(8, 1).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -2.5F, -1.5F, -0.4363F, 0.0F, -1.309F));

        ModelPartData left1 = legs1.addChild("left1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.25F));

        ModelPartData legL_r1 = left1.addChild("legL_r1", ModelPartBuilder.create().uv(8, 1).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -2.5F, -1.5F, -2.7053F, 0.0F, -1.9199F));

        ModelPartData bot1 = legs1.addChild("bot1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.25F));

        ModelPartData L_r1 = bot1.addChild("L_r1", ModelPartBuilder.create().uv(8, 2).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.25F, -3.5F, -1.5F, 0.0F, 0.0F, 0.7854F));

        ModelPartData R_r1 = bot1.addChild("R_r1", ModelPartBuilder.create().uv(8, 2).cuboid(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -3.5F, -1.5F, 0.0F, 0.0F, -0.7854F));

        ModelPartData seg2 = armor.addChild("seg2", ModelPartBuilder.create().uv(0, 6).cuboid(-2.5F, -2.5F, -0.5F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, -1.5F));

        ModelPartData top2_r1 = seg2.addChild("top2_r1", ModelPartBuilder.create().uv(12, 10).cuboid(-3.0F, 0.0F, 0.0F, 5.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -2.5F, 0.0F, -0.2182F, 0.0F, 0.0F));

        ModelPartData sideL2_r1 = seg2.addChild("sideL2_r1", ModelPartBuilder.create().uv(1, 17).cuboid(0.0F, -0.75F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -1.5F, -0.5F, 0.0F, 0.0F, 0.0436F));

        ModelPartData sideR2_r1 = seg2.addChild("sideR2_r1", ModelPartBuilder.create().uv(10, 15).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -1.25F, -0.5F, 0.0F, 0.0F, -0.0436F));

        ModelPartData legs2 = seg2.addChild("legs2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

        ModelPartData right2 = legs2.addChild("right2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData legR_r2 = right2.addChild("legR_r2", ModelPartBuilder.create().uv(8, 1).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -2.5F, -1.5F, -0.2182F, 0.0F, -1.309F));

        ModelPartData left2 = legs2.addChild("left2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData legL_r2 = left2.addChild("legL_r2", ModelPartBuilder.create().uv(8, 1).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -2.5F, -1.5F, -2.9234F, 0.0F, -1.9199F));

        ModelPartData bot2 = legs2.addChild("bot2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData L_r2 = bot2.addChild("L_r2", ModelPartBuilder.create().uv(8, 2).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.25F, -3.5F, -1.5F, 0.0F, 0.0F, 0.7854F));

        ModelPartData R_r2 = bot2.addChild("R_r2", ModelPartBuilder.create().uv(8, 2).cuboid(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -3.5F, -1.5F, 0.0F, 0.0F, -0.7854F));

        ModelPartData seg3 = armor.addChild("seg3", ModelPartBuilder.create().uv(0, 8).cuboid(-2.5F, -2.5F, -0.5F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, -0.5F));

        ModelPartData top3_r1 = seg3.addChild("top3_r1", ModelPartBuilder.create().uv(12, 11).cuboid(-3.0F, 0.0F, 0.0F, 5.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -2.5F, 0.0F, -0.2182F, 0.0F, 0.0F));

        ModelPartData sideL3_r1 = seg3.addChild("sideL3_r1", ModelPartBuilder.create().uv(1, 17).cuboid(0.0F, -0.75F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -1.5F, -0.5F, 0.0F, 0.0F, 0.0436F));

        ModelPartData sideR3_r1 = seg3.addChild("sideR3_r1", ModelPartBuilder.create().uv(16, 0).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -1.25F, -0.5F, 0.0F, 0.0F, -0.0436F));

        ModelPartData legs3 = seg3.addChild("legs3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

        ModelPartData right3 = legs3.addChild("right3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData legR_r3 = right3.addChild("legR_r3", ModelPartBuilder.create().uv(8, 1).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -2.5F, -1.5F, -0.1309F, 0.0F, -1.309F));

        ModelPartData left3 = legs3.addChild("left3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData legL_r3 = left3.addChild("legL_r3", ModelPartBuilder.create().uv(8, 1).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -2.5F, -1.5F, -3.0107F, 0.0F, -1.9199F));

        ModelPartData bot3 = legs3.addChild("bot3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData L_r3 = bot3.addChild("L_r3", ModelPartBuilder.create().uv(8, 2).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.25F, -3.5F, -1.5F, 0.0F, 0.0F, 0.7854F));

        ModelPartData R_r3 = bot3.addChild("R_r3", ModelPartBuilder.create().uv(8, 2).cuboid(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -3.5F, -1.5F, 0.0F, 0.0F, -0.7854F));

        ModelPartData seg4 = armor.addChild("seg4", ModelPartBuilder.create().uv(0, 10).cuboid(-2.5F, -2.5F, -0.5F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 0.5F));

        ModelPartData top4_r1 = seg4.addChild("top4_r1", ModelPartBuilder.create().uv(12, 12).cuboid(-3.0F, 0.0F, 0.0F, 5.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -2.5F, 0.0F, -0.2182F, 0.0F, 0.0F));

        ModelPartData sideL4_r1 = seg4.addChild("sideL4_r1", ModelPartBuilder.create().uv(1, 17).cuboid(0.0F, -0.75F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -1.5F, -0.5F, 0.0F, 0.0F, 0.0436F));

        ModelPartData sideR4_r1 = seg4.addChild("sideR4_r1", ModelPartBuilder.create().uv(4, 16).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -1.25F, -0.5F, 0.0F, 0.0F, -0.0436F));

        ModelPartData legs4 = seg4.addChild("legs4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

        ModelPartData right4 = legs4.addChild("right4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData legR_r4 = right4.addChild("legR_r4", ModelPartBuilder.create().uv(8, 1).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -2.5F, -1.5F, 0.0F, 0.0F, -1.309F));

        ModelPartData left4 = legs4.addChild("left4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData legL_r4 = left4.addChild("legL_r4", ModelPartBuilder.create().uv(8, 1).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -2.5F, -1.5F, 3.1416F, 0.0F, -1.9199F));

        ModelPartData bot4 = legs4.addChild("bot4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData L_r4 = bot4.addChild("L_r4", ModelPartBuilder.create().uv(8, 2).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.25F, -3.5F, -1.5F, 0.0F, 0.0F, 0.7854F));

        ModelPartData R_r4 = bot4.addChild("R_r4", ModelPartBuilder.create().uv(8, 2).cuboid(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -3.5F, -1.5F, 0.0F, 0.0F, -0.7854F));

        ModelPartData seg5 = armor.addChild("seg5", ModelPartBuilder.create().uv(0, 12).cuboid(-2.5F, -2.5F, -0.5F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 1.5F));

        ModelPartData top5_r1 = seg5.addChild("top5_r1", ModelPartBuilder.create().uv(12, 13).cuboid(-3.0F, 0.0F, 0.0F, 5.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -2.5F, 0.0F, -0.2182F, 0.0F, 0.0F));

        ModelPartData sideL5_r1 = seg5.addChild("sideL5_r1", ModelPartBuilder.create().uv(1, 17).cuboid(0.0F, -0.75F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -1.5F, -0.5F, 0.0F, 0.0F, 0.0436F));

        ModelPartData sideR5_r1 = seg5.addChild("sideR5_r1", ModelPartBuilder.create().uv(6, 16).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -1.25F, -0.5F, 0.0F, 0.0F, -0.0436F));

        ModelPartData legs5 = seg5.addChild("legs5", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

        ModelPartData right5 = legs5.addChild("right5", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData legR_r5 = right5.addChild("legR_r5", ModelPartBuilder.create().uv(8, 1).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -2.5F, -1.5F, 0.1309F, 0.0F, -1.309F));

        ModelPartData left5 = legs5.addChild("left5", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData legL_r5 = left5.addChild("legL_r5", ModelPartBuilder.create().uv(8, 1).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -2.5F, -1.5F, 3.0107F, 0.0F, -1.9199F));

        ModelPartData bot5 = legs5.addChild("bot5", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData L_r5 = bot5.addChild("L_r5", ModelPartBuilder.create().uv(8, 2).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.25F, -3.5F, -1.5F, 0.0F, 0.0F, 0.7854F));

        ModelPartData R_r5 = bot5.addChild("R_r5", ModelPartBuilder.create().uv(8, 2).cuboid(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -3.5F, -1.5F, 0.0F, 0.0F, -0.7854F));

        ModelPartData seg6 = armor.addChild("seg6", ModelPartBuilder.create().uv(12, 6).cuboid(-2.5F, -2.5F, -0.5F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 2.5F));

        ModelPartData top6_r1 = seg6.addChild("top6_r1", ModelPartBuilder.create().uv(0, 14).cuboid(-3.0F, 0.0F, 0.0F, 5.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -2.5F, 0.0F, -0.2182F, 0.0F, 0.0F));

        ModelPartData sideL6_r1 = seg6.addChild("sideL6_r1", ModelPartBuilder.create().uv(1, 17).cuboid(0.0F, -0.75F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -1.5F, -0.5F, 0.0F, 0.0F, 0.0436F));

        ModelPartData sideR6_r1 = seg6.addChild("sideR6_r1", ModelPartBuilder.create().uv(8, 16).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -1.25F, -0.5F, 0.0F, 0.0F, -0.0436F));

        ModelPartData legs6 = seg6.addChild("legs6", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

        ModelPartData right6 = legs6.addChild("right6", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData legR_r6 = right6.addChild("legR_r6", ModelPartBuilder.create().uv(8, 1).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -2.5F, -1.5F, 0.2182F, 0.0F, -1.309F));

        ModelPartData left6 = legs6.addChild("left6", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData legL_r6 = left6.addChild("legL_r6", ModelPartBuilder.create().uv(8, 1).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -2.5F, -1.5F, 2.9234F, 0.0F, -1.9199F));

        ModelPartData bot6 = legs6.addChild("bot6", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData L_r6 = bot6.addChild("L_r6", ModelPartBuilder.create().uv(8, 2).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.25F, -3.5F, -1.5F, 0.0F, 0.0F, 0.7854F));

        ModelPartData R_r6 = bot6.addChild("R_r6", ModelPartBuilder.create().uv(8, 2).cuboid(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -3.5F, -1.5F, 0.0F, 0.0F, -0.7854F));

        ModelPartData seg7 = armor.addChild("seg7", ModelPartBuilder.create().uv(12, 8).cuboid(-2.5F, -2.5F, -0.5F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 3.5F));

        ModelPartData top7_r1 = seg7.addChild("top7_r1", ModelPartBuilder.create().uv(14, 3).cuboid(-3.0F, 0.0F, 0.0F, 5.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -2.5F, 0.25F, -0.5672F, 0.0F, 0.0F));

        ModelPartData sideL7_r1 = seg7.addChild("sideL7_r1", ModelPartBuilder.create().uv(1, 17).cuboid(0.0F, -0.75F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -1.5F, -0.5F, 0.0F, 0.0F, 0.0436F));

        ModelPartData sideR7_r1 = seg7.addChild("sideR7_r1", ModelPartBuilder.create().uv(12, 17).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -1.25F, -0.5F, 0.0F, 0.0F, -0.0436F));

        ModelPartData legs7 = seg7.addChild("legs7", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

        ModelPartData right7 = legs7.addChild("right7", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData legR_r7 = right7.addChild("legR_r7", ModelPartBuilder.create().uv(8, 1).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -2.5F, -1.5F, 0.4363F, 0.0F, -1.309F));

        ModelPartData left7 = legs7.addChild("left7", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData legL_r7 = left7.addChild("legL_r7", ModelPartBuilder.create().uv(8, 1).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -2.5F, -1.5F, 2.7053F, 0.0F, -1.9199F));

        ModelPartData bot7 = legs7.addChild("bot7", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 1.5F));

        ModelPartData L_r7 = bot7.addChild("L_r7", ModelPartBuilder.create().uv(8, 2).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.25F, -3.5F, -1.5F, 0.0F, 0.0F, 0.7854F));

        ModelPartData R_r7 = bot7.addChild("R_r7", ModelPartBuilder.create().uv(8, 2).cuboid(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -3.5F, -1.5F, 0.0F, 0.0F, -0.7854F));

        ModelPartData seg8 = armor.addChild("seg8", ModelPartBuilder.create().uv(14, 4).cuboid(-2.0F, -2.25F, -0.5F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 4.5F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void setAngles(IsopodEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(IsopodAnimations.ISOPOD_ANIM_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, IsopodAnimations.ISOPOD_ANIM_IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.rollAnimationState, IsopodAnimations.ISOPOD_ANIM_ROLL, ageInTicks, 1f);

    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        base.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return base;
    }
}

