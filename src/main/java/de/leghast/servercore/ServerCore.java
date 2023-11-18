package de.leghast.servercore;

import de.leghast.servercore.database.Database;
import de.leghast.servercore.database.DatabaseConfigManager;
import de.leghast.servercore.rank.RankListener;
import de.leghast.servercore.rank.RankSystem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class ServerCore extends JavaPlugin {

    //DATABASE
    private DatabaseConfigManager databaseConfig;
    private Database database;

    //RANKSYSTEM
    private RankSystem rankSystem;

    @Override
    public void onEnable() {
        initialiseRankSystem();
        initialiseDatabase();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initialiseDatabase(){
        databaseConfig = new DatabaseConfigManager(this);
        database = new Database(this, databaseConfig);
        try {
            database.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initialiseRankSystem(){
        rankSystem = new RankSystem(this);
        Bukkit.getPluginManager().registerEvents(new RankListener(rankSystem), this);
    }

    public Database getDatabase(){
        return database;
    }
}
