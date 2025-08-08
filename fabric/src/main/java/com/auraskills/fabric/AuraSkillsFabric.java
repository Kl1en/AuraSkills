package com.auraskills.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

public class AuraSkillsFabric implements ModInitializer {
    public static final String MOD_ID = "auraskills";

    @Override
    public void onInitialize() {
        // Init subsystems
        Database.init();
        ConfigBridge.load();

        // Register commands
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("skills")
                .executes(ctx -> {
                    Menus.openSkills(ctx.getSource().getPlayer());
                    return 1;
                })
                .then(CommandManager.literal("reload")
                    .requires(src -> Perms.has(src.getPlayer(), "auraskills.admin.reload"))
                    .executes(ctx -> {
                        ConfigBridge.reload();
                        ctx.getSource().sendFeedback(() -> Text.literal("[AuraSkills] Reloaded"), false);
                        return 1;
                    }))
            );
        });

        // Join/Leave hooks
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            Hooks.onPlayerJoin(handler.getPlayer());
        });
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            Hooks.onPlayerLeave(handler.getPlayer());
        });

        // Block break -> XP
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
            Hooks.onBlockBreak(player, state);
        });

        // Attack -> XP
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            return Hooks.onAttack(player, entity);
        });

        // Server tick
        ServerTickEvents.END_SERVER_TICK.register(server -> Hooks.onServerTick(server));
    }
}
