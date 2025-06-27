package com.caffeineaddict.caffeineaddictmode.registry;

import com.caffeineaddict.caffeineaddictmode.CaffeineAddictMode;
import com.caffeineaddict.caffeineaddictmode.menu.WaterDispenserMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, CaffeineAddictMode.MOD_ID);

    public static final RegistryObject<MenuType<WaterDispenserMenu>> WATER_DISPENSER =
            MENUS.register("water_dispenser", () ->
                    new MenuType<>((id, inv) -> new WaterDispenserMenu(id, inv, BlockPos.ZERO)));
}
