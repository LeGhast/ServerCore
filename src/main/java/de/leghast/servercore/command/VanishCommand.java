package de.leghast.servercore.command;

import de.leghast.servercore.ServerCore;
import de.leghast.servercore.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VanishCommand implements CommandExecutor {

    private ServerCore main;

    public VanishCommand(ServerCore main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            boolean hasPermission = main.getRankSystem().getRankManager().hasRank(player.getUniqueId(), Rank.ADMINISTRATOR) || main.getRankSystem().getRankManager().hasRank(player.getUniqueId(), Rank.STAFF);
            if(hasPermission){
                if(main.getSettingsManager().getVanishedPlayers().contains(player)){
                    for(Player target : Bukkit.getOnlinePlayers()){
                        if(player != target && !main.getRankSystem().getRankManager().hasRank(target.getUniqueId(), Rank.ADMINISTRATOR) && !main.getRankSystem().getRankManager().hasRank(target.getUniqueId(), Rank.STAFF)){
                            target.showPlayer(main, player);
                        }
                    }
                    main.getSettingsManager().getVanishedPlayers().remove(player);
                    player.sendMessage("§cYou are no longer vanished");
                }else{
                    for(Player target : Bukkit.getOnlinePlayers()){
                        if(player != target && !main.getRankSystem().getRankManager().hasRank(target.getUniqueId(), Rank.ADMINISTRATOR) && !main.getRankSystem().getRankManager().hasRank(target.getUniqueId(), Rank.STAFF)){
                            target.hidePlayer(main, player);
                        }
                    }
                    main.getSettingsManager().getVanishedPlayers().add(player);
                    player.sendMessage("§aYou are now vanished");
                }
                return true;
            }else{
                player.sendMessage("§cOnly " + Rank.STAFF.getColor() + "staff members §ccan use this command");
            }
        }
        return false;
    }
}
