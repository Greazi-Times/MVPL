package me.greazi.magicvalley.util.text;

import me.greazi.magicvalley.MVpl;
import me.greazi.magicvalley.json.MVplUrl;
import me.greazi.magicvalley.json.MVplWebLinks;
import me.greazi.magicvalley.locale.LocaleLoader;
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

        // TODO Sends a null message
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

    public static void sendPlayerSubSkillList(@NotNull Player player, @NotNull List<Component> subSkillComponents) {
        final Audience audience = MVpl.getAudiences().player(player);

        Component space = Component.space();
        TextComponent atSignComponent = Component.text(LocaleLoader.getString("JSON.Hover.AtSymbolSkills"));

        Component[][] splitSubSkills = TextUtils.splitComponentsIntoGroups(subSkillComponents, 3);
        ArrayList<Component> individualLinesToSend = new ArrayList<>();

        for (Component[] componentArray : splitSubSkills) {
            individualLinesToSend.add(TextUtils.fromArray(componentArray, atSignComponent, space));
        }

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


