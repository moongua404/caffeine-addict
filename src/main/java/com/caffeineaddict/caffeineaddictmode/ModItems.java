package com.caffeineaddict.caffeineaddictmode;

import com.caffeineaddict.caffeineaddictmode.CaffeineAddictMode;
import com.caffeineaddict.caffeineaddictmode.drink.Coffee;
import com.caffeineaddict.caffeineaddictmode.drink.Drink;
import java.util.List;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.CreativeModeTab;
import com.caffeineaddict.caffeineaddictmode.registry.ModBlocks;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
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

    public static final RegistryObject<Item> GROUND_COFFEE =
            ITEMS.register("ground_coffee", () ->
                    new Item(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB)));

    public static final RegistryObject<Item> GRINDER_ITEM =
            ModItems.ITEMS.register("grinder", () ->
                    new BlockItem(ModBlocks.GRINDER_BLOCK.get(), new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB))
            );
    /**
     * Ingredients
     */
    public static final RegistryObject<Item> COFFEE_BEAN = ITEMS.register(
            "coffee_bean",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB))
    );

    public static final RegistryObject<Item> ROASTED_COFFEE_BEAN = ITEMS.register(
            "roasted_coffee_bean",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB))
    );

    public static final RegistryObject<Item> COFFEE_POWDER = ITEMS.register(
            "coffee_powder",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB))
    );

    public static final RegistryObject<Item> MILK = ITEMS.register(
            "milk",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB))
    );

    public static final RegistryObject<Item> ICE = ITEMS.register(
            "ice",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB))
    );

    public static final RegistryObject<Item> HOT_WATER = ITEMS.register(
            "hot_water",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB))
    );

    public static final RegistryObject<Item> COOL_WATER = ITEMS.register(
            "cool_water",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB))
    );

    public static final RegistryObject<Item> CUP = ITEMS.register(
            "cup",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB))
    );

    /**
     * Drink
     */
    public static final RegistryObject<Item> ESPRESSO = ITEMS.register(
            "espresso",
            () -> new Coffee(1, 1, List.of(MobEffects.MOVEMENT_SPEED), 15, 0)
    );

    public static final RegistryObject<Item> ICE_WATER = ITEMS.register(
            "ice_water",
            () -> new Drink(1, 1, List.of(MobEffects.MOVEMENT_SPEED), 15, 0)
    );

    public static final RegistryObject<Item> AMERICANO = ITEMS.register(
            "americano",
            () -> new Coffee(1, 1, List.of(MobEffects.MOVEMENT_SPEED), 15, 0)
    );

    public static final RegistryObject<Item> ICE_AMERICANO = ITEMS.register(
            "ice_americano",
            () -> new Coffee(1, 1, List.of(MobEffects.MOVEMENT_SPEED), 15, 0)
    );

    public static final RegistryObject<Item> LATTE = ITEMS.register(
            "latte",
            () -> new Coffee(1, 1, List.of(MobEffects.MOVEMENT_SPEED), 15, 0)
    );

    public static final RegistryObject<Item> ICE_LATTE = ITEMS.register(
            "ice_latte",
            () -> new Coffee(1, 1, List.of(MobEffects.MOVEMENT_SPEED), 15, 0)
    );

    /**
     * Block
     */
    public static final RegistryObject<Item> WATER_DISPENSER_ITEM =
            ITEMS.register("water_dispenser", () ->
                    new BlockItem(ModBlocks.WATER_DISPENSER.get(),
                            new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB))
            );

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}