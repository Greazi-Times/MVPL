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

    /* General Settings */
    public boolean getIsMetricsEnabled() { return config.getBoolean("Metrics.bstats", true); }

    //Retro mode will default the value to true if the config file doesn't contain the entry (server is from a previous mcMMO install)
    public boolean getIsRetroMode() { return config.getBoolean("General.RetroMode.Enabled", true); }

    public String getLocale() { return config.getString("General.Locale", "en_us"); }
    public boolean getMOTDEnabled() { return config.getBoolean("General.MOTD_Enabled", true); }
    public boolean getShowProfileLoadedMessage() { return config.getBoolean("General.Show_Profile_Loaded", true); }
    public boolean getDonateMessageEnabled() { return config.getBoolean("Commands.mcmmo.Donate_Message", true); }
    public int getSaveInterval() { return config.getInt("General.Save_Interval", 10); }
    public boolean getStatsTrackingEnabled() { return config.getBoolean("General.Stats_Tracking", true); }
    public boolean getUpdateCheckEnabled() { return config.getBoolean("General.Update_Check", true); }
    public boolean getPreferBeta() { return config.getBoolean("General.Prefer_Beta", false); }
    public boolean getVerboseLoggingEnabled() { return config.getBoolean("General.Verbose_Logging", false); }


    public boolean getMatchOfflinePlayers() { return config.getBoolean("Commands.Generic.Match_OfflinePlayers", false); }
    public long getDatabasePlayerCooldown() { return config.getLong("Commands.Database.Player_Cooldown", 1750); }

    public boolean getLevelUpSoundsEnabled() { return config.getBoolean("General.LevelUp_Sounds", true); }
    public boolean getRefreshChunksEnabled() { return config.getBoolean("General.Refresh_Chunks", false); }

    public boolean getMobHealthbarEnabled() { return config.getBoolean("Mob_Healthbar.Enabled", true); }


    public int getMobHealthbarTime() { return config.getInt("Mob_Healthbar.Display_Time", 3); }

    /* Scoreboards */
    public boolean getScoreboardsEnabled() { return config.getBoolean("Scoreboard.UseScoreboards", true); }
    public boolean getPowerLevelTagsEnabled() { return config.getBoolean("Scoreboard.Power_Level_Tags", false); }
    public boolean getAllowKeepBoard() { return config.getBoolean("Scoreboard.Allow_Keep", true); }
    public int getTipsAmount() { return config.getInt("Scoreboard.Tips_Amount", 5); }
    public boolean getShowStatsAfterLogin() { return config.getBoolean("Scoreboard.Show_Stats_After_Login", false); }
    public boolean getScoreboardRainbows() { return config.getBoolean("Scoreboard.Rainbows", false); }
    public boolean getShowAbilityNames() { return config.getBoolean("Scoreboard.Ability_Names", true); }

    public boolean getRankUseChat() { return config.getBoolean("Scoreboard.Types.Rank.Print", false); }
    public boolean getRankUseBoard() { return config.getBoolean("Scoreboard.Types.Rank.Board", true); }
    public int getRankScoreboardTime() { return config.getInt("Scoreboard.Types.Rank.Display_Time", 10); }

    public boolean getTopUseChat() { return config.getBoolean("Scoreboard.Types.Top.Print", true); }
    public boolean getTopUseBoard() { return config.getBoolean("Scoreboard.Types.Top.Board", true); }
    public int getTopScoreboardTime() { return config.getInt("Scoreboard.Types.Top.Display_Time", 15); }

    public boolean getStatsUseChat() { return config.getBoolean("Scoreboard.Types.Stats.Print", true); }
    public boolean getStatsUseBoard() { return config.getBoolean("Scoreboard.Types.Stats.Board", true); }
    public int getStatsScoreboardTime() { return config.getInt("Scoreboard.Types.Stats.Display_Time", 10); }

    public boolean getInspectUseChat() { return config.getBoolean("Scoreboard.Types.Inspect.Print", true); }
    public boolean getInspectUseBoard() { return config.getBoolean("Scoreboard.Types.Inspect.Board", true); }
    public int getInspectScoreboardTime() { return config.getInt("Scoreboard.Types.Inspect.Display_Time", 25); }

    public boolean getCooldownUseChat() { return config.getBoolean("Scoreboard.Types.Cooldown.Print", false); }
    public boolean getCooldownUseBoard() { return config.getBoolean("Scoreboard.Types.Cooldown.Board", true); }
    public int getCooldownScoreboardTime() { return config.getInt("Scoreboard.Types.Cooldown.Display_Time", 41); }

    public boolean getSkillUseBoard() { return config.getBoolean("Scoreboard.Types.Skill.Board", true); }
    public int getSkillScoreboardTime() { return config.getInt("Scoreboard.Types.Skill.Display_Time", 30); }
    public boolean getSkillLevelUpBoard() { return config.getBoolean("Scoreboard.Types.Skill.LevelUp_Board", true); }
    public int getSkillLevelUpTime() { return config.getInt("Scoreboard.Types.Skill.LevelUp_Time", 5); }

    /* Database Purging */
    public int getPurgeInterval() { return config.getInt("Database_Purging.Purge_Interval", -1); }
    public int getOldUsersCutoff() { return config.getInt("Database_Purging.Old_User_Cutoff", 6); }

    /* Backups */
    public boolean getBackupsEnabled() { return config.getBoolean("Backups.Enabled", true); }
    public boolean getKeepLast24Hours() { return config.getBoolean("Backups.Keep.Last_24_Hours", true); }
    public boolean getKeepDailyLastWeek() { return config.getBoolean("Backups.Keep.Daily_Last_Week", true); }
    public boolean getKeepWeeklyPastMonth() { return config.getBoolean("Backups.Keep.Weekly_Past_Months", true); }

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
