package me.greazi.magicvalley.json;

import me.greazi.magicvalley.locale.LocaleLoader;
import me.greazi.magicvalley.util.text.StringUtils;

public enum MVplWebLinks {
    WEBSITE,
    DISCORD,
    PATREON,
    HELP_TRANSLATE,
    WIKI;

    public String getUrl()
    {
        return MVplUrl.getUrl(this);
    }

    public String getNiceTitle()
    {
        return StringUtils.getCapitalized(toString());
    }

    public String getLocaleDescription()
    {
        switch (this)
        {
            case WEBSITE:
                return LocaleLoader.getString( "JSON.URL.Website");
            case DISCORD:
                return LocaleLoader.getString( "JSON.URL.Discord");
            case PATREON:
                return LocaleLoader.getString( "JSON.URL.Patreon");
            case HELP_TRANSLATE:
                return LocaleLoader.getString( "JSON.URL.Translation");
            case WIKI:
                return LocaleLoader.getString("JSON.URL.Wiki");
            default:
                return "";
        }
    }
}
