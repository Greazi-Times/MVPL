package me.greazi.magicvalley.config;

import me.greazi.magicvalley.MVpl;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public abstract class AutoUpdateConfigLoader extends ConfigLoader {
    public AutoUpdateConfigLoader(String relativePath, String fileName, File dataFolder) {
        super(relativePath, fileName, dataFolder);
    }

    public AutoUpdateConfigLoader(String fileName, File dataFolder) {
        super(fileName, dataFolder);
    }

    @Deprecated
    public AutoUpdateConfigLoader(String relativePath, String fileName) {
        super(relativePath, fileName);
    }

    @Deprecated
    public AutoUpdateConfigLoader(String fileName) {
        super(fileName);
    }

    protected void saveConfig() {
        try {
            MVpl.p.getLogger().info("Saving changes to config file - "+fileName);
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void loadFile() {
        super.loadFile();
        FileConfiguration internalConfig = YamlConfiguration.loadConfiguration(MVpl.p.getResourceAsReader(fileName));

        Set<String> configKeys = config.getKeys(true);
        Set<String> internalConfigKeys = internalConfig.getKeys(true);

        boolean needSave = false;

        Set<String> oldKeys = new HashSet<>(configKeys);
        oldKeys.removeAll(internalConfigKeys);

        Set<String> newKeys = new HashSet<>(internalConfigKeys);
        newKeys.removeAll(configKeys);

        if (!newKeys.isEmpty() || !oldKeys.isEmpty()) {
            needSave = true;
        }

        for (String key : newKeys) {
            MVpl.p.debug("Adding new key: " + key + " = " + internalConfig.get(key));
            config.set(key, internalConfig.get(key));
        }

        if (needSave) {
            String output = config.saveToString();

            output = output.replace("  ", "    ");

            while (output.replaceAll("[//s]", "").startsWith("#")) {
                output = output.substring(output.indexOf('\n', output.indexOf('#')) + 1);
            }

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(MVpl.p.getResource(fileName)));
                LinkedHashMap<String, String> comments = new LinkedHashMap<>();
                StringBuilder temp = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("#")) {
                        temp.append(line).append("\n");
                    }
                    else if (line.contains(":")) {
                        line = line.substring(0, line.indexOf(":") + 1);
                        if (temp.length() > 0) {
                            if(comments.containsKey(line)) {
                                int index = 0;
                                while(comments.containsKey(line + index)) {
                                    index++;
                                }

                                line = line + index;
                            }

                            comments.put(line, temp.toString());
                            temp = new StringBuilder();
                        }
                    }
                }
                HashMap<String, Integer> indexed = new HashMap<>();
                for (String key : comments.keySet()) {
                    String actualkey = key.substring(0, key.indexOf(":") + 1);

                    int index = 0;
                    if(indexed.containsKey(actualkey)) {
                        index = indexed.get(actualkey);
                    }
                    boolean isAtTop = !output.contains("\n" + actualkey);
                    index = output.indexOf((isAtTop ? "" : "\n") + actualkey, index);

                    if (index >= 0) {
                        output = output.substring(0, index) + "\n" + comments.get(key) + output.substring(isAtTop ? index : index + 1);
                        indexed.put(actualkey, index + comments.get(key).length() + actualkey.length() + 1);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            if(dataFolder == null) {
                MVpl.p.getLogger().severe("Data folder should never be null!");
                return;
            }

            try {
                String saveName = fileName;
                if (!MVpl.p.getConfig().getBoolean("General.Config_Update_Overwrite", true)) {
                    saveName += ".new";
                }

                File newSaveFile = new File(dataFolder, saveName);
                FileWriter fileWriter = new FileWriter(newSaveFile.getAbsolutePath());
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write(output);
                writer.flush();
                writer.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
