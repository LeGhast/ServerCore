package de.leghast.servercore;

import de.leghast.servercore.command.GamemodeCommand;
import de.leghast.servercore.command.VanishCommand;
import de.leghast.servercore.database.Database;
import de.leghast.servercore.database.DatabaseConfigManager;
import de.leghast.servercore.listener.PlayerChatListener;
import de.leghast.servercore.listener.PlayerJoinListener;
import de.leghast.servercore.listener.PlayerQuitListener;
import de.leghast.servercore.rank.RankSystem;
import de.leghast.servercore.rank.manager.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class ServerCore extends JavaPlugin {

    //DATABASE
    private DatabaseConfigManager databaseConfig;
    private Database database;

    //RANKSYSTEM
    private RankSystem rankSystem;

    //MANAGER
    private SettingsManager settingsManager;

    @Override
    public void onEnable() {
        initialiseRankSystem();
        registerCommands();
        initialiseManagers();
        registerListeners();
        initialiseDatabase();
    }

    @Override
    public void onDisable() {
        database.disconnect();
    }

    private void initialiseDatabase(){
        databaseConfig = new DatabaseConfigManager(this);
        database = new Database(this, databaseConfig);
            database.connect();
    }

    private void initialiseRankSystem(){
        rankSystem = new RankSystem(this);
    }

    private void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
    }

    private void initialiseManagers(){
        settingsManager = new SettingsManager(this);
    }

    private void registerCommands(){
        getCommand("vanish").setExecutor(new VanishCommand(this));
    }


    public Database getDatabase(){
        return database;
    }

    public RankSystem getRankSystem(){
        return rankSystem;
    }

    public SettingsManager getSettingsManager(){
        return  settingsManager;
    }
}
