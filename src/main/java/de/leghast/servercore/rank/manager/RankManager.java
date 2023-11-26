package de.leghast.servercore.rank.manager;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import de.leghast.servercore.ServerCore;
import de.leghast.servercore.rank.Rank;
import de.leghast.servercore.rank.RankSystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RankManager {

    private RankSystem main;
    private ServerCore plugin;

    public RankManager(RankSystem main, ServerCore plugin){
        this.main = main;
        this.plugin = plugin;
    }

    public Rank getRank(UUID uuid){
        if(main.getRankCache().containsKey(uuid)){
            return main.getRankCache().get(uuid);
        }else {
            try {
                PreparedStatement getPlayerRank;
                getPlayerRank = plugin.getDatabase().getConnection().prepareStatement("SELECT player_rank FROM players WHERE player_uuid = ?");
                getPlayerRank.setString(1, uuid.toString());
                ResultSet result = getPlayerRank.executeQuery();
                if (!result.isBeforeFirst()) {
                    return Rank.PLAYER;
                } else {
                    String rank = null;
                    while (result.next()) {
                        rank = result.getString("player_rank");
                    }
                    return Rank.valueOf(rank);
                }
            } catch (SQLException e) {
                if(e instanceof CommunicationsException){
                    plugin.getDatabase().connect();
                    return getRank(uuid);
                }
                return null;
            }
        }
    }

    public boolean hasRank(UUID uuid, Rank rank){
        return getRank(uuid) == rank;
    }


}
