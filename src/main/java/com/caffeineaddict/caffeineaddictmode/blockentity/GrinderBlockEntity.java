package com.caffeineaddict.caffeineaddictmode.blockentity;

import javax.annotation.Nullable;
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

import javax.annotation.Nonnull;

public class GrinderBlockEntity extends BlockEntity {

    private final ItemStackHandler itemHandler = new ItemStackHandler(2); // 0=input, 1=output
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public GrinderBlockEntity(BlockPos pos, BlockState state) {
        super(GrinderBlockEntities.GRINDER.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, GrinderBlockEntity be) {

    }

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }
}
