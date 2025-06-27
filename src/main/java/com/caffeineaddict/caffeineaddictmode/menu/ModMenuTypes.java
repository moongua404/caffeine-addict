package com.caffeineaddict.caffeineaddictmode.menu;

import com.caffeineaddict.caffeineaddictmode.CaffeineAddictMode;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.common.extensions.IForgeMenuType;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, CaffeineAddictMode.MOD_ID);

    public static final RegistryObject<MenuType<GrinderMenu>> GRINDER_MENU =
            MENUS.register("grinder_menu",
                    () -> IForgeMenuType.create(GrinderMenu::new));
}