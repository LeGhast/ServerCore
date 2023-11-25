package de.leghast.servercore.rank;

import net.md_5.bungee.api.ChatColor;

public enum Rank {

    ADMINISTRATOR("#E97777", 'a', "Admin", "#e3c3c3"),
    STAFF("#FFCF96",'b', "Staff", "#dbc9b4"),
    VIP("#95BDFF", 'c', "VIP", "#abc8f7"),
    PREMIUM("#BEADFA",'d', "Premium", "#bfb8d9"),
    PLAYER("#79AC78", 'e', "Player", "#b2cfb2");

    private ChatColor color;
    private char tabSorter;
    private String display;
    private ChatColor messageColor;

    Rank(String color, char tabSorter, String display, String messageColor){
        this.color = ChatColor.of(color);
        this.tabSorter = tabSorter;
        this.display = ChatColor.of(color) + display;
        this.messageColor = ChatColor.of(messageColor);
    }

    public ChatColor getColor(){
        return color;
    }
    public char getTabSorter(){
        return tabSorter;
    }
    public String getDisplay(){
        return display;
    }
    public ChatColor getMessageColor(){
        return messageColor;
    }

}
