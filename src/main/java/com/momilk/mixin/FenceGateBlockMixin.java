package com.momilk.mixin;

import com.momilk.util.ModTags;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.level.block.Block.isExceptionForConnection;

@Mixin(FenceGateBlock.class)
public class FenceGateBlockMixin {

    @Inject(at = @At("INVOKE"), method = "isWall", cancellable = true)
    private void isWall(final BlockState state, final CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(state.is(BlockTags.WALLS) || state.is(ModTags.Blocks.NON_STONE_LIKE_WALLS));
    }

}
