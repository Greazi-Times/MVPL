package me.greazi.magicvalley.util;

import me.greazi.magicvalley.locale.LocaleLoader;
import me.greazi.magicvalley.MVpl;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public final class Motd {
    public static final String PERK_PREFIX = LocaleLoader.getString("MOTD.PerksPrefix") + " ";
    private static final PluginDescriptionFile pluginDescription = MVpl.p.getDescription();

    private Motd() {}

    public static void displayAll(Player player) {
        displayVersion(player, pluginDescription.getVersion());
        displayWebsite(player, pluginDescription.getWebsite());
    }

    /**
     * Display version info.
     *
     * @param player  Target player
     * @param version Plugin version
     */
    public static void displayVersion(Player player, String version) {
        if (Permissions.showversion(player)) {
            player.sendMessage(LocaleLoader.getString("MOTD.Version.Overhaul", version));
        }
    }


    /**
     * Display website info.
     *
     * @param player  Target player
     * @param website Plugin website
     */
    public static void displayWebsite(Player player, String website) {
        player.sendMessage(LocaleLoader.getString("MOTD.Website", website));
    }
}
