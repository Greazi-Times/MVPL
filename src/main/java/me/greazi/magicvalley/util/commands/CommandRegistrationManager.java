package me.greazi.magicvalley.util.commands;

import me.greazi.magicvalley.commands.*;
import me.greazi.magicvalley.commands.MVplCommand;
import me.greazi.magicvalley.commands.admin.MVplReloadLocaleCommand;
import me.greazi.magicvalley.commands.test.MVTest;
import me.greazi.magicvalley.locale.LocaleLoader;
import me.greazi.magicvalley.MVpl;
import org.bukkit.command.PluginCommand;

public final class CommandRegistrationManager {
    private CommandRegistrationManager() {}

    private static final String permissionsMessage = LocaleLoader.getString("mcMMO.NoPermission");

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
        command.setUsage(LocaleLoader.getString("Commands.Usage.0", "mvtest"));
        command.setExecutor(new MVTest());
    }

    public static void registerCommands() {
        // Generic Commands
        registerMvplCommand();

        // Admin commands
        registerReloadLocaleCommand();

        // Test Command
        registerTestCommand();
    }
}
