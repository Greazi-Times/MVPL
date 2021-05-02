package me.greazi.magicvalley.commands.test;

import me.greazi.magicvalley.util.Permissions;
import me.greazi.magicvalley.util.text.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public class MVTest implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        switch (args.length) {
            case 0:
                if (!Permissions.mvtest(sender)) {
                    sender.sendMessage(command.getPermissionMessage());
                    return false;
                }
                sender.sendMessage(ColorUtil.addColors("&cThis command is not in use right now!"));
                return true;


            default:
                return false;
        }
    }
}
