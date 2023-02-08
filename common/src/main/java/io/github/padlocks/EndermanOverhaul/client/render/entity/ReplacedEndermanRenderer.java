package io.github.padlocks.EndermanOverhaul.client.render.entity;

import io.github.padlocks.EndermanOverhaul.client.model.entity.ReplacedEndermanModel;
import io.github.padlocks.EndermanOverhaul.common.entity.ReplacedEnderman;
import io.github.padlocks.EndermanOverhaul.core.EndermanOverhaul;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.EndermanBlockFeatureRenderer;
import software.bernie.geckolib3.renderers.geo.GeoReplacedEntityRenderer;
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;

@Environment(EnvType.CLIENT)
public class ReplacedEndermanRenderer extends GeoReplacedEntityRenderer<ReplacedEnderman> {
    public ReplacedEndermanRenderer(EntityRendererFactory.Context context) {
        super(context, new ReplacedEndermanModel(), new ReplacedEnderman());
        EndermanOverhaul.registerReplacedEntity(ReplacedEnderman.class, this);
        //this.addLayer(new LayerGlowingAreasGeo(this));
        //this.addLayer(new EndermanBlockFeatureRenderer(this));
    }

//    @Override
//    public RenderLayer getRenderType(Object animatable, float partialTicks, MatrixStack stack,
//                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
//                                     Identifier textureLocation) {
//        return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
//    }
}
