package com.caffeineaddict.caffeineaddictmode;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class ModCreativeTab {
    public static final CreativeModeTab CAFFEINE_TAB = new CreativeModeTab("caffeinemodetab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Items.COCOA_BEANS);
        }
    };
}
