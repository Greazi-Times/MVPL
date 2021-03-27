package me.greazi.MagicValley.command.subcommands;

import me.greazi.MagicValley.Main;
import me.greazi.MagicValley.util.Color;
import me.greazi.MagicValley.util.File;
import me.greazi.MagicValley.util.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LockdownCMD implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        CommandSender player = sender;
        if (cmd.getName().equals("lockdown"))
            if (!(sender instanceof Player)) {
                sender.sendMessage(Prefix.LockdownPrefix() + File.getMessage("Lockdown.Disable"));
                return true;
            }
            if (player.hasPermission("lockdown.use")) {
                if (args.length == 0) {
                    player.sendMessage(Color.color("&8&m&l+---------------&8 [ &cUsage &8] &8&m&l---------------+&r"));
                    player.sendMessage(Color.color("&c "));
                    player.sendMessage(Color.color(" &6&l• &e/lockdown enable <reason> &8| &7Enable the lockdown"));
                    player.sendMessage(Color.color(" &6&l• &e/lockdown disable &8| &7Disable the lockdown"));
                    player.sendMessage(Color.color("&c "));
                    player.sendMessage(Color.color("&8&m&l+-------------------------------------+&r"));
                    player.sendMessage(Prefix.Prefix());
                    player.sendMessage(Prefix.ConsolePrefix());
                    player.sendMessage(Prefix.LockdownPrefix());
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("enable")) {
                        player.sendMessage(Color.color("&cCorrect usage: /lockdown enable <reason>"));
                    }
                    else if (args[0].equalsIgnoreCase("disable")) {
                        player.sendMessage(String.valueOf(Prefix.LockdownPrefix()) + File.getMessage("Lockdown.Disable").replace("%player%", player.getName()));
                        Main.getInstance().getLockdown().setLockdown(false);
                    } else if (args[0].equalsIgnoreCase("help"))
                    {
                        player.sendMessage(Color.color("&8&m&l+------------&8 [ &cLockdown Help &8] &8&m&l------------+&r"));
                        player.sendMessage(Color.color("&c "));
                        player.sendMessage(Color.color(" &6&l• &e/lockdown enable <reason> &8| &7Enable the lockdown"));
                        player.sendMessage(Color.color(" &6&l• &e/lockdown disable &8| &7Disable the lockdown"));
                        player.sendMessage(Color.color("&c "));
                        player.sendMessage(Color.color("&8&m&l+-------------------------------------+&r"));
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
