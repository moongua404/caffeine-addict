package com.caffeineaddict.caffeineaddictmode.client.renderer;


import java.util.function.Consumer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

/**
 * 클라이언트에서만 호출되도록 분리된 렌더러 공급자
 */
public class ClientItemRendererProvider {
    public static void registerCupRenderer(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer renderer = new CupItemRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer;
            }
        });
    }
}