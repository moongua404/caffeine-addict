package com.caffeineaddict.caffeineaddictmode;

import com.caffeineaddict.caffeineaddictmode.block.entity.GrinderBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CaffeineAddictMode.MOD_ID);

    public static final RegistryObject<Block> GRINDER_BLOCK =
            BLOCKS.register("grinder", () ->
                    new GrinderBlock());

    public static void register() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}