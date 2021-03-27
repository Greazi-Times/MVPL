package me.greazi.MagicValley.util;

import me.greazi.MagicValley.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

public class LockdownHandler extends Handler implements Listener {
    public boolean lockdown = false;
    private String reason = new String();

    public LockdownHandler(Main plugin) {
        super(plugin);
    }

    public void enable() {
        getInstance().getServer().getPluginManager().registerEvents(this, (Plugin)getInstance());
    }

    public boolean isLockdown() {
        return this.lockdown;
    }

    public String getReason() {
        return this.reason;
    }

    public void setLockdown(boolean lockdown) {
        this.lockdown = lockdown;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @EventHandler
    public void PlayerJoin(PlayerLoginEvent event) {
        final Player player = event.getPlayer();
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, new Runnable() {
            public void run() {
                if (Main.getInstance().getLockdown().isLockdown()) {
                    String lockdownreason = LockdownHandler.this.reason;
                    player.kickPlayer(Color.color(File.file.getMessageFile().getString("Kick.Lockdown")).replace("%reason%", lockdownreason));
                }
            }
        }, 1L);
    }
}
