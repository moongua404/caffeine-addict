package com.caffeineaddict.caffeineaddictmode.block.entity;

import com.caffeineaddict.caffeineaddictmode.menu.WaterDispenserMenu;
import com.caffeineaddict.caffeineaddictmode.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WaterDispenserBlockEntity extends BlockEntity implements MenuProvider {
    public WaterDispenserBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WATER_DISPENSER.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("정수기");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new WaterDispenserMenu(id, playerInventory, worldPosition);
    }
}
