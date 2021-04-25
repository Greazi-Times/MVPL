package me.greazi.magicvalley.json;

import net.md_5.bungee.api.chat.BaseComponent;

/*
 *
 * Copyright © Greazi 2020 | All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by Greazi
 *
 */

public class CustomBaseComponent extends BaseComponent {
    @Override
    public BaseComponent duplicate() {
        return this;
    }
}
