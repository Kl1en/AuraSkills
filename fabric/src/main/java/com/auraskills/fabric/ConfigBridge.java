package com.auraskills.fabric;

import net.fabricmc.loader.api.FabricLoader;
import java.nio.file.*;

public final class ConfigBridge {
    private ConfigBridge() {}

    public static void load() {
        // TODO: wire to common's config system if present; ensure files under config/auraskills/
        Path dir = FabricLoader.getInstance().getConfigDir().resolve("auraskills");
        try { Files.createDirectories(dir); } catch (Exception ignored) {}
    }

    public static void reload() {
        load();
    }
}
