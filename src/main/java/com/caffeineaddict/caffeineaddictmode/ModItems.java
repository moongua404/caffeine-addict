package com.caffeineaddict.caffeineaddictmode;

import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "caffeineaddictmode");

    public static final RegistryObject<Item> PLACEHOLDER_ITEM = ITEMS.register(
            "placeholder_item",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB))
    );

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
