package com.caffeineaddict.caffeineaddictmode.registry;

import com.caffeineaddict.caffeineaddictmode.CaffeineAddictMode;
import com.caffeineaddict.caffeineaddictmode.block.entity.WaterDispenserBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CaffeineAddictMode.MOD_ID);

    public static final RegistryObject<BlockEntityType<WaterDispenserBlockEntity>> WATER_DISPENSER =
            BLOCK_ENTITIES.register("water_dispenser", () ->
                    BlockEntityType.Builder.of(WaterDispenserBlockEntity::new,
                            ModBlocks.WATER_DISPENSER.get()).build(null));
}