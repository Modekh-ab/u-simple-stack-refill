package net.modekh.ussrefill.utils;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EventUtils {
    public static void getNewItemStack(Player player, ItemStack stack, EquipmentSlot slot, InteractionHand hand,
                                       boolean allowCreative, boolean isThrowable) {
        Inventory inventory = player.getInventory();
        Item item = stack.getItem();

        if (!allowCreative && stack.getCount() > 1) {
            return;
        }

        for (int i = 35; i >= 0; i--) {
            ItemStack stackInSlot = inventory.getItem(i);

            if (!stack.equals(stackInSlot)) {
                Item itemInSlot = stackInSlot.getItem();

                if (item.equals(itemInSlot)) {
                    player.setItemInHand(hand, !isThrowable ? stackInSlot : new ItemStack(itemInSlot, stackInSlot.getCount() + 1));
                    player.getSlot(i).set(ItemStack.EMPTY);

                    break;
                }
            }
        }

        inventory.setChanged();
    }

    public static void getNewItemStack(Player player, ItemStack stack, EquipmentSlot slot, InteractionHand hand, boolean allowCreative) {
        getNewItemStack(player, stack, slot, hand, allowCreative, false);
    }
}
