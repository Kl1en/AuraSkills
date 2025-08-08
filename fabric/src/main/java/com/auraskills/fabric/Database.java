package com.auraskills.fabric;

import net.fabricmc.loader.api.FabricLoader;
import java.nio.file.*;
import java.sql.*;

public final class Database {
    private static Connection conn;

    private Database() {}

    public static void init() {
        try {
            Path dir = FabricLoader.getInstance().getConfigDir().resolve("auraskills");
            Files.createDirectories(dir);
            String url = "jdbc:sqlite:" + dir.resolve("auraskills.db").toAbsolutePath();
            conn = DriverManager.getConnection(url);
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("""                CREATE TABLE IF NOT EXISTS player_stats(
                  uuid TEXT PRIMARY KEY,
                  data TEXT NOT NULL,
                  updated_at INTEGER NOT NULL
                );
                """);
                st.executeUpdate("""                CREATE TABLE IF NOT EXISTS player_balances(
                  uuid TEXT PRIMARY KEY,
                  balance BIGINT NOT NULL DEFAULT 0
                );
                """);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to init SQLite", e);
        }
    }

    public static Connection conn() { return conn; }
}
