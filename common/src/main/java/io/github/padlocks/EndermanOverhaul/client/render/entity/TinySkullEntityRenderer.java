package io.github.padlocks.EndermanOverhaul.client.render.entity;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import io.github.padlocks.EndermanOverhaul.client.model.entity.TinySkullEntityModel;
import io.github.padlocks.EndermanOverhaul.common.block.TinySkullBlock;
import io.github.padlocks.EndermanOverhaul.core.EndermanOverhaul;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.WallSkullBlock;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.SkullBlockEntityModel;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.Direction;

import java.util.Map;

public class TinySkullEntityRenderer extends SkullBlockEntityRenderer {
    public TinySkullEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        super(ctx);
        this.MODELS = getModels(ctx.getLayerRenderDispatcher());
    }
    private final Map<SkullBlock.SkullType, SkullBlockEntityModel> MODELS;

    private static final Map TEXTURES = (Map) Util.make(Maps.newHashMap(), (map) -> {
        map.put(TinySkullBlock.Type.TINY, new Identifier(EndermanOverhaul.MOD_ID, "textures/entity/badlands/badlands_enderman.png"));
    });

    public static Map<SkullBlock.SkullType, SkullBlockEntityModel> getModels(EntityModelLoader modelLoader) {
        ImmutableMap.Builder<SkullBlock.SkullType, SkullBlockEntityModel> builder = ImmutableMap.builder();
        builder.put(TinySkullBlock.Type.TINY, new TinySkullEntityModel(modelLoader.getModelPart(EntityModelLayers.SKELETON_SKULL)));
        return builder.build();
    }

    @Override
    public void render(SkullBlockEntity skullBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        float g = skullBlockEntity.getTicksPowered(f);
        BlockState blockState = skullBlockEntity.getCachedState();
        boolean bl = blockState.getBlock() instanceof WallSkullBlock;
        Direction direction = bl ? (Direction)blockState.get(WallSkullBlock.FACING) : null;
        float h = 22.5F * (float)(bl ? (2 + direction.getHorizontal()) * 4 : (Integer)blockState.get(SkullBlock.ROTATION));
        SkullBlock.SkullType skullType = ((AbstractSkullBlock)blockState.getBlock()).getSkullType();
        SkullBlockEntityModel skullBlockEntityModel = (SkullBlockEntityModel)this.MODELS.get(skullType);
        RenderLayer renderLayer = getRenderLayer(skullType, skullBlockEntity.getOwner());
        renderSkull(direction, h, g, matrixStack, vertexConsumerProvider, i, skullBlockEntityModel, renderLayer);
    }
}
