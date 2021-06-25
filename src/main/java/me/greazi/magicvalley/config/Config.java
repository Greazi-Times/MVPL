package me.greazi.magicvalley.config;

import java.util.ArrayList;
import java.util.List;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public class Config extends AutoUpdateConfigLoader {
    private static Config instance;

    private Config() {
        super("config.yml");
        validate();
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }

        return instance;
    }

    @Override
    protected void loadKeys() {

    }

    @Override
    protected boolean validateKeys() {
        List<String> reason = new ArrayList<>();

        if (getSaveInterval() <= 0) {
            reason.add("General.Save_Interval should be greater than 0!");
        }

        return noErrorsInConfig(reason);
    }

    //GENERAL SETTINGS
    public String getLocale() { return config.getString("General.Locale", "en_us"); }
    public boolean getMOTDEnabled() { return config.getBoolean("General.MOTD_Enabled", true); }
    public boolean getDonateMessageEnabled() { return config.getBoolean("Commands.MVPL.Donate_Message", true); }
    public int getSaveInterval() { return config.getInt("General.Save_Interval", 10); }

    public boolean getMatchOfflinePlayers() { return config.getBoolean("Commands.Generic.Match_OfflinePlayers", false); }

    /* Backups */
    public boolean getBackupsEnabled() { return config.getBoolean("Backups.Enabled", true); }
    public boolean getKeepLast24Hours() { return config.getBoolean("Backups.Keep.Last_24_Hours", true); }
    public boolean getKeepDailyLastWeek() { return config.getBoolean("Backups.Keep.Daily_Last_Week", true); }
    public boolean getKeepWeeklyPastMonth() { return config.getBoolean("Backups.Keep.Weekly_Past_Months", true); }

    /* Armorstand */
    public boolean getLeftClickEnabled() { return config.getBoolean("modules.ascmd.leftclick", true); }

    /* Config Version */
    public int getConfigVersion() { return config.getInt("ConfigVersion"); }

    private String getStringIncludingInts(String key) {
        String str = config.getString(key);

        if (str == null) {
            str = String.valueOf(config.getInt(key));
        }

        if (str.equals("0")) {
            str = "No value set for '" + key + "'";
        }
        return str;
    }

}
