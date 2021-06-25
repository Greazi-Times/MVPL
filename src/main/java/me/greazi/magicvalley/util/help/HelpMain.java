package me.greazi.magicvalley.util.help;

import me.greazi.magicvalley.MVpl;
import me.greazi.magicvalley.locale.LocaleLoader;
import me.greazi.magicvalley.util.text.ColorUtil;
import net.md_5.bungee.api.ChatColor;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public class HelpMain {
	public static String[] getHelpHeader() {
		return new String[] { ColorUtil.addColors("&8"), ColorUtil.addColors("&8") +

				LocaleLoader.getString("Message.Component.Header.Help"),
				getHeaderPrefix() + "  " + MVpl.p.getName() + ColorUtil.addColors("&8") + ColorUtil.addColors(" &7") + MVpl.p.getDescription().getVersion(), " ",
				ColorUtil.addColors("&2  [] &f= ") + LocaleLoader.getString("Message.Component.Part.OptionalArgs"),
				ColorUtil.addColors("&6  <> &f= ") + LocaleLoader.getString("Message.Component.Part.RequiredArgs"), " " };
	}

	protected static String getHeaderPrefix() {
		return "" + ChatColor.GOLD + ChatColor.BOLD;
	}

	public static String  hoverPage(){
		return  "§7You can also navigate using the\n" +
				"§7hidden '/mvpl help <page>' command.";
	}
}
