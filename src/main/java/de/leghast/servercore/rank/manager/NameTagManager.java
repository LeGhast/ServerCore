package de.leghast.servercore.rank.manager;

import de.leghast.servercore.rank.Rank;
import de.leghast.servercore.rank.RankSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NameTagManager {

    private RankSystem main;
    private boolean displayingRanks = true;

    public NameTagManager(RankSystem main){
        this.main = main;
    }

    public void setNameTags(Player player){
        if(displayingRanks){
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

            for(Rank rank : Rank.values()){
                Team team = player.getScoreboard().registerNewTeam(rank.getTabSorter() + rank.name());
                team.setPrefix(rank.getDisplay() + " §7| " + rank.getColor());
            }

            Team team = player.getScoreboard().getTeam(main.getRankManager().getRank(player.getUniqueId()).getTabSorter() +
                    main.getRankManager().getRank(player.getUniqueId()).name());
            player.setPlayerListName(team.getPrefix() + player.getName());
            player.setDisplayName(team.getPrefix() + player.getName());

            for(Player target : Bukkit.getOnlinePlayers()){
                if(player.getUniqueId() != target.getUniqueId()){
                    player.getScoreboard().getTeam(
                            main.getRankManager().getRank(target.getUniqueId()).getTabSorter() +
                                    main.getRankManager().getRank(target.getUniqueId()).name()).addEntry(target.getName());
                }
            }
        }
    }

    public void newNameTag(Player player){
        if(displayingRanks){
            Rank rank = main.getRankManager().getRank(player.getUniqueId());
            for(Player target : Bukkit.getOnlinePlayers()){
                target.getScoreboard().getTeam(rank.getTabSorter() + rank.name()).addEntry(player.getName());
            }
        }
    }

    public void removeNameTag(Player player){
        if(displayingRanks){
            for (Player target : Bukkit.getOnlinePlayers()) {
                target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
            }
        }
    }

    public void updateTags(Player player){
        removeNameTag(player);
        newNameTag(player);
    }

    public boolean isDisplayingRanks(){
        return displayingRanks;
    }

    public void setDisplayingRanks(boolean displayingRanks){
       this.displayingRanks = displayingRanks;
    }

}
