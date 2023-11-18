package de.leghast.servercore.database;

import de.leghast.servercore.ServerCore;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DatabaseConfigManager {

    private ServerCore main;

    private File file;
    private YamlConfiguration config;

    public DatabaseConfigManager(ServerCore main){
        this.main = main;
        if(!main.getDataFolder().exists()){
            main.getDataFolder().mkdir();
        }

        file = new File(main.getDataFolder(), "database.yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public String getUrl(){
        return config.getString("url");
    }

    public String getUsername(){
        return config.getString("username");
    }

    public String getPassword(){
        return config.getString("password");
    }

}
