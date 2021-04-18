package me.greazi.magicvalley.config;

import me.greazi.magicvalley.util.text.StringUtils;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

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
        // Validate all the settings!
        List<String> reason = new ArrayList<>();

        /* General Settings */
        if (getSaveInterval() <= 0) {
            reason.add("General.Save_Interval should be greater than 0!");
        }

        return noErrorsInConfig(reason);
    }

    /*
     * GENERAL SETTINGS
     */

    public String getLocale() { return config.getString("General.Locale", "en_US"); }
    public boolean getMOTDEnabled() { return config.getBoolean("General.MOTD_Enabled", true); }
    public boolean getDonateMessageEnabled() { return config.getBoolean("Commands.mcmmo.Donate_Message", true); }
    public int getSaveInterval() { return config.getInt("General.Save_Interval", 10); }


    /* Backups */
    public boolean getBackupsEnabled() { return config.getBoolean("Backups.Enabled", true); }
}
