package me.greazi.MagicValley;

import me.greazi.MagicValley.command.subcommands.LockdownCMD;
import me.greazi.MagicValley.util.File;
import me.greazi.MagicValley.util.LockdownHandler;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public static Main instance;

    public static Main plugin;


    private LockdownHandler serverlockdown = new LockdownHandler(this);
    private String Prefix = "§8[§9MagicValley§7-§bPlugin§8] ";

    @Override
    public void onEnable() {
       // try {
            // Now needs to be done via this ugly way as the original way did lead to a loading error.
            instance = this;

            // ------------------------------------------------------------------------------------------------------ //
            //                                                Metrics                                                 //
            // ------------------------------------------------------------------------------------------------------ //

            // Safes the current time in milliseconds
            long l1 = System.currentTimeMillis();

            // Sends starting message of the plugin
            System.out.println("§8 ");
            System.out.println(Prefix + "§aStarting...");

            // Starts the file setup (Checks if the files exists. When they don't exists it will create the files.
            File.file.setup(this);

            // ------------------------------------------------------------------------------------------------------ //
            //                                                Features                                                //
            // ------------------------------------------------------------------------------------------------------ //

            // Registers commands

            // Registers all the events
            registerEvents();

            this.serverlockdown.enable();

            // ------------------------------------------------------------------------------------------------------ //
            //                                                Metrics Final                                           //
            // ------------------------------------------------------------------------------------------------------ //

            // Sends figlit plugin name
             Figlit();

            // Sends the final startup message that contains the time the startup started
            System.out.println(Prefix +"§aStarted! §8took §c" + TimeLoad() + "ms§8");
            System.out.println("§8 ");

            // This will be triggered when the plugin failed to load
        /*} catch (final Exception e) {
        // ------------------------------------------------------------------------------------------------------ //
        //                                              Failed loading                                            //
        // ------------------------------------------------------------------------------------------------------ //
            System.out.println("§8 ");
            System.out.println("§8[§9MagicValley§7-§bPlugin§8] §c§l§0ERROR");
            System.out.println("§8 ");
            System.out.println("§c The plugin was unable to load!");
            System.out.println("§4 Disabeling the plugin");
            System.out.println("§9Running Version§b: §a" + getDescription().getVersion());
            System.out.println("§8 ");
            System.out.println("§8[§9MagicValley§7-§bPlugin§8] §c§l§oDisabeling...");
            System.out.println("§8 ");
            System.out.println("§8[§9MagicValley§7-§bPlugin§8] §cDisabled!");
            System.out.println("§8 ");
        }*/
    }

    // Calculates the time for the startup
    private static Long TimeLoad(){
        long l1 = System.currentTimeMillis();
        long l2 = System.currentTimeMillis();

        return l2 - l1;
    }


    @Override
    public void onDisable() {
        // Disable message when the plugin gets disabled
        System.out.println(Prefix +"§cDisabling...");
        Figlit();
        System.out.println(Prefix +"§cDisabled! §8took §c" + TimeLoad() + "ms§8");

    }

    // TODO Add all the events here
    public void registerEvents(){
        // Gets the servers plugin manager and names it == pm
        PluginManager pm = getServer().getPluginManager();
        instance = this;

        // EVENTS HERE

    }


    public LockdownHandler getLockdown() {
        return this.serverlockdown;
    }
    private static String  Figlit(){
        return  "§9   __  ___          _    _   __     ____        §7      §b   ___  __          _    \n" +
                "§9  /  |/  /__ ____ _(_)__| | / /__ _/ / /__ __ __§7  ____§b  / _ \\/ /_ _____ _(_)__ \n" +
                "§9 / /|_/ / _ `/ _ `/ / __/ |/ / _ `/ / / -_) // /§7 /___/§b / ___/ / // / _ `/ / _ \\\n" +
                "§9/_/  /_/\\_,_/\\_, /_/\\__/|___/\\_,_/_/_/\\__/\\_, / §7      §b/_/  /_/\\_,_/\\_, /_/_//_/\n" +
                "§9            /___/                        /___/  §7      §b            /___/        ";
    }

    public static Main getInstance() {
        return plugin;
    }

}
