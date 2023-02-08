package io.github.padlocks.EndermanOverhaul.client.render.entity;

import io.github.padlocks.EndermanOverhaul.common.entity.base.BaseEnderman;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EndermanEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import software.bernie.geckolib3.renderers.geo.layer.AbstractLayerGeo;

import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class EndermanBlockLayer<E extends BaseEnderman> extends AbstractLayerGeo<E> {
    public EndermanBlockLayer(GeoEntityRenderer<E> renderer, Function<E, Identifier> funcGetCurrentTexture,
                              Function<E, Identifier> funcGetCurrentModel) {
        super(renderer, funcGetCurrentTexture, funcGetCurrentModel);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, E entity, float f, float g, float h, float j, float k, float l) {
        BlockState blockState = entity.getCarriedBlock();
        if (blockState != null) {
            matrixStack.push();
            matrixStack.translate(0.0, 0.6875, -0.75);
            matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(20.0F));
            matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(45.0F));
            matrixStack.translate(0.25, 0.1875, 0.25);
            float m = 0.5F;
            matrixStack.scale(-0.5F, -0.5F, 0.5F);
            matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90.0F));
            MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(blockState, matrixStack, vertexConsumerProvider, i, OverlayTexture.DEFAULT_UV);
            matrixStack.pop();
        }
    }
}
