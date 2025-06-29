package com.caffeineaddict.caffeineaddictmode.drink;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class Coffee extends Drink {
    private static final String TAG_CREATOR = "creator";
    private static final String TAG_STAR = "star";

    public Coffee(int nutrition, float saturation, MobEffect effect, int duration, int amplifier) {
        super(nutrition, saturation, effect, duration, amplifier);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);

        CompoundTag tag = stack.getOrCreateTag();
        String creator = tag.contains(TAG_CREATOR) ? tag.getString(TAG_CREATOR) : "unknown";
        int star = tag.contains(TAG_STAR) ? tag.getInt(TAG_STAR) : 0;

        tooltip.add(Component.literal("제작자: " + creator));
        tooltip.add(Component.literal("등급: " + "⭑".repeat(Math.max(0, star))));
    }

    public static ItemStack withMeta(ItemStack stack, String creator, int star) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString(TAG_CREATOR, creator);
        tag.putInt(TAG_STAR, star);
        return stack;
    }
}
