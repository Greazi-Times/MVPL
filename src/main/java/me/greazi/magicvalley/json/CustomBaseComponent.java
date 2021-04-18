package me.greazi.magicvalley.json;

import net.md_5.bungee.api.chat.BaseComponent;

public class CustomBaseComponent extends BaseComponent {
    @Override
    public BaseComponent duplicate() {
        return this;
    }
}
