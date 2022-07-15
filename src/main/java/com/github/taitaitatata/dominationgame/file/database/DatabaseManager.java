package com.github.taitaitatata.dominationgame.file.database;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.*;

public class DatabaseManager {

    private final String name;
    private Connection connection;
    private Statement statement;
    private final JavaPlugin instance;
    private final FileConfiguration config;
    private final String DBPath;

    public DatabaseManager(JavaPlugin plugin) {
        this.instance = plugin;
        this.config = plugin.getConfig();
        this.name = config.getString("database.file-name");

        DBPath = new File(plugin.getDataFolder(), name).toString();
    }

    public void init() {

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:"+DBPath);
            statement = connection.createStatement();

            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS " + config.getString("database.table-prefix") + "matches (id string, winner string, pointOne int, pointTwo int)"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS " + config.getString("database.table-prefix") + "user (uuid string, win int, lose int, kill int, death int, played int, extra string)"
            );


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
