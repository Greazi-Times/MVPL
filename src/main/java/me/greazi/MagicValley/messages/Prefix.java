package me.greazi.MagicValley.messages;

import me.greazi.MagicValley.util.Color;
import me.greazi.MagicValley.util.File;

public class Prefix {
    // TODO change "PluginName" to the name of the plugin
    public static String ConsolePrefix() {
        return Color.color("&aMagic&6Valley&d>>>  ");
    }

    public static String LockdownPrefix() {
        return Color.color(File.file.getMessageFile().getString("Prefixes.Lockdown") + "&c ");
    }

}
