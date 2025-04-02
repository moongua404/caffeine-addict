package com.caffeineaddict.caffeineaddictmode;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreativeTab {
    public static final CreativeModeTab CAFFEINE_TAB = new CreativeModeTab("caffeinemodetab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.COCOA_BEANS);
        }
    };
}
