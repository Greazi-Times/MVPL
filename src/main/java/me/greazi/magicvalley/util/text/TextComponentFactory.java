package me.greazi.magicvalley.util.text;

import me.greazi.magicvalley.MVpl;
import me.greazi.magicvalley.json.MVplUrl;
import me.greazi.magicvalley.json.MVplWebLinks;
import me.greazi.magicvalley.locale.LocaleLoader;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public class TextComponentFactory {

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
        TextComponent suffix = Component.text(" "+LocaleLoader.getString("Overhaul.MVpl.Url.Wrap.Suffix"));

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
                componentBuilder.append(Component.text("\nInformation about MagicValley can be found here", NamedTextColor.DARK_GRAY));
                break;
            case PATREON:
                addUrlHeaderHover(webLinks, componentBuilder);
                componentBuilder.append(Component.newline()).append(Component.newline());
                componentBuilder.append(Component.text(webLinks.getLocaleDescription(), NamedTextColor.GREEN));
                componentBuilder.append(Component.newline());
                componentBuilder.append(Component.text("Show support by helping me financially", NamedTextColor.DARK_GRAY));
                break;
            case WIKI:
                addUrlHeaderHover(webLinks, componentBuilder);
                componentBuilder.append(Component.newline()).append(Component.newline());
                componentBuilder.append(Component.text(webLinks.getLocaleDescription(), NamedTextColor.GREEN));
                componentBuilder.append(Component.newline());
                componentBuilder.append(Component.text("Need some help? The wiki's will explain everything!", NamedTextColor.DARK_GRAY));
                break;
            case DISCORD:
                addUrlHeaderHover(webLinks, componentBuilder);
                componentBuilder.append(Component.newline()).append(Component.newline());
                componentBuilder.append(Component.text(webLinks.getLocaleDescription(), NamedTextColor.GREEN));
                componentBuilder.append(Component.newline());
                componentBuilder.append(Component.text("Join our discord server!", NamedTextColor.DARK_GRAY));
                break;
            case HELP_TRANSLATE:
                addUrlHeaderHover(webLinks, componentBuilder);
                componentBuilder.append(Component.newline()).append(Component.newline());
                componentBuilder.append(Component.text(webLinks.getLocaleDescription(), NamedTextColor.GREEN));
                componentBuilder.append(Component.newline());
                componentBuilder.append(Component.text("We are working on translating the server to more languages!" +
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

    private static TextComponent.Builder getNewComponentBuilder(String skillName) {
        TextComponent.Builder componentBuilder = Component.text().content(skillName);
        componentBuilder.append(Component.newline());
        return componentBuilder;
    }

}


