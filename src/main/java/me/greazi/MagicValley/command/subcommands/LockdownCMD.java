package me.greazi.MagicValley.command.subcommands;

import me.greazi.MagicValley.Main;
import me.greazi.MagicValley.util.Color;
import me.greazi.MagicValley.util.File;
import me.greazi.MagicValley.util.messages.Prefix;
import me.greazi.MagicValley.util.messages.help.Lockdown;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LockdownCMD implements CommandExecutor {
    Main plugin;
    public LockdownCMD(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("lockdown").setExecutor(this::onCommand);

    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        CommandSender player = sender;
        if (cmd.getName().equals("lockdown"))
            if (!(sender instanceof Player)) {
                sender.sendMessage(Prefix.LockdownPrefix() + File.getMessage("Lockdown.Disable"));
                return true;
            }
            if (player.hasPermission("lockdown.use")) {
                if (args.length == 0) {
                    Lockdown.LockdownUsage();
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("enable")) {
                        player.sendMessage(Color.color("&8[&c&l!&8] &7Correct usage: &6/lockdown &aenable &e<reason>"));
                    }
                    else if (args[0].equalsIgnoreCase("disable")) {
                        player.sendMessage(String.valueOf(Prefix.LockdownPrefix()) + File.getMessage("Lockdown.Disable").replace("%player%", player.getName()));
                        Main.getInstance().getLockdown().setLockdown(false);
                    } else if (args[0].equalsIgnoreCase("help"))
                    {
                        Lockdown.LockdownHelp();
                    }
                } else if (args[0].equalsIgnoreCase("enable")) {
                    if (args.length >= 2) {
                        Bukkit.broadcastMessage(String.valueOf(Prefix.LockdownPrefix()) + Color.color(File.file.getMessageFile().getString("Messages.Lockdown.Disable")).replace("<player>", player.getName()));
                        Main.getInstance().getLockdown().setLockdown(true);
                        StringBuilder message = new StringBuilder();
                        for (int i = 1; i < args.length; i++)
                            message.append(args[i]).append(" ");
                        Main.getInstance().getLockdown().setReason(message.toString());
                        byte b;
                        int j;
                        Player[] arrayOfPlayer;
                        for (j = (arrayOfPlayer = Bukkit.getOnlinePlayers().toArray(new Player[0])).length, b = 0; b < j; ) {
                            Player online = arrayOfPlayer[b];
                            if (!online.hasPermission("lockdown.bypass"))
                                online.kickPlayer(Color.color(File.file.getMessageFile().getString("Kick.Lockdown")).replace("%reason%", message));
                            b++;
                        }
                    }
                }
            } else {
                player.sendMessage(File.getMessage("NoPermission"));
            }
        return false;
    }
}
