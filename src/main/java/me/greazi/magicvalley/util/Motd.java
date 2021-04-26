package me.greazi.magicvalley.util;

import me.greazi.magicvalley.MVpl;
import me.greazi.magicvalley.locale.LocaleLoader;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public final class Motd {
    public static final String PERK_PREFIX = LocaleLoader.getString("MOTD.PerksPrefix") + " ";
    private static final PluginDescriptionFile pluginDescription = MVpl.p.getDescription();

    private Motd() {}

    public static void displayAll(Player player) {
        displayVersion(player, pluginDescription.getVersion());
        displayWebsite(player, pluginDescription.getWebsite());
    }

    public static void displayVersion(Player player, String version) {
        if (Permissions.showversion(player)) {
            player.sendMessage(LocaleLoader.getString("MOTD.Version.Overhaul", version));
        }
    }

    public static void displayWebsite(Player player, String website) {
        player.sendMessage(LocaleLoader.getString("MOTD.Website", website));
    }
}
