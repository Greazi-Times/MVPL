package me.greazi.magicvalley.commands.admin;

import me.greazi.magicvalley.MVpl;
import me.greazi.magicvalley.locale.LocaleLoader;
import me.greazi.magicvalley.util.Permissions;
import me.greazi.magicvalley.util.text.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public final class MVplReloadLocaleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) {
            if (!Permissions.reloadlocale(sender)) {
                sender.sendMessage(command.getPermissionMessage());
                return true;
            }

            sender.sendMessage(ColorUtil.addColors("&aReloading Locale...."));
            LocaleLoader.reloadLocale();

            new BukkitRunnable(){

                @Override
                public void run() {
                    sender.sendMessage(LocaleLoader.getString("Locale.Reloaded"));

                }

            }.runTaskLater(MVpl.p, 20 * 4);
            return true;
        }
        return false;
    }
}
