package me.greazi.magicvalley.util.text;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;

import java.util.function.BiConsumer;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public enum MVplMessageType {
    ACTION_BAR(Audience::sendActionBar),
    SYSTEM((audience, message) -> audience.sendMessage(Identity.nil(), message, MessageType.SYSTEM));

    private final BiConsumer<Audience, Component> sender;

    MVplMessageType(final BiConsumer<Audience, Component> sender) {
        this.sender = sender;
    }

    public void send(final Audience audience, final Component message) {
      this.sender.accept(audience, message);
    }
}
