package net.voidane.testmod.item.custom;

import net.minecraft.core.NonNullList;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.voidane.testmod.item.ModItems;

public class ModArmorItem extends ArmorItem {

    public ModArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {

        if (pEntity instanceof Player player) {
            NonNullList<ItemStack> armorHolder = player.getInventory().armor;

            if (isMatch(armorHolder)) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 1, true, false));
            }
        }
    }

    private boolean isMatch(NonNullList<ItemStack> armorSlots) {

        return armorSlots.get(0).getItem() == ModItems.SAPPHIRE_BOOTS.get() &&
                armorSlots.get(1).getItem() == ModItems.SAPPHIRE_LEGGINGS.get() &&
                armorSlots.get(2).getItem() == ModItems.SAPPHIRE_CHESTPLATE.get() &&
                armorSlots.get(3).getItem() == ModItems.SAPPHIRE_HELMET.get();
    }
}