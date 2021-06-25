package me.greazi.magicvalley.util.commands;

import me.greazi.magicvalley.MVpl;
import me.greazi.magicvalley.commands.MVplCommand;
import me.greazi.magicvalley.commands.admin.MVplReloadLocaleCommand;
import me.greazi.magicvalley.commands.test.MVTest;
import me.greazi.magicvalley.locale.LocaleLoader;
import org.bukkit.command.PluginCommand;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public final class CommandRegistrationManager {
    private CommandRegistrationManager() {}

    private static final String permissionsMessage = LocaleLoader.getString("MVPL.NoPermission");

    private static void registerMvplCommand() {
        PluginCommand command = MVpl.p.getCommand("mvpl");
        command.setDescription(LocaleLoader.getString("Commands.Description.mvpl"));
        command.setPermission("mvpl.commands.mvpl.description;mvpl.commands.mvpl.help");
        command.setPermissionMessage(permissionsMessage);
        command.setUsage(LocaleLoader.getString("Commands.Usage.0", "mvpl"));
        command.setUsage(command.getUsage() + "\n" + LocaleLoader.getString("Commands.Usage.1", "mvpl", "help"));
        command.setExecutor(new MVplCommand());
    }

    private static void registerReloadLocaleCommand() {
        PluginCommand command = MVpl.p.getCommand("mvplreloadlocale");
        command.setDescription("Reloads locale");
        command.setPermission("mvpl.commands.reloadlocale");
        command.setPermissionMessage(permissionsMessage);
        command.setUsage(LocaleLoader.getString("Commands.Usage.0", "mvplreloadlocale"));
        command.setExecutor(new MVplReloadLocaleCommand());
    }

    private static void registerTestCommand() {
        PluginCommand command = MVpl.p.getCommand("mvtest");
        command.setDescription(LocaleLoader.getString("Commands.Description.mvtest"));
        command.setPermission("mvpl.commands.test");
        command.setPermissionMessage(permissionsMessage);
        command.setUsage(LocaleLoader.getString("Commands.Usage.1", "mvtest", "help"));
        command.setExecutor(new MVTest());
    }

   /* private static void registerAsCommand() {
        PluginCommand command = MVpl.p.getCommand("ascmd");
        command.setDescription(LocaleLoader.getString("Commands.Description.ascmd"));
        command.setPermission("mvpl.commands.mvpl.ascmd");
        command.setPermissionMessage(permissionsMessage);
        command.setUsage(LocaleLoader.getString("Commands.Usage.0", "ascmd"));
        command.setExecutor(new ArmorStandCmd());
    } */

    public static void registerCommands() {
        // Generic Commands
        registerMvplCommand();

        // Admin commands
        registerReloadLocaleCommand();

        // Test Command
        registerTestCommand();
    }
}
