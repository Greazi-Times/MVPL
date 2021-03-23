package me.greazi.MagicValley.command.subcommands;

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
                sender.sendMessage(File.getMessage("Lockdown.Disable"));
                return true;
            }
            if (player.hasPermission("lockdown.use")) {
                if (args.length == 0) {
                    player.sendMessage(" Lockdown commands:");
                    player.sendMessage("");
                    player.sendMessage("* /lockdown enable <reason>");
                    player.sendMessage("* /lockdown disable");
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("enable")) {
                        player.sendMessage(Color.color("&cCorrect usage: /lockdown enable <reason>"));
                    } else if (args[0].equalsIgnoreCase("disable")) {
                        player.sendMessage(Prefix.LockdownPrefix() + File.getMessage("Lockdown.Disable").replace("%player%", player.getName());
                        LockdownCMD.getInstance().getLockdown().setLockdown(false);
                    }
                } else if (args.length >= 2 &&
                        args[0].equalsIgnoreCase("enable")) {
                    Bukkit.broadcastMessage(String.valueOf(Prefix.LockdownPrefix()) + Messages.LOCKDOWN_ENABLED.toString().replace("<player>", player.getName()));
                    LockdownCMD.getInstance().getLockdown().setLockdown(true);
                    StringBuilder message = new StringBuilder();
                    for (int i = 1; i < args.length; i++)
                        message.append(args[i]).append(" ");
                    LockdownCMD.getInstance().getLockdown().setReason(message.toString());
                    byte b;
                    int j;
                    Player[] arrayOfPlayer;
                    for (j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, b = 0; b < j; ) {
                        Player online = arrayOfPlayer[b];
                        if (!online.hasPermission("lockdown.bypass"))
                            online.kickPlayer(Messages.LOCKDOWN_KICK_MESSAGE.toString().replace("<reason>", message.toString().replace("&", ")));
                                    b++;
                    }
                }
            } else {
                player.sendMessage(Messages.COMMAND_NO_PERMISSION.toString());
            }
        return false;
    }
}
