package me.greazi.magicvalley.util.commands;

import com.google.common.collect.ImmutableList;
import me.greazi.magicvalley.MVpl;
import me.greazi.magicvalley.config.Config;
import me.greazi.magicvalley.locale.LocaleLoader;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class CommandUtils {
    public static final List<String> TRUE_FALSE_OPTIONS = ImmutableList.of("on", "off", "true", "false", "enabled", "disabled");
    public static final List<String> RESET_OPTIONS = ImmutableList.of("clear", "reset");

    private CommandUtils() {}

    public static boolean hidden(CommandSender sender, Player target, boolean hasPermission) {
        return sender instanceof Player && !((Player) sender).canSee(target) && !hasPermission;
    }

    public static boolean noConsoleUsage(CommandSender sender) {
        if (sender instanceof Player) {
            return false;
        }

        sender.sendMessage(LocaleLoader.getString("Commands.NoConsole"));
        return true;
    }

    public static boolean isOffline(CommandSender sender, OfflinePlayer player) {
        if (player.isOnline()) {
            return false;
        }

        sender.sendMessage(LocaleLoader.getString("Commands.Offline"));
        return true;
    }


    public static List<String> getOnlinePlayerNames(CommandSender sender) {
        Player player = sender instanceof Player ? (Player) sender : null;
        List<String> onlinePlayerNames = new ArrayList<>();

        for (Player onlinePlayer : MVpl.p.getServer().getOnlinePlayers()) {
            if (player != null && player.canSee(onlinePlayer)) {
                onlinePlayerNames.add(onlinePlayer.getName());
            }
        }

        return onlinePlayerNames;
    }

    /**
     * Get a matched player name if one was found in the database.
     *
     * @param partialName Name to match
     *
     * @return Matched name or {@code partialName} if no match was found
     */
    public static String getMatchedPlayerName(String partialName) {
        if (Config.getInstance().getMatchOfflinePlayers()) {
            List<String> matches = matchPlayer(partialName);

            if (matches.size() == 1) {
                partialName = matches.get(0);
            }
        }
        else {
            Player player = MVpl.p.getServer().getPlayer(partialName);

            if (player != null) {
                partialName = player.getName();
            }
        }

        return partialName;
    }

    /**
     * Attempts to match any player names with the given name, and returns a list of all possibly matches.
     *
     * This list is not sorted in any particular order.
     * If an exact match is found, the returned list will only contain a single result.
     *
     * @param partialName Name to match
     * @return List of all possible names
     */
    private static List<String> matchPlayer(String partialName) {
        List<String> matchedPlayers = new ArrayList<>();

        for (OfflinePlayer offlinePlayer : MVpl.p.getServer().getOfflinePlayers()) {
            String playerName = offlinePlayer.getName();
            
            if (playerName == null) { //Do null checking here to detect corrupted data before sending it throuogh .equals
            	System.err.println("[MVpl] Player data file with UIID " + offlinePlayer.getUniqueId() + " is missing a player name. This may be a legacy file from before bukkit.lastKnownName. This should be okay to ignore.");
            	continue; //Don't let an error here interrupt the loop
            }

            if (partialName.equalsIgnoreCase(playerName)) {
                // Exact match
                matchedPlayers.clear();
                matchedPlayers.add(playerName);
                break;
            }

            if (playerName.toLowerCase(Locale.ENGLISH).contains(partialName.toLowerCase(Locale.ENGLISH))) {
                // Partial match
                matchedPlayers.add(playerName);
            }
        }

        return matchedPlayers;
    }
}
