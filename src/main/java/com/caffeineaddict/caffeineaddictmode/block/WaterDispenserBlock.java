package com.caffeineaddict.caffeineaddictmode.block;

import static net.minecraft.world.level.block.DoorBlock.HALF;

import com.caffeineaddict.caffeineaddictmode.block.entity.WaterDispenserBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class WaterDispenserBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public WaterDispenserBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER
                ? new WaterDispenserBlockEntity(pos, state)
                : null;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        if (pos.getY() < level.getMaxBuildHeight() - 1 &&
                level.getBlockState(pos.above()).canBeReplaced(context)) {
            return this.defaultBlockState()
                    .setValue(FACING, context.getHorizontalDirection().getOpposite())
                    .setValue(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state,
                            LivingEntity placer, ItemStack stack) {
        level.setBlock(pos.above(),
                state.setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos,
                         BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            DoubleBlockHalf half = state.getValue(HALF);
            BlockPos otherPos = (half == DoubleBlockHalf.LOWER) ? pos.above() : pos.below();
            BlockState otherState = level.getBlockState(otherPos);
            if (otherState.getBlock() == this) {
                level.removeBlock(otherPos, false);
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {
        // 엔티티는 항상 하단 블록에만 존재
        BlockPos basePos = (state.getValue(HALF) == DoubleBlockHalf.LOWER) ? pos : pos.below();
        BlockEntity entity = level.getBlockEntity(basePos);

        if (!level.isClientSide && entity instanceof WaterDispenserBlockEntity dispenser) {
            // 물 양동이 들고 있으면
            if (player.getItemInHand(hand).is(Items.WATER_BUCKET)) {
                dispenser.fill();
                player.setItemInHand(hand, new ItemStack(Items.BUCKET));
                return InteractionResult.SUCCESS;
            }

            // 클릭 위치 판별
            Vec3 local = hit.getLocation().subtract(pos.getX(), pos.getY(), pos.getZ());
            Direction facing = state.getValue(FACING);
            Vec3 rotated = rotateVec3(local, facing);

            double x = rotated.x;
            double y = rotated.y;

            if (hit.getDirection() == facing) {
                if (y > 0.6 && y <= 1.0) {
                    if (x >= 0.25 && x <= 0.48) {
                        dispenser.giveCoolWater(player); // 왼쪽
                    } else if (x >= 0.55 && x <= 0.80) {
                        dispenser.giveHotWater(player); // 오른쪽
                    }
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    private Vec3 rotateVec3(Vec3 vec, Direction facing) {
        return switch (facing) {
            case NORTH -> vec;
            case SOUTH -> new Vec3(1 - vec.x, vec.y, 1 - vec.z);
            case WEST  -> new Vec3(vec.z, vec.y, 1 - vec.x);
            case EAST  -> new Vec3(1 - vec.z, vec.y, vec.x);
            default -> vec;
        };
    }
}
