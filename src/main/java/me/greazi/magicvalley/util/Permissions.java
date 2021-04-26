package me.greazi.magicvalley.util;

import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public final class Permissions {
    private Permissions() {}


    // GENERAL
    public static boolean motd(Permissible permissible) { return permissible.hasPermission("mvpl.motd"); }
    public static boolean showversion(Permissible permissible) { return permissible.hasPermission("mvpl.showversion"); }

    //COMMANDS
    public static boolean mvinfo(Permissible permissible) { return permissible.hasPermission("mvpl.commands.mvplinfo"); }

    public static boolean mvplDescription(Permissible permissible) { return permissible.hasPermission("mvpl.commands.mvpl.description"); }
    public static boolean mvplHelp(Permissible permissible) { return permissible.hasPermission("mvpl.commands.mvpl.help"); }

    public static boolean reloadlocale(Permissible permissible) { return permissible.hasPermission("mvpl.commands.reloadlocale"); }
    public static boolean mvtest(Permissible permissible) { return permissible.hasPermission("mvpl.commands.mvtest"); }

    private static void addDynamicPermission(String permissionName, PermissionDefault permissionDefault, PluginManager pluginManager) {
        Permission permission = new Permission(permissionName);
        permission.setDefault(permissionDefault);
        pluginManager.addPermission(permission);
    }

}
