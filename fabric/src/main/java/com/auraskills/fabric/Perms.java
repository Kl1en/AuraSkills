package com.auraskills.fabric;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.network.ServerPlayerEntity;

public final class Perms {
    private Perms() {}
    public static boolean has(ServerPlayerEntity player, String node) {
        if (player == null) return true; // console
        return Permissions.check(player, node, true);
    }
}
