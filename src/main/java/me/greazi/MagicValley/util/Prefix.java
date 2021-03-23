package me.greazi.MagicValley.util;

public class Prefix {
    // TODO change "PluginName" to the name of the plugin
    public static String ConsolePrefix() {
        return Color.color("&aMagic&6Valley&d>>>  ");
    }

    public static String Prefix() {
        return Color.color(File.file.getMessageFile().getString("Prefix"));
    }

    public static String LockdownPrefix() {
        return Color.color(File.file.getMessageFile().getString("LockdownPrefix"));
    }
}
