package com.caffeineaddict.caffeineaddictmode;

import com.caffeineaddict.caffeineaddictmode.item.CupItem;
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

    public static final RegistryObject<Item> EMPTY_CUP = ITEMS.register(
            "empty_cup",
            () -> new CupItem(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB))
    );

    public static final RegistryObject<Item> COFFEE_BEANS = ITEMS.register("coffee_beans",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB))
    );

    public static final RegistryObject<Item> ICE = ITEMS.register("ice",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB))
    );

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
