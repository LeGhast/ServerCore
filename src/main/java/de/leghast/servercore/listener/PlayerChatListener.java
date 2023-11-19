package de.leghast.servercore.listener;

import de.leghast.servercore.ServerCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    private ServerCore main;

    public PlayerChatListener(ServerCore main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        e.setCancelled(true);

        Bukkit.broadcastMessage(main.getRankSystem().getRankManager().getRank(player.getUniqueId()).getColor() + player.getName() + " §7>> "
                + main.getRankSystem().getRankManager().getRank(player.getUniqueId()).getMessageColor() + e.getMessage());
    }

}
