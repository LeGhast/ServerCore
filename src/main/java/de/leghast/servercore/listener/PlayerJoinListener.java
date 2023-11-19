package de.leghast.servercore.listener;

import de.leghast.servercore.ServerCore;
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
        main.getRankSystem().getNameTagManager().setNameTags(player);
        main.getRankSystem().getNameTagManager().newNameTag(player);
    }

}
