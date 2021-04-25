package me.greazi.magicvalley.json;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public class MVplUrl {
    public static final String urlWebsite   = "https://www.magicvalley.nl";
    public static final String urlDiscord   = "https://discord.magicvalley.nl";
    public static final String urlPatreon   = "https://www.patreon.com/MagicValley";
    public static final String urlWiki      = "https://www.magicvalley.nl/wiki/";
    public static final String urlTranslate = "https://translate.magicvalley.nl";

    public static String getUrl(MVplWebLinks webLinks)
    {
        switch(webLinks)
        {
            case WIKI:
                return urlWiki;
            case PATREON:
                return urlPatreon;
            case DISCORD:
                return urlDiscord;
            case WEBSITE:
                return urlWebsite;
            case HELP_TRANSLATE:
                return urlTranslate;
            default:
                return "https://www.magicvalley.nl";
        }
    }
}
