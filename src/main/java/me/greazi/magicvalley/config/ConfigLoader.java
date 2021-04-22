package me.greazi.magicvalley.config;

import me.greazi.magicvalley.MVpl;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public abstract class ConfigLoader {
    protected String fileName;
    protected final File configFile;
    protected FileConfiguration config;
    protected @NotNull final File dataFolder;

    public ConfigLoader(String relativePath, String fileName, @NotNull File dataFolder) {
        this.fileName = fileName;
        this.dataFolder = dataFolder;
        configFile = new File(dataFolder, relativePath + File.separator + fileName);
        loadFile();
    }

    public ConfigLoader(String fileName, @NotNull File dataFolder) {
        this.fileName = fileName;
        this.dataFolder = dataFolder;
        configFile = new File(dataFolder, fileName);
        loadFile();
    }

    @Deprecated
    public ConfigLoader(String relativePath, String fileName) {
        this.fileName = fileName;
        configFile = new File(MVpl.p.getDataFolder(), relativePath + File.separator + fileName);
        this.dataFolder = MVpl.p.getDataFolder();
        loadFile();
    }

    @Deprecated
    public ConfigLoader(String fileName) {
        this.fileName = fileName;
        configFile = new File(MVpl.p.getDataFolder(), fileName);
        this.dataFolder = MVpl.p.getDataFolder();
        loadFile();
    }

    protected void loadFile() {
        if (!configFile.exists()) {
            MVpl.p.debug("Creating MVpl " + fileName + " File...");

            try {
                MVpl.p.saveResource(fileName, false); // Normal files
            }
            catch (IllegalArgumentException ex) {
                MVpl.p.saveResource(configFile.getParentFile().getName() + File.separator + fileName, false); // Mod files
            }
        }
        else {
            MVpl.p.debug("Loading MVpl " + fileName + " File...");
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    protected abstract void loadKeys();

    protected boolean validateKeys() {
        return true;
    }

    protected boolean noErrorsInConfig(List<String> issues) {
        for (String issue : issues) {
            MVpl.p.getLogger().warning(issue);
        }

        return issues.isEmpty();
    }

    protected void validate() {
        if (validateKeys()) {
            MVpl.p.debug("No errors found in " + fileName + "!");
        }
        else {
            MVpl.p.getLogger().warning("Errors were found in " + fileName + "! MVpl was disabled!");
            MVpl.p.getServer().getPluginManager().disablePlugin(MVpl.p);
            MVpl.p.noErrorsInConfigFiles = false;
        }
    }

    public File getFile() {
        return configFile;
    }

    public void backup() {
        MVpl.p.getLogger().warning("You are using an old version of the " + fileName + " file.");
        MVpl.p.getLogger().warning("Your old file has been renamed to " + fileName + ".old and has been replaced by an updated version.");

        configFile.renameTo(new File(configFile.getPath() + ".old"));

        if (MVpl.p.getResource(fileName) != null) {
            MVpl.p.saveResource(fileName, true);
        }

        MVpl.p.getLogger().warning("Reloading " + fileName + " with new values...");
        loadFile();
        loadKeys();
    }
}
