package com.momilk.mixin;
import com.momilk.MoMilk;
import com.momilk.block.BaseSaltBlock;
import com.momilk.block.BrushableSaltBlock;
import com.momilk.item.ModItems;
import com.momilk.util.ModLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BrushItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrushItem.class)
public class BrushItemMixin {



    @Inject(at = @At("INVOKE"), method = "onUseTick", cancellable = true)
    public void onUseTick(final Level level, final LivingEntity livingEntity, final ItemStack itemStack, final int ticksRemaining, final CallbackInfo info) {
        if (ticksRemaining >= 0 && livingEntity instanceof Player player) {
            HitResult hitResult = ProjectileUtil.getHitResultOnViewVector(player, EntitySelector.CAN_BE_PICKED, player.blockInteractionRange());;
            if (hitResult instanceof BlockHitResult blockHitResult && hitResult.getType() == HitResult.Type.BLOCK) {
                int timeElapsed = 200 - ticksRemaining + 1;
                boolean isLastTickBeforeBackswing = timeElapsed % 10 == 5;
                if (isLastTickBeforeBackswing) {
                    BlockPos pos = blockHitResult.getBlockPos();
                    BlockState state = level.getBlockState(pos);
                    EquipmentSlot equippedHand = itemStack.equals(player.getItemBySlot(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;

                    if (state.getBlock() instanceof BrushableSaltBlock saltBlock) {
                        if(saltBlock.brushed(state, level, pos))
                        {
                            var key = saltBlock.getLootTable(state);

                            if(key != null) {
                                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), ModLootTables.getItemFromLootTable(key, level, itemStack, state, blockHitResult.getLocation())));
                            }
                            itemStack.hurtAndBreak(1, player, equippedHand);
                        }
                    }
                    else if(state.is(Blocks.GRAVEL) && level.getRandom().nextInt(50) <= 0)
                    {
                        level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), ModLootTables.getItemFromLootTable(ModLootTables.BRUSH_GRAVEL, level, itemStack, state, blockHitResult.getLocation())));
                        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                        itemStack.hurtAndBreak(1, player, equippedHand);
                    }
                    else if(state.is(Blocks.GILDED_BLACKSTONE) && level.getRandom().nextInt(50) <= 0)
                    {
                        level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), ModLootTables.getItemFromLootTable(ModLootTables.BRUSH_GILDED_BLACKSTONE, level, itemStack, state, blockHitResult.getLocation())));
                        level.setBlock(pos, Blocks.BLACKSTONE.defaultBlockState(), 3);
                        itemStack.hurtAndBreak(1, player, equippedHand);
                    }
                }
            }
        }
    }
}
