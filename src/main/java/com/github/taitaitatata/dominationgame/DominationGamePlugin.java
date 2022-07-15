package com.github.taitaitatata.dominationgame;

import com.github.taitaitatata.dominationgame.command.MainCommand;
import com.github.taitaitatata.dominationgame.file.Config;
import com.github.taitaitatata.dominationgame.file.FileManager;
import com.github.taitaitatata.dominationgame.file.database.DatabaseManager;
import com.github.taitaitatata.dominationgame.listener.*;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ドミネーションを遊びたいから作ったプラグイン
 * <hr />
 * @author Taitaitatata
 */
public final class DominationGamePlugin extends JavaPlugin {

    private static DominationGamePlugin instance;
    private WorldEditPlugin WEPlugin;
    private DatabaseManager db;
    private Config config;
    private final File ARENA_DIR = new File(getDataFolder(), "arena");

    public static DominationGamePlugin get() {
        return instance;
    }

    public WorldEditPlugin getWE() {return WEPlugin;}
    public Config getCfg() {return config;}
    public File getArenaDir() {return ARENA_DIR;}

    @Override
    public void onLoad() {
        instance = this;

        getLogger().info("Initializing plugin system...");
        init();
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new KilledListener(), this);

        getCommand("domination").setExecutor(new MainCommand());

        getLogger().info("Checking depends...");
        WEPlugin = (WorldEditPlugin) getServer().getPluginManager().getPlugin("worldedit");
    }

    @Override
    public void onDisable() {
    }

    public void init() {
        getLogger().info("Creating data directories. (1/3)");
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        if (!ARENA_DIR.exists()) ARENA_DIR.mkdir();

        getLogger().info("If not exists config file then saving default config... (2/3");
        saveDefaultConfig();

        config = new Config(this);

        getLogger().info("Initializing database (3/3)");
        db = new DatabaseManager(this);
        db.init();
    }
}
