package com.caffeineaddict.caffeineaddictmode.item;

import java.util.function.Consumer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.fml.DistExecutor;

/**
 * 렌더러가 필요한 아이템의 베이스 클래스.
 */
public class RenderableItem extends Item {
    public RenderableItem(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            consumer.accept(new IClientItemExtensions() {
                private final BlockEntityWithoutLevelRenderer renderer = getRenderer();

                @Override
                public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                    return renderer;
                }
            });
        });
    }

    /**
     * 각 아이템에서 이 메서드를 오버라이드하여 렌더러를 지정.
     */
    protected BlockEntityWithoutLevelRenderer getRenderer() {
        throw new UnsupportedOperationException("getRenderer() must be overridden");
    }
}
