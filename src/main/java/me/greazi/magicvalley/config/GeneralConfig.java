package me.greazi.magicvalley.config;

import org.jetbrains.annotations.NotNull;

import java.io.File;
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

public class GeneralConfig extends AutoUpdateConfigLoader {

    public GeneralConfig(@NotNull File dataFolder) {
        super("config.yml", dataFolder);
        validate();
    }

    @Override
    protected void loadKeys() {

    }

    @Override
    protected boolean validateKeys() {
        List<String> reason = new ArrayList<>();

        // General Settings
        if (getSaveInterval() <= 0) {
            reason.add("General.Save_Interval should be greater than 0!");
        }

        return noErrorsInConfig(reason);
    }

    // GENERAL SETTINGS
    public String getLocale() { return config.getString("General.Locale", "en_US"); }
    public boolean getMOTDEnabled() { return config.getBoolean("General.MOTD_Enabled", true); }
    public boolean getDonateMessageEnabled() { return config.getBoolean("Commands.MVPL.Donate_Message", true); }
    public int getSaveInterval() { return config.getInt("General.Save_Interval", 10); }


    // Backups
    public boolean getBackupsEnabled() { return config.getBoolean("Backups.Enabled", true); }

    /* Config Version */
    public int getConfigVersion() { return config.getInt("ConfigVersion"); }
}
