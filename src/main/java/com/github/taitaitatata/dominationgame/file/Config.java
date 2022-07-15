package com.github.taitaitatata.dominationgame.file;

import com.github.taitaitatata.dominationgame.DominationGamePlugin;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;

public final class Config {

    private final DominationGamePlugin instance;
    private FileConfiguration config;

    private String DATABASE_FILE_NAME;
    private String DATABASE_TABLE_PREFIX;

    private int DEFAULTS_ARENA_PLAYERS_MIN;
    private int DEFAULTS_ARENA_PLAYERS_MAX;
    private int DEFAULTS_ARENA_RESPAWNAT;

    private double TEAM_PUBLIC_HEALTH;
    private String TEAM_ONE_NAME;
    private Color TEAM_ONE_COLOR;
    private String TEAM_TWO_NAME;
    private Color TEAM_TWO_COLOR;

    public Config(DominationGamePlugin instance) {
        this.instance = instance;
        this.config = instance.getConfig();

        DATABASE_FILE_NAME = config.getString("database.file-name", "database.db");
        DATABASE_TABLE_PREFIX = config.getString("database.table-prefix", "domination_");

        DEFAULTS_ARENA_PLAYERS_MIN = config.getInt("defaults.arena.players.min", 2);
        DEFAULTS_ARENA_PLAYERS_MAX = config.getInt("defaults.arena.players.max", 16);
        DEFAULTS_ARENA_RESPAWNAT = config.getInt("defaults.arena.respawnAt", 5);

        TEAM_PUBLIC_HEALTH = config.getDouble("team.public.health", 100.0);
        TEAM_ONE_NAME = config.getString("team.one.name", "RED");
        TEAM_ONE_COLOR = config.getColor("team.one.color", Color.RED);
        TEAM_TWO_NAME = config.getString("team.two.name", "BLUE");
        TEAM_TWO_COLOR = config.getColor("team.two.color", Color.BLUE);
    }

    public void save() {
        config.set("database.file-name", DATABASE_FILE_NAME);
        config.set("database.table-prefix", DATABASE_TABLE_PREFIX);

        config.set("defaults.arena.players.min", DEFAULTS_ARENA_PLAYERS_MIN);
        config.set("defaults.arena.players.max", DEFAULTS_ARENA_PLAYERS_MAX);

        config.set("defaults.arena.respawnAt", DEFAULTS_ARENA_RESPAWNAT);
    }

    public void reload() {
        instance.reloadConfig();
        config = instance.getConfig();

        DATABASE_FILE_NAME = config.getString("database.file-name", "database.db");
        DATABASE_TABLE_PREFIX = config.getString("database.table-prefix", "domination_");

        DEFAULTS_ARENA_PLAYERS_MIN = config.getInt("defaults.arena.players.min", 2);
        DEFAULTS_ARENA_PLAYERS_MAX = config.getInt("defaults.arena.players.max", 16);

        DEFAULTS_ARENA_RESPAWNAT = config.getInt("defaults.arena.respawnAt", 5);
    }

    public int getDEFAULTS_ARENA_PLAYERS_MAX() {
        return DEFAULTS_ARENA_PLAYERS_MAX;
    }

    public int getDEFAULTS_ARENA_PLAYERS_MIN() {
        return DEFAULTS_ARENA_PLAYERS_MIN;
    }

    public int getDEFAULTS_ARENA_RESPAWNAT() {
        return DEFAULTS_ARENA_RESPAWNAT;
    }

    public String getDATABASE_FILE_NAME() {
        return DATABASE_FILE_NAME;
    }

    public String getDATABASE_TABLE_PREFIX() {
        return DATABASE_TABLE_PREFIX;
    }

    public Color getTEAM_ONE_COLOR() {
        return TEAM_ONE_COLOR;
    }

    public Color getTEAM_TWO_COLOR() {
        return TEAM_TWO_COLOR;
    }

    public double getTEAM_PUBLIC_HEALTH() {
        return TEAM_PUBLIC_HEALTH;
    }

    public String getTEAM_ONE_NAME() {
        return TEAM_ONE_NAME;
    }

    public String getTEAM_TWO_NAME() {
        return TEAM_TWO_NAME;
    }
}
