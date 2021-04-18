package me.greazi.magicvalley.util.text;

import me.greazi.magicvalley.json.MVplUrl;
import me.greazi.magicvalley.json.MVplWebLinks;
import me.greazi.magicvalley.locale.LocaleLoader;
import me.greazi.magicvalley.MVpl;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles many of the JSON components that mcMMO makes and uses
 */
public class TextComponentFactory {

    /**
     * Makes a text component using strings from a locale and supports passing an undefined number of variables to the LocaleLoader
     * @param localeKey target locale string address
     * @param values vars to be passed to the locale loader
     * @return
     */
    public static TextComponent getNotificationMultipleValues(String localeKey, String... values)
    {
        String preColoredString = LocaleLoader.getString(localeKey, (Object[]) values);
        return Component.text(preColoredString);
    }

    public static Component getNotificationTextComponentFromLocale(String localeKey)
    {
        return getNotificationTextComponent(LocaleLoader.getString(localeKey));
    }

    private static TextComponent getNotificationTextComponent(String text)
    {
        //textComponent.setColor(getNotificationColor(notificationType));
        return Component.text(text);
    }

    public static void sendPlayerUrlHeader(Player player) {
        TextComponent prefix = Component.text(LocaleLoader.getString("Overhaul.MVpl.Url.Wrap.Prefix") + " ");
        /*prefix.setColor(ChatColor.DARK_AQUA);*/
        TextComponent suffix = Component.text(" "+LocaleLoader.getString("Overhaul.MVpl.Url.Wrap.Suffix"));
        /*suffix.setColor(ChatColor.DARK_AQUA);*/

        TextComponent emptySpace = Component.space();

        MVpl.getAudiences().player(player).sendMessage(Identity.nil(),TextComponent.ofChildren(
          prefix,
          getWebLinkTextComponent(MVplWebLinks.WEBSITE),
          emptySpace,
          getWebLinkTextComponent(MVplWebLinks.DISCORD),
          emptySpace,
          getWebLinkTextComponent(MVplWebLinks.PATREON),
          emptySpace,
          getWebLinkTextComponent(MVplWebLinks.WIKI),
          emptySpace,
          getWebLinkTextComponent(MVplWebLinks.HELP_TRANSLATE),
          suffix
        ), MessageType.SYSTEM);
    }

    /**
     * Sends a player a bunch of text components that represent a list of sub-skills
     * Styling and formatting is applied before sending the messages
     *
     * @param player target player
     * @param subSkillComponents the text components representing the sub-skills by name
     */
    public static void sendPlayerSubSkillList(@NotNull Player player, @NotNull List<Component> subSkillComponents) {
        final Audience audience = MVpl.getAudiences().player(player);

        //@ Signs, done for style
        Component space = Component.space();
        TextComponent atSignComponent = Component.text(LocaleLoader.getString("JSON.Hover.AtSymbolSkills"));

        //Only send 3 sub-skills per line
        Component[][] splitSubSkills = TextUtils.splitComponentsIntoGroups(subSkillComponents, 3);
        ArrayList<Component> individualLinesToSend = new ArrayList<>();

        //Create each line
        for (Component[] componentArray : splitSubSkills) {
            individualLinesToSend.add(TextUtils.fromArray(componentArray, atSignComponent, space));
        }

        //Send each group
        for(Component curLine : individualLinesToSend) {
            audience.sendMessage(Identity.nil(), curLine);
        }
    }

