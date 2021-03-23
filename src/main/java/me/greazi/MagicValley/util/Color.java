package me.greazi.MagicValley.util;

import org.bukkit.ChatColor;

public class Color {
    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
