package net.voidane.testmod.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.voidane.testmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

public class MetalDetectorItem extends Item {

    public MetalDetectorItem(Properties pProperties)
    {
        super(pProperties);
    }

    /**
     * Tells the player if there are any ores under them from where they clicked
     * @param pContext
     * @return
     */
    @Override
    public InteractionResult useOn(UseOnContext pContext)
    {
        if(!pContext.getLevel().isClientSide()) {

            // Position clicked
            BlockPos blockPos = pContext.getClickedPos();

            // The player
            Player player = pContext.getPlayer();
            pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                    pPlayer -> pPlayer.broadcastBreakEvent(pPlayer.getUsedItemHand()));

            boolean found = false;

            for ( int i = 0 ; i <= blockPos.getY() + 64 ; i++ )
            {
                // The block in the world
                BlockState blockState = pContext.getLevel().getBlockState(
                        new BlockPos(blockPos.getX(),blockPos.getY()-i, blockPos.getZ()));

                if (isValuable(blockState))
                {
                    found = true;
                    // Send message to player
                    player.sendSystemMessage(Component.literal("Valuable Block: " +
                            I18n.get(blockState.getBlock().getDescriptionId()) + " at " +
                            "(" + blockPos.getX() + "," + (blockPos.getY()-i) + "," + blockPos.getZ() + ")"));
                }
                // System.out.println("Checked block pos" + (blockPos.getY()-i) + " got block: " +
                //        I18n.get(blockState.getBlock().getDescriptionId()));
            }

        if (!found)
            player.sendSystemMessage(Component.literal("No valuables found"));

        }
        return InteractionResult.SUCCESS;
    }

    /**
     * Sends the player a message of what biome they are in
     * @param pStack
     * @param pLevel
     * @param pState
     * @param pPos
     * @param pMiningEntity
     * @return
     */
    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pMiningEntity)
    {
        BlockState block = pLevel.getBlockState(pPos);
        Player player = null;

        if (pMiningEntity instanceof Player) {
            player = (Player) pMiningEntity;
        } else {
            return false;
        }

        player.sendSystemMessage(Component.literal("Biome: " + pLevel.getBiome(pPos)));

        return false;
    }

    private boolean isValuable(BlockState block)
    {
        return block.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        pTooltipComponents.add(Component.translatable("testmod.block_translatable.metal_detector.tooltip.1"));
    }
}
