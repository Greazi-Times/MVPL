package me.greazi.magicvalley.commands;

import me.greazi.magicvalley.MVpl;
import me.greazi.magicvalley.config.Config;
import me.greazi.magicvalley.locale.LocaleLoader;
import me.greazi.magicvalley.util.Permissions;
import me.greazi.magicvalley.util.help.HelpPage1;
import me.greazi.magicvalley.util.help.HelpPage2;
import me.greazi.magicvalley.util.text.TextComponentFactory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public class MVplCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        switch (args.length) {
            case 0:
                if (!Permissions.mvplDescription(sender)) {
                    sender.sendMessage(command.getPermissionMessage());
                    return true;
                }

                Player player = (Player) sender;

                String description = LocaleLoader.getString("mvpl.Description");
                String[] mcSplit = description.split(",");
                sender.sendMessage(mcSplit);
                sender.sendMessage("§c ");

                String devDescription = LocaleLoader.getString("mvpl.Description.Dev");
                String[] mcSplit2 = devDescription.split(",");
                sender.sendMessage(mcSplit2);
                sender.sendMessage("§c ");

                if (Config.getInstance().getDonateMessageEnabled()) {
                    String donate = LocaleLoader.getString("MOTD.Donate");
                    String[] mcSplit3 = donate.split(",");
                    sender.sendMessage(mcSplit3);
                    sender.sendMessage("§c ");
                }

                TextComponentFactory.sendPlayerUrlHeader(player);

                if (Permissions.showversion(sender)) {
                    sender.sendMessage(LocaleLoader.getString("MOTD.Version", MVpl.p.getDescription().getVersion()));
                }

                return true;

            case 1:
                if (args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("commands")) {
                    if (!Permissions.mvplHelp(sender)) {
                        sender.sendMessage(command.getPermissionMessage());
                        return true;
                    }

                    HelpPage1.HelpPage1(sender);

                }
                return true;

            case 2:
                if (args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("commands")) {
                    if (!Permissions.mvplHelp(sender)) {
                        sender.sendMessage(command.getPermissionMessage());
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("1")) {
                        HelpPage1.HelpPage1(sender);
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("2")) {
                        HelpPage2.HelpPage2(sender);
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;

            default:
                return false;
        }
    }
}
