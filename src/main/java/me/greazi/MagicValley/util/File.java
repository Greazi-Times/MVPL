package me.greazi.MagicValley.util;

import me.greazi.MagicValley.modules.FileManagement;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class File {

    public static String getMessage(String msg) {
        return Color.color(String.valueOf(Prefix.Prefix()) + file.getMessageFile().getString("Messages." + msg));
    }

    public static String getMessageP(String msg, Player player) {
        String msg2 = Objects.requireNonNull(file.getMessageFile().getString("Messages." + msg)).replace("%player%", player.getName());
        return Color.color(msg2);
    }

    public static FileManagement file = FileManagement.getInstance();

    public static String systemtime() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return format.format(now);
    }

    public static String msg(String item) {
        return Color.color(String.valueOf(Prefix.Prefix()) + item);
    }

    public static Boolean isPluginLoaded(String name) {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(name);
        return (plugin != null) ? Boolean.valueOf(true) : Boolean.valueOf(false);
    }
}
