package io.github.padlocks.EndermanOverhaul.core.fabric;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import java.util.function.Supplier;

public class EndermanOverhaulImpl {
    public static void registerBlockRenderType(Supplier<Block> block, RenderLayer type) {
        BlockRenderLayerMap.INSTANCE.putBlock(block.get(), type);
    }
}
