package com.caffeineaddict.caffeineaddictmode.blockentity;

import com.caffeineaddict.caffeineaddictmode.menu.GrinderMenu;
import com.caffeineaddict.caffeineaddictmode.ModItems;

import javax.annotation.Nullable;
import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.MenuProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class GrinderBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(2); // 0=input, 1=output
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private int progress = 0;

    public GrinderBlockEntity(BlockPos pos, BlockState state) {
        super(GrinderBlockEntities.GRINDER.get(), pos, state);
    }

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    public Component getDisplayName() {
        return Component.literal("Grinder");
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    public int getProgress() {
        return this.progress;
    }
    public int getMaxProgress() {
        return 100;
    }
    public int getScaledProgress() {
        int maxProgress = 100; // 전체 작업 시간
        int progressBarWidth = 24; // 진행 바 너비(px)
        return (progress * progressBarWidth) / maxProgress;
    }

    @Nullable
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new GrinderMenu(id, playerInventory, this);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, GrinderBlockEntity entity) {
        if (level.isClientSide) return;

        ItemStack input = entity.itemHandler.getStackInSlot(0);
        ItemStack output = entity.itemHandler.getStackInSlot(1);

        if (input.getItem() == ModItems.ROASTED_COFFEE_BEAN.get()) {
            if (output.isEmpty() || (output.getItem() == ModItems.GROUND_COFFEE.get() && output.getCount() < output.getMaxStackSize())) {
                entity.progress++;
                if (entity.progress >= 100) {
                    // 커피콩 소비
                    entity.itemHandler.extractItem(0, 1, false);
                    // 커피 가루 추가
                    entity.itemHandler.insertItem(1, new ItemStack(ModItems.GROUND_COFFEE.get(), 1), false);
                    entity.progress = 0;
                }
            } else {
                entity.progress = 0; // 출력 슬롯이 가득 찼다면 리셋
            }
        } else {
            entity.progress = 0; // 입력이 올바르지 않으면 리셋
        }

        entity.setChanged(); // 저장 플래그
    }
}
