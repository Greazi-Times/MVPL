package me.greazi.magicvalley.commands;

import co.aikar.commands.BukkitCommandIssuer;
import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.ConditionFailedException;
import me.greazi.magicvalley.locale.LocaleLoader;
import me.greazi.magicvalley.MVpl;
import me.greazi.magicvalley.util.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;
import org.jetbrains.annotations.NotNull;

/*
 * For now this class will only handle ACF converted commands, all other commands will be handled elsewhere
 */
public class CommandManager {
    public static final @NotNull String MV_DATA_LOADED = "mvDataLoaded";

    private final @NotNull MVpl pluginRef;
    private final @NotNull BukkitCommandManager bukkitCommandManager;

    public CommandManager(@NotNull MVpl pluginRef) {
        this.pluginRef = pluginRef;
        bukkitCommandManager = new BukkitCommandManager(pluginRef);

        registerConditions();
    }

    public void registerConditions() {
        bukkitCommandManager.getCommandConditions().addCondition(MV_DATA_LOADED, (context) -> {
            BukkitCommandIssuer bukkitCommandIssuer = context.getIssuer();

        });
    }

    private void validatePermission(@NotNull String permissionNode, @NotNull Permissible permissible) {
        if(!permissible.hasPermission(permissionNode)) {
            throw new ConditionFailedException(LocaleLoader.getString("MVpl.NoPermission"));
        }
    }

    public @NotNull BukkitCommandManager getBukkitCommandManager() {
        return bukkitCommandManager;
    }
}
