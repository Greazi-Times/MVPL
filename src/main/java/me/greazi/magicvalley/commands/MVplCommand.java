package me.greazi.magicvalley.commands;

import me.greazi.magicvalley.MVpl;
import me.greazi.magicvalley.config.Config;
import me.greazi.magicvalley.locale.LocaleLoader;
import me.greazi.magicvalley.util.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MVplCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        switch (args.length) {
            case 0:
                if (!Permissions.mvplDescription(sender)) {
                    sender.sendMessage(command.getPermissionMessage());
                    return true;
                }

                String description = LocaleLoader.getString("mvpl.Description");
                String[] mcSplit = description.split(",");
                sender.sendMessage(mcSplit);
                String devDescription = LocaleLoader.getString("mvpl.Description.Dev");
                String[] mcSplit2 = devDescription.split(",");
                sender.sendMessage(mcSplit2);

                if (Config.getInstance().getDonateMessageEnabled()) {
                    String donate = LocaleLoader.getString("MOTD.Donate");
                    String[] mcSplit3 = donate.split(",");
                    sender.sendMessage(mcSplit3);
                }

                if (Permissions.showversion(sender)) {
                    sender.sendMessage(LocaleLoader.getString("MOTD.Version", MVpl.p.getDescription().getVersion()));
                }

//                mcMMO.getHolidayManager().anniversaryCheck(sender);
                return true;

            case 1:
                if (args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("commands")) {
                    if (!Permissions.mvplHelp(sender)) {
                        sender.sendMessage(command.getPermissionMessage());
                        return true;
                    }

                    sender.sendMessage(LocaleLoader.getString("Commands.mv.Header"));
                    displayGeneralCommands(sender);
                }
                return true;

            default:
                return false;
        }
    }

    private void displayGeneralCommands(CommandSender sender) {
        sender.sendMessage(ChatColor.DARK_AQUA + " /mcstats " + LocaleLoader.getString("Commands.Stats"));
        sender.sendMessage(ChatColor.DARK_AQUA + " /<skill>" + LocaleLoader.getString("Commands.SkillInfo"));
        sender.sendMessage(ChatColor.DARK_AQUA + " /mctop " + LocaleLoader.getString("Commands.Leaderboards"));
    }


}
