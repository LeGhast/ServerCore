package de.leghast.servercore.rank.manager;

import de.leghast.servercore.rank.Rank;
import de.leghast.servercore.rank.RankSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NameTagManager {

    private RankSystem main;

    public NameTagManager(RankSystem main){
        this.main = main;
    }

    public void setNameTags(Player player){
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        for(Rank rank : Rank.values()){
            Team team = player.getScoreboard().registerNewTeam(rank.getTabSorter() + rank.name());
            team.setPrefix(rank.getDisplay() + " §7| " + rank.getColor());
        }

        Team team = player.getScoreboard().getTeam(main.getRankManager().getRank(player.getUniqueId()).getTabSorter() +
                main.getRankManager().getRank(player.getUniqueId()).name());
        player.setPlayerListName(team.getPrefix() + player.getName());

        for(Player target : Bukkit.getOnlinePlayers()){
            if(player.getUniqueId() != target.getUniqueId()){
                player.getScoreboard().getTeam(
                        main.getRankManager().getRank(target.getUniqueId()).getTabSorter() +
                                main.getRankManager().getRank(target.getUniqueId()).name()).addEntry(target.getName());
            }
        }
    }

    public void newNameTag(Player player){
        Rank rank = main.getRankManager().getRank(player.getUniqueId());
        for(Player target : Bukkit.getOnlinePlayers()){
            target.getScoreboard().getTeam(rank.getTabSorter() + rank.name()).addEntry(player.getName());
        }
    }

    public void removeNameTag(Player player){
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }
    }

}
