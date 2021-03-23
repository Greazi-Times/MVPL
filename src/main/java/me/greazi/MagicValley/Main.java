package me.greazi.MagicValley;

import me.greazi.MagicValley.util.File;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public static Main plugin;

    public static Plugin instance;

    @Override
    public void onEnable() {
        try {
            /*
              This will be executed on the plugin start
             */
            // Starts timer when starting up
            long l1 = System.currentTimeMillis();
            // Sends starting message of the plugin
            // TODO change "PluginName" to the name of the plugin
            System.out.println("§8[§9PluginName§7-§bPlugin§8] §aStarting...");
            // Registers all commands
            registerCommands();
            // This wil register all events
            registerEvents();
            File.file.setup(this);
            System.out.println("§8 ");
            System.out.println("§9   __  ___          _    _   __     ____        §7      §b   ___  __          _    ");
            System.out.println("§9  /  |/  /__ ____ _(_)__| | / /__ _/ / /__ __ __§7  ____§b  / _ \\/ /_ _____ _(_)__ ");
            System.out.println("§9 / /|_/ / _ `/ _ `/ / __/ |/ / _ `/ / / -_) // /§7 /___/§b / ___/ / // / _ `/ / _ \\");
            System.out.println("§9/_/  /_/\\_,_/\\_, /_/\\__/|___/\\_,_/_/_/\\__/\\_, / §7      §b/_/  /_/\\_,_/\\_, /_/_//_/");
            System.out.println("§9                                                      §8          §b              /____/          ");
            System.out.println("§9Running Version§b: §a" + getDescription().getVersion());
            System.out.println("§8 ");
            long l2 = System.currentTimeMillis();
            long l3 = l2 - l1;
            // TODO change "PluginName" to the name of the plugin
            System.out.println("§8[§9PluginName§7-§bPlugin§8] §aStarted! §8took §c" + l3 + "ms§8");

        } catch (final Exception e) {
        // ------------------------------------------------------------------------------------------------------ //
        //                                              Failed loading                                            //
        // ------------------------------------------------------------------------------------------------------ //
            System.out.println("§8[§9MagicValley§7-§bPlugin§8] §aDisabeling...");
            System.out.println("§8 ");
            System.out.println("§c The plugin was unable to load!");
            System.out.println("§4 Disabeling the plugin");
            System.out.println("§9Running Version§b: §a" + getDescription().getVersion());
            System.out.println("§8 ");
            System.out.println("§8[§9MagicValley§7-§bPlugin§8] §cDisabeling...");
            System.out.println("§8 ");
            System.out.println("§8[§9MagicValley§7-§bPlugin§8] §4Disabled!");
        }
    }


    @Override
    public void onDisable() {
        /*
         * This will be executed when the plugin is disabled
         */
        // Disable message when the plugin gets disabled
        long l1 = System.currentTimeMillis();
        System.out.println("§8[§9MagicValley§7-§bPlugin§8] §cDisabling...");
        System.out.println("§8 ");
        System.out.println("§9   __  ___          _    _   __     ____        §7      §b   ___  __          _    ");
        System.out.println("§9  /  |/  /__ ____ _(_)__| | / /__ _/ / /__ __ __§7  ____§b  / _ \\/ /_ _____ _(_)__ ");
        System.out.println("§9 / /|_/ / _ `/ _ `/ / __/ |/ / _ `/ / / -_) // /§7 /___/§b / ___/ / // / _ `/ / _ \\");
        System.out.println("§9/_/  /_/\\_,_/\\_, /_/\\__/|___/\\_,_/_/_/\\__/\\_, / §7      §b/_/  /_/\\_,_/\\_, /_/_//_/");
        System.out.println("§9                                                      §8          §b              /____/          ");
        System.out.println("§9Running Version §a" + getDescription().getVersion());
        System.out.println("§8 ");
        long l2 = System.currentTimeMillis();
        long l3 = l2 - l1;
        System.out.println("§8[§9MagicValley§7-§bPlugin§8] §cDisabled! §8took §c" + l3 + "ms§8");

    }

    // Commands here
    private void registerCommands() {
        /*
          Commands needs to be put in here
         */
    }

    public static Main getInstance() {
        return plugin;
    }


    // Executer for events
    public void registerEvents(){
        //getServer().getPluginManager() get the name pm
        PluginManager pm = getServer().getPluginManager();
        instance = this;
        /*
          Events needs to be put in here
         */

    }
}
