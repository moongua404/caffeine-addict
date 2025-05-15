package com.caffeineaddict.caffeineaddictmode.client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CupItemRenderer extends BlockEntityWithoutLevelRenderer {

    @SuppressWarnings("removal")
    private static final ResourceLocation BASE_TEXTURE = new ResourceLocation(
            "caffeineaddictmode", "textures/item/empty_cup.png"
    );

    private final Map<Integer, List<ResourceLocation>> textureCache = new HashMap<>();

    @SuppressWarnings("DataFlowIssue")
    public CupItemRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack stack, @NotNull TransformType transformType,
                             @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer,
                             int light, int overlay) {

        // 기본 컵 텍스처
        renderTexture(BASE_TEXTURE, poseStack, buffer, light, overlay, 0f, 0f, 0f, 1.0f);

        // 재료 텍스처 오버레이
        if (stack.hasTag() && stack.getTag() != null &&
                stack.getTag().contains("ingredients", Tag.TAG_LIST)) {

            ListTag ingredients = stack.getTag().getList("ingredients", Tag.TAG_STRING);
            int count = ingredients.size();

            //캐싱
            int hash = ingredients.hashCode();
            @SuppressWarnings("removal")
            List<ResourceLocation> textures = textureCache.computeIfAbsent(hash, h -> {
                List<ResourceLocation> result = new ArrayList<>();
                for (int i = 0; i < ingredients.size(); i++) {
                    String name = ingredients.getString(i);
                    result.add(new ResourceLocation("caffeineaddictmode", "textures/item/" + name + ".png"));
                }
                return result;
            });

            for (int i = 0; i < textures.size(); i++) {
                float scale = 0.6f;
                float xOffset = 0.25f + (i % 2 == 0 ? 1 : -1) * 0.2f;
                float yOffset = computeYOffset(i, textures.size());
                float zOffset = 0f;

                renderTexture(textures.get(i), poseStack, buffer, light, overlay,
                        xOffset, yOffset, zOffset, scale);
            }
        }
    }

    /**
     * 각 재료의 Y축 오프셋 계산
     */
    private float computeYOffset(int index, int total) {
        float baseY = 0.0f;
        float heightRange = 0.5f;
        return baseY + (heightRange * index / Math.max(1, total - 1));
    }

    /**
     * 주어진 텍스처를 1x1 정사각형으로 렌더
     */
    private void renderTexture(ResourceLocation texture, PoseStack poseStack,
                               MultiBufferSource buffer, int light, int overlay,
                               float x, float y, float z, float scale) {

        poseStack.pushPose();
        poseStack.translate(x, y, z);
        poseStack.scale(scale, scale, scale);

        RenderSystem.setShaderTexture(0, texture);
        VertexConsumer vc = buffer.getBuffer(RenderType.entityNoOutline(texture));
        Matrix4f mat = poseStack.last().pose();

        vc.vertex(mat, 0, 0, 0).color(255, 255, 255, 255).uv(0, 1)
                .overlayCoords(overlay).uv2(light).normal(0, 0, 1).endVertex();
        vc.vertex(mat, 1, 0, 0).color(255, 255, 255, 255).uv(1, 1)
                .overlayCoords(overlay).uv2(light).normal(0, 0, 1).endVertex();
        vc.vertex(mat, 1, 1, 0).color(255, 255, 255, 255).uv(1, 0)
                .overlayCoords(overlay).uv2(light).normal(0, 0, 1).endVertex();
        vc.vertex(mat, 0, 1, 0).color(255, 255, 255, 255).uv(0, 0)
                .overlayCoords(overlay).uv2(light).normal(0, 0, 1).endVertex();

        vc.vertex(mat, 0, 0, 0).color(255, 255, 255, 255).uv(0, 1)
                .overlayCoords(overlay).uv2(light).normal(0, 0, -1).endVertex();
        vc.vertex(mat, 0, 1, 0).color(255, 255, 255, 255).uv(0, 0)
                .overlayCoords(overlay).uv2(light).normal(0, 0, -1).endVertex();
        vc.vertex(mat, 1, 1, 0).color(255, 255, 255, 255).uv(1, 0)
                .overlayCoords(overlay).uv2(light).normal(0, 0, -1).endVertex();
        vc.vertex(mat, 1, 0, 0).color(255, 255, 255, 255).uv(1, 1)
                .overlayCoords(overlay).uv2(light).normal(0, 0, -1).endVertex();

        poseStack.popPose();
    }
}
