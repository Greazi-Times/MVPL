package me.greazi.magicvalley.commands.test;

import me.greazi.magicvalley.MVpl;
import me.greazi.magicvalley.locale.LocaleLoader;
import me.greazi.magicvalley.util.Permissions;
import me.greazi.magicvalley.util.text.ColorUtil;
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
        switch (args.length) {
            case 0:
                if (!Permissions.mvtest(sender)) {
                    sender.sendMessage(command.getPermissionMessage());
                    return true;
                }
                return true;

            case 1:
                if (args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("commands")) {
                    if (!Permissions.mvplHelp(sender)) {
                        sender.sendMessage(command.getPermissionMessage());
                        return true;
                    }

                    sender.sendMessage(getHelpHeader());
                    displayGeneralCommands(sender);
                    footerHelpP1(sender);

                }
                return true;

            default:
                return false;
        }
    }

    private void displayGeneralCommands(CommandSender sender) {
        sender.sendMessage(ColorUtil.addColors("   &6&l- &a/mvpl " + LocaleLoader.getString("Commands.Description.mvpl")));
        sender.sendMessage(ColorUtil.addColors("   &6&l- &a/mvtest " + LocaleLoader.getString("Commands.Description.mvtest")));
    }

    private void NextPageComponent() {
        TextComponent subComponent = new TextComponent( ">>" );
        subComponent.setColor( ChatColor.AQUA );
        subComponent.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "Click me!" ).create() ) );
        subComponent.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND,"mvtest help 2"));
    }

    protected String[] getHelpHeader() {
        return new String[] { ColorUtil.addColors("&8"), ColorUtil.addColors("&8") +

                LocaleLoader.getString("Message.Component.Header.Help"),
                getHeaderPrefix() + "  " + MVpl.p.getName() + ColorUtil.addColors("&8") + ColorUtil.addColors(" &7") + MVpl.p.getDescription().getVersion(), " ",
                ColorUtil.addColors("&2  [] &f= ") + LocaleLoader.getString("Message.Component.Part.OptionalArgs"),
                ColorUtil.addColors("&6  <> &f= ") + LocaleLoader.getString("Message.Component.Part.RequiredArgs"), " " };
    }

    protected String getHeaderPrefix() {
        return "" + ChatColor.GOLD + ChatColor.BOLD;
    }

    private void footerHelpP1(CommandSender sender) {
        TextComponent firstComponent = new TextComponent(ColorUtil.addColors("&8&m---------------------- &7« "));
        firstComponent.setColor( ChatColor.GOLD );

        TextComponent subComponent = new TextComponent( "Maybe u r noob?" );
        subComponent.setColor( ChatColor.AQUA );
        subComponent.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "Click me!" ).create() ) );
        subComponent.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/wiki/the-chat-component-api/" ) );

        firstComponent.addExtra( subComponent );
        firstComponent.addExtra( " &8&m----------------------" );
        sender.spigot().sendMessage( firstComponent );
    }
}
