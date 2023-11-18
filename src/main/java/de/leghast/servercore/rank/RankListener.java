package de.leghast.servercore.rank;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class RankListener implements Listener {

    private RankSystem main;

    public RankListener(RankSystem main){
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        main.getNameTagManager().setNameTags(player);
        main.getNameTagManager().newNameTag(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        main.getNameTagManager().removeNameTag(e.getPlayer());
        main.getRankCache().remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        e.setCancelled(true);

        Bukkit.broadcastMessage(main.getRankManager().getRank(player.getUniqueId()).getColor() + player.getName() + " §7>> "
                + main.getRankManager().getRank(player.getUniqueId()).getMessageColor() + e.getMessage());
    }

}
