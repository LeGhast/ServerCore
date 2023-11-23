package de.leghast.servercore.listener;

import de.leghast.servercore.ServerCore;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private ServerCore main;

    public PlayerJoinListener(ServerCore main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        String header = ChatColor.of("#96B6C5") + "LeGhast Network";
        String footer = ChatColor.of("#96B6C5") + "You are currently on §e" + Bukkit.getServer().getMotd() ;
        player.setPlayerListHeaderFooter(header, footer);
        e.setJoinMessage("");
        main.getRankSystem().getNameTagManager().setNameTags(player);
        main.getRankSystem().getNameTagManager().newNameTag(player);
        for(Player vanishedPlayer : main.getSettingsManager().getVanishedPlayers()){
            if(vanishedPlayer != player){
                player.hidePlayer(main, vanishedPlayer);
            }
        }
    }
}