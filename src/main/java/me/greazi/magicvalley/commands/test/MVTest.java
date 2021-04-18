package me.greazi.magicvalley.commands.test;

import me.greazi.magicvalley.util.Permissions;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MVTest implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) {
            if (!Permissions.mvtest(sender)) {
                sender.sendMessage(command.getPermissionMessage());
                return true;
            }

            TextComponent mainComponent = new TextComponent( "Here's a question: " );
            mainComponent.setColor( ChatColor.GOLD );
            TextComponent subComponent = new TextComponent( "Maybe u r noob?" );
            subComponent.setColor( ChatColor.AQUA );
            subComponent.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "Click me!" ).create() ) );
            subComponent.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/wiki/the-chat-component-api/" ) );
            mainComponent.addExtra( subComponent );
            mainComponent.addExtra( " Does that answer your question?" );
            sender.spigot().sendMessage( mainComponent );

            return true;
        }
        return false;
    }
}
