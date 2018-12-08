package com.github.mamizu0312.eighteen;

import org.bukkit.plugin.java.JavaPlugin;

public final class Eighteen extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Enabled.");
        getCommand("eighteen").setExecutor(new EighteenCommandManager());
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled.");
    }

}