package me.greazi.MagicValley.util;

import me.greazi.MagicValley.Main;

public class Handler {
    protected Main plugin;

    public Handler(Main plugin) {
        this.plugin = plugin;
    }

    public void enable() {}

    public void disable() {}

    public Main getInstance() {
        return this.plugin;
    }
}