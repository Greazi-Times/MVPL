package me.greazi.magicvalley;

import me.greazi.magicvalley.commands.CommandManager;
import me.greazi.magicvalley.config.GeneralConfig;
import me.greazi.magicvalley.locale.LocaleLoader;
import me.greazi.magicvalley.util.commands.CommandRegistrationManager;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.shatteredlands.shatt.backup.ZipLibrary;
import org.bukkit.event.HandlerList;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public class MVpl extends JavaPlugin {
    /* Managers */
    private static CommandManager commandManager; //ACF
    private static boolean serverShutdownExecuted = false;

    /* Adventure */
    private static BukkitAudiences audiences;

    /* File Paths */
    private static String mainDirectory;
    private static String localesDirectory;
    private static String flatFileDirectory;
    private static String usersFile;

    public static MVpl p;

    // Jar Stuff
    public static File mvpl;

    // Config Validation Check
    public boolean noErrorsInConfigFiles = true;

    public static FixedMetadataValue metadataValue;

    private GeneralConfig generalConfig;

    public MVpl() {
        p = this;
    }

    /**
     * Things to be run when the plugin is enabled.
     */
    @Override
    public void onEnable() {
        //try {
            generalConfig = new GeneralConfig(getDataFolder());

            metadataValue = new FixedMetadataValue(this, true);

            setupFilePaths();

            if (!noErrorsInConfigFiles) {
                return;
            }


            registerEvents();

            debug("Version " + getDescription().getVersion() + " is enabled!");

            CommandRegistrationManager.registerCommands();
        //}

        /*catch (Throwable t) {
            getLogger().severe("There was an error while enabling mcMMO!");

            if (!(t instanceof ExceptionInInitializerError)) {
                t.printStackTrace();
            }
            else {
                getLogger().info("Please do not replace the mcMMO jar while the server is running.");
            }

            getServer().getPluginManager().disablePlugin(this);

            //Fixes #4438 - Don't initialize things if we are going to disable mcMMO anyway
            return;
        }*/

        commandManager = new CommandManager(this);

        setServerShutdown(false); //Reset flag, used to make decisions about async saves
        LocaleLoader.reloadLocale();
    }

    /**
     * Things to be run when the plugin is disabled.
     */
    @Override
    public void onDisable() {
        setServerShutdown(true);
        MVpl.p.getLogger().info("Server shutdown has been executed, saving and cleaning up data...");

        if (generalConfig.getBackupsEnabled()) {
            // Remove other tasks BEFORE starting the Backup, or we just cancel it straight away.
            try {
                ZipLibrary.mcMMOBackup();
            }
            catch (IOException e) {
                getLogger().severe(e.toString());
            }
            catch(NoClassDefFoundError e) {
                getLogger().severe("Backup class not found!");
                getLogger().info("Please do not replace the mcMMO jar while the server is running.");
            }
            catch (Throwable e) {
                getLogger().severe(e.toString());
            }
        }

        debug("Canceling all tasks...");
        getServer().getScheduler().cancelTasks(this); // This removes our tasks
        debug("Unregister all events...");
        HandlerList.unregisterAll(this); // Cancel event registrations

        debug("Was disabled."); // How informative!
    }

    public static String getMainDirectory() {
        return mainDirectory;
    }

    public static String getLocalesDirectory() {
        return localesDirectory;
    }

    public static String getFlatFileDirectory() {
        return flatFileDirectory;
    }

    public static String getUsersFilePath() {
        return usersFile;
    }

    public void debug(String message) {
        getLogger().info("[Debug] " + message);
    }

    /**
     * Setup the various storage file paths
     */
    private void setupFilePaths() {
        mvpl = getFile();
        mainDirectory = getDataFolder().getPath() + File.separator;
        localesDirectory = mainDirectory + "locales" + File.separator;
        flatFileDirectory = mainDirectory + "flatfile" + File.separator;
        usersFile = flatFileDirectory + "mcmmo.users";
        fixFilePaths();
    }

    private void fixFilePaths() {
        File oldFlatfilePath = new File(mainDirectory + "FlatFileStuff" + File.separator);

        if (oldFlatfilePath.exists()) {
            if (!oldFlatfilePath.renameTo(new File(flatFileDirectory))) {
                getLogger().warning("Failed to rename FlatFileStuff to flatfile!");
            }
        }

        File currentFlatfilePath = new File(flatFileDirectory);
        currentFlatfilePath.mkdirs();
        File localesDirectoryPath = new File(localesDirectory);
        localesDirectoryPath.mkdirs();
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();

        // Register events
    }



    public @Nullable InputStreamReader getResourceAsReader(@NotNull String fileName) {
        InputStream in = getResource(fileName);
        return in == null ? null : new InputStreamReader(in, StandardCharsets.UTF_8);
    }

    public static BukkitAudiences getAudiences() {
        return audiences;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public static synchronized boolean isServerShutdownExecuted() {
        return serverShutdownExecuted;
    }

    private static synchronized void setServerShutdown(boolean bool) {
        serverShutdownExecuted = bool;
    }

    public @NotNull GeneralConfig getGeneralConfig() {
        return generalConfig;
    }

}
