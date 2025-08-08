package com.auraskills.fabric;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.ActionResult;

public final class Hooks {
    private Hooks() {}

    public static void onPlayerJoin(ServerPlayerEntity player) {
        // TODO: call into common to load profile etc.
    }

    public static void onPlayerLeave(ServerPlayerEntity player) {
        // TODO: save profile
    }

    public static void onBlockBreak(ServerPlayerEntity player, BlockState state) {
        // TODO: translate to common XP event
    }

    public static ActionResult onAttack(PlayerEntity player, Entity target) {
        // TODO: translate to common XP event
        return ActionResult.PASS;
    }

    public static void onServerTick(MinecraftServer server) {
        // TODO: common tick
    }
}
