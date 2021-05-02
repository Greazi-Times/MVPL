package me.greazi.magicvalley.util.help;

import me.greazi.magicvalley.util.text.ColorUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;

public class HelpPage1 {
	public static void HelpPage1(CommandSender sender){
		sender.sendMessage(HelpMain.getHelpHeader());
		displayPage1(sender);
		FooterPage1(sender);
	}

	public static void displayPage1(CommandSender sender) {
		Commands.MVplCommand(sender);
		Commands.MVtestCommand(sender);
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

	public static void FooterPage1(CommandSender sender) {
		TextComponent firstComponent = new TextComponent(ColorUtil.addColors("&8&m----------------------&7 « "));
		firstComponent.setColor( ChatColor.DARK_GRAY );

		TextComponent subComponent1 = new TextComponent( "1/2" );
		subComponent1.setColor( ChatColor.GRAY );
		subComponent1.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( ColorUtil.addColors( HelpMain.hoverPage() )).create() ) );

		subComponent1.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "" ) );

		TextComponent subComponent2 = new TextComponent( ColorUtil.addColors( " &6» " ) );
		subComponent2.setColor( ChatColor.GOLD );
		subComponent2.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( ColorUtil.addColors("&7Go to page 2" )).create() ) );
		subComponent2.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/mvpl ? 2" ) );

		firstComponent.addExtra( subComponent1 );
		firstComponent.addExtra( subComponent2 );
		firstComponent.addExtra( ColorUtil.addColors("&8&m----------------------"));
		sender.spigot().sendMessage( firstComponent );
	}

	private static String  hoverPage(){
		return  "§7You can also navigate using the\n" +
				"§7hidden '/mvpl help <page>' command.";
	}
}
