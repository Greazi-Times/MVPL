package me.greazi.magicvalley.util.text;

import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.Locale;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public class StringUtils {

    protected static DecimalFormat percent = new DecimalFormat("##0.00%");
    protected static DecimalFormat shortDecimal = new DecimalFormat("##0.0");

    public static String getCapitalized(String target) {
        return target.substring(0, 1).toUpperCase() + target.substring(1).toLowerCase(Locale.ENGLISH);
    }

    public static String ticksToSeconds(double ticks) {
        return shortDecimal.format(ticks / 20);
    }

    public static String buildStringAfterNthElement(@NotNull String @NotNull []args, int index) {
        StringBuilder trimMessage = new StringBuilder();

        for (int i = index; i < args.length; i++) {
            if(i + 1 >= args.length)
                trimMessage.append(args[i]);
            else
                trimMessage.append(args[i]).append(" ");
        }

        return trimMessage.toString();
    }

    public static String getPrettyItemString(Material material) {
        return createPrettyString(material.toString());
    }

    public static String getPrettyEntityTypeString(EntityType entity) {
        return createPrettyString(entity.toString());
    }

    
    public static String getWildcardConfigBlockDataString(BlockData data) {
        return getWildcardConfigMaterialString(data.getMaterial());
    }

    public static String getWildcardConfigMaterialString(Material data) {
        return StringUtils.getPrettyItemString(data).replace(" ", "_") + "|*";
    }

    public static String getFriendlyConfigBlockDataString(BlockData data) {
        switch(data.getMaterial()){
            case CHORUS_FLOWER:
            case COCOA:
            case WHEAT:
            case BEETROOTS:
            case CARROTS:
            case POTATOES:
            case NETHER_WART: {
                if (data instanceof Ageable) {
                    Ageable ageData = (Ageable) data;
                    if (ageData.getAge() == ageData.getMaximumAge()) {
                        return getPrettyItemString(data.getMaterial()).replace(" ", "_") + "_Ripe";
                    }
                }
                return getPrettyItemString(data.getMaterial()).replace(" ", "_") + "_Ungrown";
            }
        }
        return getPrettyItemString(data.getMaterial()).replace(" ", "_");
    }

    public static String getFriendlyConfigMaterialString(Material data) {
        return getPrettyItemString(data).replace(" ", "_");
    }

    public static String getExplicitConfigBlockDataString(BlockData data) {
        return getExplicitConfigMaterialString(data.getMaterial());
    }

    public static String getExplicitConfigMaterialString(Material data) {
        return StringUtils.getPrettyItemString(data).replace(" ", "_");
    }

    private static String createPrettyString(String baseString) {
        String[] substrings = baseString.split("_");
        String prettyString = "";
        int size = 1;

        for (String string : substrings) {
            prettyString = prettyString.concat(getCapitalized(string));

            if (size < substrings.length) {
                prettyString = prettyString.concat(" ");
            }

            size++;
        }

        return prettyString;
    }

    public static boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException nFE) {
            return false;
        }
    }

    public static boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException nFE) {
            return false;
        }
    }

}
