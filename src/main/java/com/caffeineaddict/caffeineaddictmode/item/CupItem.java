package com.caffeineaddict.caffeineaddictmode.item;

import com.caffeineaddict.caffeineaddictmode.client.renderer.CupItemRenderer;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 컵 아이템: NBT에 저장된 재료 정보를 툴팁에 표시.
 */
public class CupItem extends RenderableItem {
    public CupItem(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeClient(@NotNull Consumer<IClientItemExtensions> consumer) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer renderer = new CupItemRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer;
            }
        }));
    }


    /**
     * 툴팁에 재료 정보(NBT의 "ingredients" 태그)를 표시한다.
     *
     * @param stack   현재 아이템 스택
     * @param level   현재 월드 (nullable)
     * @param tooltip 툴팁에 추가할 컴포넌트 리스트
     * @param flag    고급 툴팁 활성 여부
     */
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip,
                                @NotNull TooltipFlag flag) {
        if (stack.hasTag()) {
            assert stack.getTag() != null;
            if (stack.getTag().contains("ingredients", Tag.TAG_LIST)) {
                makeIngredientTag(tooltip, stack.getTag().getList("ingredients", Tag.TAG_STRING));
            }
        }
    }

    private void makeIngredientTag(List<Component> tooltip, ListTag ingredients) {
        tooltip.add(Component.literal("Ingredients:").withStyle(ChatFormatting.GRAY));
        ingredients.forEach(tag ->
                tooltip.add(Component.literal(format(tag.getAsString()))
                        .withStyle(ChatFormatting.GRAY))
        );
    }

    private String format(String raw) {
        return " - "
                + raw.substring(0, 1).toUpperCase()
                + raw.substring(1).replace("_", " ");
    }
}