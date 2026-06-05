package com.momilk.mixin;

import com.momilk.util.ModTags;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.level.block.Block.isExceptionForConnection;

@Mixin(WallBlock.class)
public class WallBlockMixin {

    @Inject(at = @At("INVOKE"), method = "connectsTo", cancellable = true)
    private void connectsTo(final BlockState state, final boolean faceSolid, final Direction direction, final CallbackInfoReturnable<Boolean> info) {
        Block block = state.getBlock();
        boolean connectedFenceGate = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(state, direction);
        info.setReturnValue(state.is(BlockTags.WALLS) || !isExceptionForConnection(state) && faceSolid || block instanceof IronBarsBlock || connectedFenceGate || state.is(ModTags.Blocks.NON_STONE_LIKE_WALLS));
    }

}
