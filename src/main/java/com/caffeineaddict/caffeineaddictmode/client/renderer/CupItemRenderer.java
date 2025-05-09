package com.caffeineaddict.caffeineaddictmode.client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class CupItemRenderer extends BlockEntityWithoutLevelRenderer {

    private static final ResourceLocation BASE_TEXTURE = new ResourceLocation("caffeineaddictmode",
            "textures/item/empty_cup.png");

    public CupItemRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack stack, TransformType transformType,
                             PoseStack poseStack, MultiBufferSource buffer,
                             int light, int overlay) {

        // 1. 기본 컵 텍스처 렌더링
        renderTextureDirect(BASE_TEXTURE, poseStack, buffer, light, overlay, 0.0F);

        // 2. 오버레이 렌더링 (NBT 기반)
        if (stack.hasTag() && stack.getTag().contains("ingredients", Tag.TAG_LIST)) {
            ListTag ingredients = stack.getTag().getList("ingredients", Tag.TAG_STRING);
            float zOffset = 0.01f;

            for (Tag tag : ingredients) {
                String name = tag.getAsString();
                ResourceLocation texture = new ResourceLocation("caffeineaddictmode", "textures/item/" + name + ".png");
                renderTextureDirect(texture, poseStack, buffer, light, overlay, zOffset);
                zOffset += 0.001f;
            }
        }
    }

    private void renderTextureDirect(ResourceLocation texture, PoseStack poseStack,
                                     MultiBufferSource buffer, int light, int overlay, float zOffset) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.0F, zOffset);

        RenderSystem.setShaderTexture(0, texture);
        VertexConsumer vc = buffer.getBuffer(RenderType.entityTranslucent(texture));

        Matrix4f mat = poseStack.last().pose();

        // 렌더 영역: 1x1 정사각형 (아이템 전체)
        vc.vertex(mat, 0, 0, 0).color(255, 255, 255, 255).uv(0, 1)
                .overlayCoords(overlay).uv2(light).normal(0, 0, 1).endVertex();
        vc.vertex(mat, 1, 0, 0).color(255, 255, 255, 255).uv(1, 1)
                .overlayCoords(overlay).uv2(light).normal(0, 0, 1).endVertex();
        vc.vertex(mat, 1, 1, 0).color(255, 255, 255, 255).uv(1, 0)
                .overlayCoords(overlay).uv2(light).normal(0, 0, 1).endVertex();
        vc.vertex(mat, 0, 1, 0).color(255, 255, 255, 255).uv(0, 0)
                .overlayCoords(overlay).uv2(light).normal(0, 0, 1).endVertex();

        poseStack.popPose();
    }
}
