package de.leghast.servercore.listener;

import de.leghast.servercore.ServerCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private ServerCore main;

    public PlayerQuitListener(ServerCore main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        e.setQuitMessage("");
        main.getRankSystem().getNameTagManager().removeNameTag(e.getPlayer());
        main.getRankSystem().getRankCache().remove(e.getPlayer().getUniqueId());
    }

}
