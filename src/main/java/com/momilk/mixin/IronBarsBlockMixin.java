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

@Mixin(IronBarsBlock.class)
public class IronBarsBlockMixin {

    @Inject(at = @At("INVOKE"), method = "attachsTo", cancellable = true)
    public void attachsTo(final BlockState state, final boolean faceSolid, final CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(!isExceptionForConnection(state) && faceSolid || state.getBlock() instanceof IronBarsBlock || state.is(BlockTags.WALLS) || state.is(ModTags.Blocks.NON_STONE_LIKE_WALLS));
    }

}
