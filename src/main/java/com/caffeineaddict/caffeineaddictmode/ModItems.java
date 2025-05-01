package com.caffeineaddict.caffeineaddictmode;

import com.example.examplemod.CaffeineAddict;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final String MOD_ID = CaffeineAddictMode.MOD_ID;

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> PLACEHOLDER_ITEM = ITEMS.register(
            "placeholder_item",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB))
    );

    public static final RegistryObject<Item> COFFEE_BEAN =
            ITEMS.register("coffee_bean", () -> new Item(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB)));

    public static final RegistryObject<Item> ROASTED_COFFEE_BEAN =
            ITEMS.register("roasted_coffee_bean", () -> new Item(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB)));


    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}