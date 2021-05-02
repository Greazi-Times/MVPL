package me.greazi.magicvalley.util.help;

import me.greazi.magicvalley.locale.LocaleLoader;
import me.greazi.magicvalley.util.text.ColorUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;

public class Commands {
	public static void MVplCommand(CommandSender sender) {
		TextComponent mvplCommand = new TextComponent(LocaleLoader.getString("Commands.Command.MVpl"));

		mvplCommand.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( ColorUtil.addColors( MVplHover() )).create() ));
		mvplCommand.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "" ));

		sender.spigot().sendMessage( mvplCommand );
	}

	private static String  MVplHover(){
		return  "§7Description: §fThe main command of MagicValley-Plugin\n" +
				"§7Permission: §fmvpl.commands.mvpl\n" +
				"§7Usage:\n" +
				"§f/mvpl §7- Get more information about the plugin\n" +
		        "§f/mvpl §2[help] [page:§6<number>§2] §7- Get the help page of the plugin\n";

	}

	public static void MVtestCommand(CommandSender sender) {
		TextComponent mvtestCommand = new TextComponent(LocaleLoader.getString("Commands.Command.MVtest"));

		mvtestCommand.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( ColorUtil.addColors( MVtestHover() )).create() ));
		mvtestCommand.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "" ));

		sender.spigot().sendMessage( mvtestCommand );
	}

	private static String  MVtestHover(){
		return  "§7Description: §fThe offical test command for new commands of MagicValley-Plugin\n" +
				"§7Permission: §fmvpl.commands.test\n" +
				"§7Usage:\n" +
				"§f/mvtest §7- This command has 1 main command /mvtest possible arguments are not shown\n" +
				"§c \n" +
				"§4§lWARNING: §cThis command is a developer command and is know to cause bugs on usage\n" +
				"§cPlease be carful on usage of this command§c§l!";
	}
}
