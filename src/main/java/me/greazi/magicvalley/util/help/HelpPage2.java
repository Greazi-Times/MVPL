package me.greazi.magicvalley.util.help;

import me.greazi.magicvalley.util.text.ColorUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public class HelpPage2 {
	public static void HelpPage2(CommandSender sender){
		sender.sendMessage(HelpMain.getHelpHeader());
		displayPage1(sender);
		FooterPage2(sender);
	}

	public static void displayPage1(CommandSender sender) {
		sender.sendMessage( ColorUtil.addColors("&c We are still working on the helping page") );
		sender.sendMessage( ColorUtil.addColors("&c Because of this this page is empty") );
		sender.sendMessage( ColorUtil.addColors("&c ") );
		sender.sendMessage( ColorUtil.addColors("&c ") );
		sender.sendMessage( ColorUtil.addColors("&c ") );
		sender.sendMessage( ColorUtil.addColors("&c ") );
		sender.sendMessage( ColorUtil.addColors("&c ") );
		sender.sendMessage( ColorUtil.addColors("&c ") );
		sender.sendMessage( ColorUtil.addColors("&c ") );
		sender.sendMessage( ColorUtil.addColors("&c ") );
		sender.sendMessage( ColorUtil.addColors("&c ") );
		sender.sendMessage( ColorUtil.addColors("&c ") );
		sender.sendMessage( ColorUtil.addColors("&c ") );
	}
	public static void FooterPage2(CommandSender sender) {
		TextComponent firstComponent = new TextComponent(ColorUtil.addColors("&8&m----------------------"));
		firstComponent.setColor( ChatColor.DARK_GRAY );

		TextComponent subComponent1 = new TextComponent( "2/2" );
		subComponent1.setColor( ChatColor.GRAY );

		subComponent1.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( HelpMain.hoverPage() ).create() ) );
		subComponent1.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "" ) );

		TextComponent subComponent2 = new TextComponent( ColorUtil.addColors( " &6« " ) );
		subComponent2.setColor( ChatColor.GOLD );
		subComponent2.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( ColorUtil.addColors("&7Go to page 1" )).create() ) );
		subComponent2.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/mvpl ? 1" ) );

		firstComponent.addExtra( subComponent2 );
		firstComponent.addExtra( subComponent1 );
		firstComponent.addExtra( ColorUtil.addColors(" &7» &8&m----------------------"));
		sender.spigot().sendMessage( firstComponent );
	}

	private static String  hoverPage(){
		return  "§7You can also navigate using the\n" +
				"§7hidden '/mvpl help <page>' command.";
	}
}
