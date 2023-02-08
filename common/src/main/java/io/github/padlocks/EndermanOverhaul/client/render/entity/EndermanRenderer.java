package io.github.padlocks.EndermanOverhaul.client.render.entity;

import io.github.padlocks.EndermanOverhaul.client.model.entity.EndermanModel;
import io.github.padlocks.EndermanOverhaul.common.entity.base.BaseEnderman;
import io.github.padlocks.EndermanOverhaul.common.entity.base.EndermanType;
import io.github.padlocks.EndermanOverhaul.core.EndermanOverhaul;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.EndermanBlockFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;

import java.util.Random;

public class EndermanRenderer extends GeoEntityRenderer<BaseEnderman> {
    private final Random random = new Random();
    public EndermanRenderer(EntityRendererFactory.Context renderManager, AnimatedGeoModel<BaseEnderman> modelProvider, EndermanType type) {
        super(renderManager, modelProvider);
        //addLayer(new EndermanGlowLayer<>(this));
        this.addLayer(new LayerGlowingAreasGeo<>(this, getGeoModelProvider()::getTextureLocation, getGeoModelProvider()::getModelLocation, RenderLayer::getEyes));
        this.addLayer(new EndermanBlockLayer<>(this, getGeoModelProvider()::getTextureLocation, getGeoModelProvider()::getModelLocation));
        this.shadowRadius = 0.85f;
    }

    @Override
    public void render(BaseEnderman entity, float entityYaw, float partialTicks, MatrixStack matrixStack, VertexConsumerProvider buffer, int packedLight) {
        matrixStack.push();

        BlockState blockState = entity.getCarriedBlock();
        EndermanModel<BaseEnderman> endermanEntityModel = new EndermanModel<>(entity.getEndermanType());
        endermanEntityModel.carryingBlock = blockState != null;
        endermanEntityModel.angry = entity.isAngry();

        matrixStack.pop();
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    public Vec3d getPositionOffset(BaseEnderman entity, float f) {
        if (entity.isAngry()) {
            double d = 0.02;
            return new Vec3d(this.random.nextGaussian() * 0.02, 0.0, this.random.nextGaussian() * 0.02);
        } else {
            return super.getPositionOffset(entity, f);
        }
    }

    @Override
    public Identifier getTextureLocation(@NotNull BaseEnderman entity) {
        return this.modelProvider.getTextureLocation(entity);
    }

    @Override
    public RenderLayer getRenderType(BaseEnderman animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
    }
}
