package me.greazi.magicvalley.util.text;

import org.bukkit.ChatColor;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public class ColorUtil {
    public static String addColors(String input) {
        input = input.replaceAll("\\Q[[BLACK]]\\E", ChatColor.BLACK.toString());
        input = input.replaceAll("\\Q[[DARK_BLUE]]\\E", ChatColor.DARK_BLUE.toString());
        input = input.replaceAll("\\Q[[DARK_GREEN]]\\E", ChatColor.DARK_GREEN.toString());
        input = input.replaceAll("\\Q[[DARK_AQUA]]\\E", ChatColor.DARK_AQUA.toString());
        input = input.replaceAll("\\Q[[DARK_RED]]\\E", ChatColor.DARK_RED.toString());
        input = input.replaceAll("\\Q[[DARK_PURPLE]]\\E", ChatColor.DARK_PURPLE.toString());
        input = input.replaceAll("\\Q[[GOLD]]\\E", ChatColor.GOLD.toString());
        input = input.replaceAll("\\Q[[GRAY]]\\E", ChatColor.GRAY.toString());
        input = input.replaceAll("\\Q[[DARK_GRAY]]\\E", ChatColor.DARK_GRAY.toString());
        input = input.replaceAll("\\Q[[BLUE]]\\E", ChatColor.BLUE.toString());
        input = input.replaceAll("\\Q[[GREEN]]\\E", ChatColor.GREEN.toString());
        input = input.replaceAll("\\Q[[AQUA]]\\E", ChatColor.AQUA.toString());
        input = input.replaceAll("\\Q[[RED]]\\E", ChatColor.RED.toString());
        input = input.replaceAll("\\Q[[LIGHT_PURPLE]]\\E", ChatColor.LIGHT_PURPLE.toString());
        input = input.replaceAll("\\Q[[YELLOW]]\\E", ChatColor.YELLOW.toString());
        input = input.replaceAll("\\Q[[WHITE]]\\E", ChatColor.WHITE.toString());
        input = input.replaceAll("\\Q[[BOLD]]\\E", ChatColor.BOLD.toString());
        input = input.replaceAll("\\Q[[UNDERLINE]]\\E", ChatColor.UNDERLINE.toString());
        input = input.replaceAll("\\Q[[ITALIC]]\\E", ChatColor.ITALIC.toString());
        input = input.replaceAll("\\Q[[STRIKE]]\\E", ChatColor.STRIKETHROUGH.toString());
        input = input.replaceAll("\\Q[[MAGIC]]\\E", ChatColor.MAGIC.toString());
        input = input.replaceAll("\\Q[[RESET]]\\E", ChatColor.RESET.toString());

        input = input.replaceAll("\\Q&0\\E", ChatColor.BLACK.toString());
        input = input.replaceAll("\\Q&1\\E", ChatColor.DARK_BLUE.toString());
        input = input.replaceAll("\\Q&2\\E", ChatColor.DARK_GREEN.toString());
        input = input.replaceAll("\\Q&3\\E", ChatColor.DARK_AQUA.toString());
        input = input.replaceAll("\\Q&4\\E", ChatColor.DARK_RED.toString());
        input = input.replaceAll("\\Q&5\\E", ChatColor.DARK_PURPLE.toString());
        input = input.replaceAll("\\Q&6\\E", ChatColor.GOLD.toString());
        input = input.replaceAll("\\Q&7\\E", ChatColor.GRAY.toString());
        input = input.replaceAll("\\Q&8\\E", ChatColor.DARK_GRAY.toString());
        input = input.replaceAll("\\Q&9\\E", ChatColor.BLUE.toString());
        input = input.replaceAll("\\Q&a\\E", ChatColor.GREEN.toString());
        input = input.replaceAll("\\Q&b\\E", ChatColor.AQUA.toString());
        input = input.replaceAll("\\Q&c\\E", ChatColor.RED.toString());
        input = input.replaceAll("\\Q&d\\E", ChatColor.LIGHT_PURPLE.toString());
        input = input.replaceAll("\\Q&e\\E", ChatColor.YELLOW.toString());
        input = input.replaceAll("\\Q&f\\E", ChatColor.WHITE.toString());
        input = input.replaceAll("\\Q&l\\E", ChatColor.BOLD.toString());
        input = input.replaceAll("\\Q&n\\E", ChatColor.UNDERLINE.toString());
        input = input.replaceAll("\\Q&o\\E", ChatColor.ITALIC.toString());
        input = input.replaceAll("\\Q&m\\E", ChatColor.STRIKETHROUGH.toString());
        input = input.replaceAll("\\Q&?\\E", ChatColor.MAGIC.toString());
        input = input.replaceAll("\\Q&r\\E", ChatColor.RESET.toString());

        return input;
    }
}
