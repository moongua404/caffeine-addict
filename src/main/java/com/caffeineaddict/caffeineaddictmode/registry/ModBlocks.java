package com.caffeineaddict.caffeineaddictmode.registry;

import com.caffeineaddict.caffeineaddictmode.CaffeineAddictMode;
import com.caffeineaddict.caffeineaddictmode.block.WaterDispenserBlock;
import com.caffeineaddict.caffeineaddictmode.block.entity.GrinderBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CaffeineAddictMode.MOD_ID);
//    public static final DeferredRegister<Block> BLOCKS =
//            DeferredRegister.create(ForgeRegistries.BLOCKS, CaffeineAddictMode.MOD_ID);

    public static final RegistryObject<Block> WATER_DISPENSER =
            BLOCKS.register("water_dispenser",
                    () -> new WaterDispenserBlock(BlockBehaviour.Properties.of(Material.METAL).strength(3.0f)));

    public static final RegistryObject<Block> GRINDER_BLOCK =
            BLOCKS.register("grinder", () ->
                    new GrinderBlock());
    // 그라인더 블록 아이템 등록
//    public static final RegistryObject<Item> GRINDER_ITEM =
//            ModItems.ITEMS.register("grinder", () ->
//                    new BlockItem(GRINDER_BLOCK.get(), new Item.Properties().tab(ModCreativeTab.CAFFEINE_TAB)));
    public static void register() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}