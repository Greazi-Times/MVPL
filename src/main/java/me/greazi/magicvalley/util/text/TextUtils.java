package me.greazi.magicvalley.util.text;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public class TextUtils {
    private static @Nullable LegacyComponentSerializer customLegacySerializer;

    private TextUtils() {

    }

    static @NotNull Component fromArray(@NotNull Component[] componentsArray, @Nullable Component prefixComponent, @Nullable Component suffixComponent) {
        TextComponent.Builder componentBuilder = Component.text();

        for(Component component : componentsArray) {
            if(component == null)
                continue;

            if(prefixComponent != null)
                componentBuilder.append(prefixComponent);

            componentBuilder.append(component);

            if(suffixComponent != null)
                componentBuilder.append(suffixComponent);

        }

        return componentBuilder.build();
    }

    static @NotNull Component[][] splitComponentsIntoGroups(@NotNull List<Component> components, int groupsSize) {
        int groupCount = (int) Math.ceil((double) components.size() / (double) groupsSize);

        Component[][] splitGroups = new Component[groupCount][groupsSize];

        int groupsFinished = 0;

        while (groupsFinished < groupCount) {
            for(int i = 0; i < groupsSize; i++) {
                int indexOfPotentialMember = i + (groupsFinished * 3);

                if(indexOfPotentialMember > components.size()-1) {
                    break;
                }

                Component potentialMember = components.get(indexOfPotentialMember);

                if(potentialMember != null) {
                    splitGroups[groupsFinished][i] = potentialMember;
                }
            }

            groupsFinished++;
        }

        return splitGroups;
    }

    static void addChildWebComponent(@NotNull ComponentBuilder<?, ?> webTextComponent, @NotNull String childName) {
        TextComponent childComponent = Component.text(childName).color(NamedTextColor.GOLD);
        webTextComponent.append(childComponent);
    }

    static void addNewHoverComponentToTextComponent(@NotNull TextComponent.Builder textComponent, @NotNull Component baseComponent) {
        textComponent.hoverEvent(HoverEvent.showText(baseComponent));
    }

    public static @NotNull TextComponent ofLegacyTextRaw(@NotNull String rawString) {
        return LegacyComponentSerializer.legacySection().deserialize(rawString);
    }

    public static @NotNull TextComponent colorizeText(@NotNull String rawtext) {
        if(customLegacySerializer == null) {
            customLegacySerializer = getSerializer();
        }

        return customLegacySerializer.deserialize(rawtext);
    }

    @NotNull
    private static LegacyComponentSerializer getSerializer() {
        return LegacyComponentSerializer.builder()
                .hexColors()
                .useUnusualXRepeatedCharacterHexFormat()
                .character('&')
                .hexCharacter('#')
                .extractUrls(Style.style()
                        .decorate(getURLStyle())
                        .color(NamedTextColor.DARK_AQUA)
                        .build())
                .build();
    }

    public static @NotNull TextDecoration[] getURLStyle() {
        return new TextDecoration[]{TextDecoration.UNDERLINED};
    }

    public static @NotNull String sanitizeForSerializer(@NotNull String string) {
        if(customLegacySerializer == null) {
            customLegacySerializer = getSerializer();
        }

        TextComponent componentForm = ofLegacyTextRaw(string);
        return customLegacySerializer.serialize(componentForm);
    }
}
