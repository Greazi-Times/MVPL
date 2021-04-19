package me.greazi.magicvalley.locale;

import me.greazi.magicvalley.config.Config;
import me.greazi.magicvalley.MVpl;
import me.greazi.magicvalley.util.text.ColorUtil;
import me.greazi.magicvalley.util.text.TextUtils;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.*;
import java.util.logging.Level;

public final class LocaleLoader {
    private static final String BUNDLE_ROOT = "me.greazi.magicvalley.locale.locale";
    private static Map<String, String> bundleCache = new HashMap<>();
    private static ResourceBundle bundle = null;
    private static ResourceBundle filesystemBundle = null;
    private static ResourceBundle enBundle = null;

    private LocaleLoader() {}

    public static String getString(String key) {
        return getString(key, (Object[]) null);
    }

    /**
     * Gets the appropriate string from the Locale files.
     *
     * @param key The key to look up the string with
     * @param messageArguments Any arguments to be added to the string
     * @return The properly formatted locale string
     */
    public static String getString(String key, Object... messageArguments) {
        if (bundle == null) {
            initialize();
        }

        String rawMessage = bundleCache.computeIfAbsent(key, LocaleLoader::getRawString);
        return formatString(rawMessage, messageArguments);
    }

    //TODO: Remove this hacky crap with something better later
    /**
     * Gets the appropriate TextComponent representation of a formatted string from the Locale files.
     *
     * @param key The key to look up the string with
     * @param messageArguments Any arguments to be added to the text component
     * @return The properly formatted text component
     */
    public static TextComponent getTextComponent(String key, Object... messageArguments) {
        if (bundle == null) {
            initialize();
        }

        String rawMessage = bundleCache.computeIfAbsent(key, LocaleLoader::getRawString);
        return formatComponent(rawMessage, messageArguments);
    }

    /**
     * Reloads locale
     */
    public static void reloadLocale() {
        bundle = null;
        filesystemBundle = null;
        enBundle = null;
        bundleCache = new HashMap<>(); // Cheaper to replace than clear()
        initialize();
    }

    private static String getRawString(String key) {
        if (filesystemBundle != null) {
            try {
                return filesystemBundle.getString(key);
            }
            catch (MissingResourceException ignored) {}
        }

        try {
            return bundle.getString(key);
        }
        catch (MissingResourceException ignored) {}

        try {
            return enBundle.getString(key);
        }
        catch (MissingResourceException ignored) {
            if (!key.contains("Guides")) {
                MVpl.p.getLogger().warning("Could not find locale string: " + key);
            }

            return '!' + key + '!';
        }
    }

    public static String formatString(String string, Object... messageArguments) {
        if (messageArguments != null) {
            MessageFormat formatter = new MessageFormat("");
            formatter.applyPattern(string.replace("'", "''"));
            string = formatter.format(messageArguments);
        }

        string = ColorUtil.addColors(string);

        return string;
    }

    public static TextComponent formatComponent(String string, Object... messageArguments) {
        if (messageArguments != null) {
            MessageFormat formatter = new MessageFormat("");
            formatter.applyPattern(string.replace("'", "''"));
            string = formatter.format(messageArguments);
        }

        return TextUtils.colorizeText(string);
    }

    public static Locale getCurrentLocale() {
        if (bundle == null) {
            initialize();
        }
        return bundle.getLocale();
    }

    private static void initialize() {
        if (bundle == null) {
            Locale.setDefault(new Locale("en", "US"));
            Locale locale = null;

            String[] myLocale = MVpl.p.getGeneralConfig().getLocale().split("[-_ ]");

            if (myLocale.length == 1) {
                locale = new Locale(myLocale[0]);
            }
            else if (myLocale.length >= 2) {
                locale = new Locale(myLocale[0], myLocale[1]);
            }

            if (locale == null) {
                throw new IllegalStateException("Failed to parse locale string '" + MVpl.p.getGeneralConfig().getLocale() + "'");
            }

            Path localePath = Paths.get(MVpl.getLocalesDirectory() + "locale_" + locale.toString() + ".properties");
            if (Files.exists(localePath) && Files.isRegularFile(localePath)) {
                try (Reader localeReader = Files.newBufferedReader(localePath)) {
                    MVpl.p.getLogger().log(Level.INFO, "Loading locale from {0}", localePath);
                    filesystemBundle = new PropertyResourceBundle(localeReader);
                } catch (IOException e) {
                    MVpl.p.getLogger().log(Level.WARNING, "Failed to load locale from " + localePath, e);
                }
            }
            bundle = ResourceBundle.getBundle(BUNDLE_ROOT, locale);
            enBundle = ResourceBundle.getBundle(BUNDLE_ROOT, Locale.US);
        }
    }
}
