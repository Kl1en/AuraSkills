package com.auraskills.fabric;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public final class Menus {
    private Menus() {}

    public static void openSkills(ServerPlayerEntity player) {
        player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inv, p) -> {
            var handler = new GenericContainerScreenHandler(ScreenHandlerType.GENERIC_9X3, syncId, inv, new net.minecraft.inventory.SimpleInventory(27), 3);
            // Fill demo contents
            for (int i = 0; i < 27; i++) {
                ItemStack stack = new ItemStack(Items.PAPER);
                stack.setCustomName(Text.literal("Skill Slot " + (i+1)));
                handler.getInventory().setStack(i, stack);
            }
            return handler;
        }, Text.literal("AuraSkills")));
    }
}
