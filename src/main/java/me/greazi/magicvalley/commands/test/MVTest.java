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
                    displayPage1(sender);
                    footerHelpP1(sender);

                }
                return true;

            case 2:
                if (args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("commands")) {
                    if (!Permissions.mvplHelp(sender)) {
                        sender.sendMessage(command.getPermissionMessage());
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("1")) {
                        sender.sendMessage(getHelpHeader());
                        displayPage1(sender);
                        footerHelpP1(sender);
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("2")) {
                        sender.sendMessage(getHelpHeader());
                        sender.sendMessage(ColorUtil.addColors("&6This is page 2&c&l!!!!!!"));
                        footerHelpP2(sender);
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

    private void displayPage1(CommandSender sender) {
        sender.sendMessage(ColorUtil.addColors("   &6&l- &a/mvpl     &8|" + LocaleLoader.getString("Commands.Description.mvpl")));
        sender.sendMessage(ColorUtil.addColors("   &6&l- &a/mvtest   &8|" + LocaleLoader.getString("Commands.Description.mvtest")));
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
        TextComponent firstComponent = new TextComponent(ColorUtil.addColors("&8&m----------------------&7 « "));
        firstComponent.setColor( ChatColor.DARK_GRAY );

        TextComponent subComponent1 = new TextComponent( "1/2" );
        subComponent1.setColor( ChatColor.GRAY );
        subComponent1.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( ColorUtil.addColors( hoverPage() )).create() ) );

        subComponent1.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "" ) );

        TextComponent subComponent2 = new TextComponent( ColorUtil.addColors( " &6» " ) );
        subComponent2.setColor( ChatColor.GRAY );
        subComponent2.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( ColorUtil.addColors("&7Go to page 2" )).create() ) );
        subComponent2.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/mvtest ? 2" ) );

        firstComponent.addExtra( subComponent1 );
        firstComponent.addExtra( subComponent2 );
        firstComponent.addExtra( ColorUtil.addColors("&8&m----------------------"));
        sender.spigot().sendMessage( firstComponent );
    }

    private void footerHelpP2(CommandSender sender) {
        TextComponent firstComponent = new TextComponent(ColorUtil.addColors("&8&m----------------------&6 « "));
        firstComponent.setColor( ChatColor.DARK_GRAY );

        TextComponent subComponent1 = new TextComponent( "2/2" );
        subComponent1.setColor( ChatColor.GRAY );

        subComponent1.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( hoverPage() ).create() ) );
        subComponent1.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "" ) );

        firstComponent.addExtra( subComponent1 );
        firstComponent.addExtra( ColorUtil.addColors(" &7» &8&m----------------------"));
        sender.spigot().sendMessage( firstComponent );
    }

    private static String  hoverPage(){
        return  "§7You can also navigate using the\n" +
                "§7hidden '/mvplv help <page>' command.";
    }

}
