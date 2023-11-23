package de.leghast.servercore.rank.manager;

import de.leghast.servercore.ServerCore;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SettingsManager {

    private ServerCore main;

    public SettingsManager(ServerCore main){
        this.main = main;
    }

    private List<Player> vanishedPlayers = new ArrayList<>();


    public List<Player> getVanishedPlayers(){
        return vanishedPlayers;
    }

    public boolean isVanished(Player player){
        return vanishedPlayers.contains(player);
    }

}
