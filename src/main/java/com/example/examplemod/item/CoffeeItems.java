package com.example.examplemod.item;

import com.example.examplemod.CaffeineAddict;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.minecraft.world.item.CreativeModeTab;

public class CoffeeItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CaffeineAddict.MODID);

    public static final RegistryObject<Item> COFFEE_BEAN = ITEMS.register("coffee_bean",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ROASTED_COFFEE_BEAN = ITEMS.register("roasted_coffee_bean",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}