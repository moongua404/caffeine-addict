package com.caffeineaddict.caffeineaddictmode;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
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
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.CAFFEINE_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.3f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 15 * 20, 0), 1.0f)
                            .alwaysEat()
                            .build()
                    )
            )
    );

    public static final RegistryObject<Item> ICE_WATER = ITEMS.register(
            "ice_water",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.CAFFEINE_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.3f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 15 * 20, 0), 1.0f)
                            .alwaysEat()
                            .build()
                    )
            )
    );

    public static final RegistryObject<Item> AMERICANO = ITEMS.register(
            "americano",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.CAFFEINE_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.3f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 15 * 20, 0), 1.0f)
                            .alwaysEat()
                            .build()
                    )
            )
    );

    public static final RegistryObject<Item> ICE_AMERICANO = ITEMS.register(
            "ice_americano",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.CAFFEINE_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.3f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 15 * 20, 0), 1.0f)
                            .alwaysEat()
                            .build()
                    )
            )
    );

    public static final RegistryObject<Item> LATTE = ITEMS.register(
            "latte",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.CAFFEINE_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.3f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 15 * 20, 0), 1.0f)
                            .alwaysEat()
                            .build()
                    )
            )
    );

    public static final RegistryObject<Item> ICE_LATTE = ITEMS.register(
            "ice_latte",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.CAFFEINE_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.3f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 15 * 20, 0), 1.0f)
                            .alwaysEat()
                            .build()
                    )
            )
    );

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