    private static Component getWebLinkTextComponent(MVplWebLinks webLinks)
    {
        TextComponent.Builder webTextComponent;

        switch(webLinks)
        {
            case WEBSITE:
                webTextComponent = Component.text().content(LocaleLoader.getString("JSON.Hover.AtSymbolURL"));
                TextUtils.addChildWebComponent(webTextComponent, "Web");
                webTextComponent.clickEvent(getUrlClickEvent(MVplUrl.urlWebsite));
                break;
            case SPIGOT:
                webTextComponent = Component.text().content(LocaleLoader.getString("JSON.Hover.AtSymbolURL"));
                TextUtils.addChildWebComponent(webTextComponent, "Spigot");
                webTextComponent.clickEvent(getUrlClickEvent(MVplUrl.urlSpigot));
                break;
            case DISCORD:
                webTextComponent = Component.text().content(LocaleLoader.getString("JSON.Hover.AtSymbolURL"));
                TextUtils.addChildWebComponent(webTextComponent, "Discord");
                webTextComponent.clickEvent(getUrlClickEvent(MVplUrl.urlDiscord));
                break;
            case PATREON:
                webTextComponent = Component.text().content(LocaleLoader.getString("JSON.Hover.AtSymbolURL"));
                TextUtils.addChildWebComponent(webTextComponent, "Patreon");
                webTextComponent.clickEvent(getUrlClickEvent(MVplUrl.urlPatreon));
                break;
            case WIKI:
                webTextComponent = Component.text().content(LocaleLoader.getString("JSON.Hover.AtSymbolURL"));
                TextUtils.addChildWebComponent(webTextComponent, "Wiki");
                webTextComponent.clickEvent(getUrlClickEvent(MVplUrl.urlWiki));
                break;
            case HELP_TRANSLATE:
                webTextComponent = Component.text().content(LocaleLoader.getString("JSON.Hover.AtSymbolURL"));
                TextUtils.addChildWebComponent(webTextComponent, "Lang");
                webTextComponent.clickEvent(getUrlClickEvent(MVplUrl.urlTranslate));
                break;
            default:
                webTextComponent = Component.text().content("NOT DEFINED");
        }

        TextUtils.addNewHoverComponentToTextComponent(webTextComponent, getUrlHoverEvent(webLinks));
        webTextComponent.insertion(webLinks.getUrl());

        return webTextComponent.build();
    }

    private static Component getUrlHoverEvent(MVplWebLinks webLinks)
    {
        TextComponent.Builder componentBuilder = Component.text().content(webLinks.getNiceTitle());

        switch(webLinks)
        {
            case WEBSITE:
                addUrlHeaderHover(webLinks, componentBuilder);
                componentBuilder.append(Component.newline()).append(Component.newline());
                componentBuilder.append(Component.text(webLinks.getLocaleDescription(), NamedTextColor.GREEN));
                componentBuilder.append(Component.text("\nDev Blogs, and information related to mcMMO can be found here", NamedTextColor.GRAY));
                break;
            case SPIGOT:
                addUrlHeaderHover(webLinks, componentBuilder);
                componentBuilder.append(Component.newline()).append(Component.newline());
                componentBuilder.append(Component.text(webLinks.getLocaleDescription(), NamedTextColor.GREEN));
                componentBuilder.append(Component.text("\nI post regularly in the discussion thread here!", NamedTextColor.GRAY));
                break;
            case PATREON:
                addUrlHeaderHover(webLinks, componentBuilder);
                componentBuilder.append(Component.newline()).append(Component.newline());
                componentBuilder.append(Component.text(webLinks.getLocaleDescription(), NamedTextColor.GREEN));
                componentBuilder.append(Component.newline());
                componentBuilder.append(Component.text("Show support by buying me a coffee :)", NamedTextColor.GRAY));
                break;
            case WIKI:
                addUrlHeaderHover(webLinks, componentBuilder);
                componentBuilder.append(Component.newline()).append(Component.newline());
                componentBuilder.append(Component.text(webLinks.getLocaleDescription(), NamedTextColor.GREEN));
                componentBuilder.append(Component.newline());
                componentBuilder.append(Component.text("I'm looking for more wiki staff, contact me on our discord!", NamedTextColor.DARK_GRAY));
                break;
            case DISCORD:
                addUrlHeaderHover(webLinks, componentBuilder);
                componentBuilder.append(Component.newline()).append(Component.newline());
                componentBuilder.append(Component.text(webLinks.getLocaleDescription(), NamedTextColor.GREEN));
                break;
            case HELP_TRANSLATE:
                addUrlHeaderHover(webLinks, componentBuilder);
                componentBuilder.append(Component.newline()).append(Component.newline());
                componentBuilder.append(Component.text(webLinks.getLocaleDescription(), NamedTextColor.GREEN));
                componentBuilder.append(Component.newline());
                componentBuilder.append(Component.text("You can use this website to help translate mcMMO into your language!" +
                  "\nIf you want to know more contact me in discord.", NamedTextColor.DARK_GRAY));
        }

        return componentBuilder.build();
    }

    private static void addUrlHeaderHover(MVplWebLinks webLinks, TextComponent.Builder componentBuilder) {
        componentBuilder.append(Component.newline());
        componentBuilder.append(Component.text(webLinks.getUrl(), NamedTextColor.GRAY, TextDecoration.ITALIC));
    }

    private static ClickEvent getUrlClickEvent(String url)
    {
        return ClickEvent.openUrl(url);
    }

}


