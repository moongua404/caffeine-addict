// WaterDispenserMenu.java
package com.caffeineaddict.caffeineaddictmode.menu;

import com.caffeineaddict.caffeineaddictmode.ModItems;
import com.caffeineaddict.caffeineaddictmode.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class WaterDispenserMenu extends AbstractContainerMenu {

    private final ContainerLevelAccess access;
    private final SimpleContainer container;

    public WaterDispenserMenu(int id, Inventory playerInv, BlockPos pos) {
        super(ModMenus.WATER_DISPENSER.get(), id);
        this.access = ContainerLevelAccess.create(playerInv.player.level, pos);
        this.container = new SimpleContainer(2);

        this.addSlot(new Slot(this.container, 0, 62, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                super.onTake(player, stack);
                this.set(new ItemStack(ModItems.HOT_WATER.get()));
            }

            @Override
            public boolean mayPickup(Player player) {
                return true;
            }
        });

        this.addSlot(new Slot(this.container, 1, 98, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                super.onTake(player, stack);
                this.set(new ItemStack(ModItems.ICE_WATER.get()));
            }

            @Override
            public boolean mayPickup(Player player) {
                return true;
            }
        });

        container.setItem(0, new ItemStack(ModItems.HOT_WATER.get()));
        container.setItem(1, new ItemStack(ModItems.ICE_WATER.get()));

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInv, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}
