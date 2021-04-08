package me.greazi.MagicValley.modules;

import me.greazi.MagicValley.util.messages.Prefix;
import me.greazi.MagicValley.util.Color;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileManagement {
    static FileManagement instance = new FileManagement();

    Plugin p;

    FileConfiguration config;

    File cfile;

    FileConfiguration data;

    File datafile;

    FileConfiguration message;

    File messagefile;


    public static FileManagement getInstance() {
        return instance;
    }

    public void setup(Plugin p) {
        if (!p.getDataFolder().exists())
            p.getDataFolder().mkdir();
        File dir = new File(p.getDataFolder() + File.separator + "Log");
        if (!dir.exists())
            dir.mkdirs();
        this.cfile = new File(p.getDataFolder(), "config.yml");
        if (!this.cfile.exists()) {
            try {
                File en = new File(p.getDataFolder(), "/config.yml");
                InputStream E = getClass().getResourceAsStream("/config.yml");
                copyFile(E, en);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix.ConsolePrefix()) + Color.color("&econfig.yml created"));
        }
        this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.cfile);
        this.messagefile = new File(p.getDataFolder(), "message.yml");
        if (!this.messagefile.exists())
            try {
                File en = new File(p.getDataFolder(), "/message.yml");
                InputStream E = getClass().getResourceAsStream("/message.yml");
                copyFile(E, en);
            } catch (Exception e) {
                e.printStackTrace();
            }
        this.message = (FileConfiguration)YamlConfiguration.loadConfiguration(this.messagefile);
        this.datafile = new File(p.getDataFolder(), "data.yml");
        if (!this.datafile.exists()) {
            try {
                File en = new File(p.getDataFolder(), "/data.yml");
                InputStream E = getClass().getResourceAsStream("/data.yml");
                copyFile(E, en);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix.ConsolePrefix()) + Color.color("&edata.yml created"));
        }
        this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(this.datafile);

    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void saveConfig() {
        try {
            this.config.save(this.cfile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger()
                    .severe(ChatColor.RED + "Could not save config.yml!");
        }
    }

    public void reloadConfig() {
        this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.cfile);
    }

    public FileConfiguration getMessageFile() {
        return this.message;
    }

    public void saveMessageFile() {
        try {
            this.message.save(this.messagefile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger()
                    .severe(ChatColor.RED + "Could not save message.yml!");
        }
    }

    public void reloadLang() {
        this.message = (FileConfiguration)YamlConfiguration.loadConfiguration(this.messagefile);
    }

    public FileConfiguration getdata() {
        return this.data;
    }

    public void savedata() {
        try {
            this.data.save(this.datafile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger()
                    .severe(ChatColor.RED + "Could not save data.yml!");
        }
    }

    public void reloaddata() {
        this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(this.datafile);
    }

    public PluginDescriptionFile getDesc() {
        return this.p.getDescription();
    }

    public static void copyFile(InputStream in, File out) throws Exception {
        InputStream fis = in;
        FileOutputStream fos = new FileOutputStream(out);
        try {
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1)
                fos.write(buf, 0, i);
        } catch (Exception e) {
            throw e;
        } finally {
            if (fis != null)
                fis.close();
            if (fos != null)
                fos.close();
        }
    }
}
