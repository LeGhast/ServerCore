package de.leghast.servercore.rank;

import de.leghast.servercore.ServerCore;
import de.leghast.servercore.rank.manager.NameTagManager;
import de.leghast.servercore.rank.manager.RankManager;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class RankSystem {

    private ServerCore main;
    private RankManager rankManager;
    private NameTagManager nameTagManager;
    private HashMap<UUID, Rank> rankCache = new HashMap<>();

    public RankSystem(ServerCore main){
        this.main = main;
        rankManager = new RankManager(this, main);
        nameTagManager = new NameTagManager(this);
    }

    public RankManager getRankManager(){
        return rankManager;
    }

    public NameTagManager getNameTagManager(){
        return nameTagManager;
    }

    public HashMap<UUID, Rank> getRankCache(){
        return rankCache;
    }



}
