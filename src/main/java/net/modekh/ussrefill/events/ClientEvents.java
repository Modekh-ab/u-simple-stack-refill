package net.modekh.ussrefill.events;

import com.google.common.base.Throwables;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.modekh.ussrefill.utils.EventUtils;
import net.modekh.ussrefill.utils.Reference;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
    private ClientEvents() {}

    @SubscribeEvent
    public static void onItemBreak(PlayerDestroyItemEvent event) {
        InteractionHand hand = Objects.equals(event.getSlot(), EquipmentSlot.MAINHAND)
                ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;

        EventUtils.getNewItemStack(event.getEntity(), event.getOriginal(),
                event.getSlot(), hand, false);
    }

    @SubscribeEvent
    public static void onItemToss(ItemTossEvent event) {
        Player player = event.getPlayer();
        ItemStack stack = event.getEntity().getItem();

        if (!player.getMainHandItem().isEmpty() || stack.getCount() > 1) {
            return;
        }

        EventUtils.getNewItemStack(player, stack,
                player.getEquipmentSlotForItem(stack), player.getUsedItemHand(),
                true);
    }

    @SubscribeEvent
    public static InteractionResultHolder<ItemStack> onItemRightClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();

        if (player.isCreative()) {
            return InteractionResultHolder.pass(stack);
        }

        Item item = stack.getItem();

        if (item instanceof ProjectileItem
                || item instanceof EnderpearlItem || item instanceof EnderEyeItem) {
            EventUtils.getNewItemStack(player, stack,
                    player.getEquipmentSlotForItem(stack), player.getUsedItemHand(),
                    false, true);
        }

        return InteractionResultHolder.pass(stack);
    }
}
