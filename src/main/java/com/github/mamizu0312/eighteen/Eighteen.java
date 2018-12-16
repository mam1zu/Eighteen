package com.github.mamizu0312.eighteen;

import org.bukkit.plugin.java.JavaPlugin;

public final class Eighteen extends JavaPlugin{
    EighteenEvent event = new EighteenEvent();
    @Override
    public void onEnable() {
        getLogger().info("Enabled.");
        getCommand("eighteen").setExecutor(new EighteenCommandManager());
        getServer().getPluginManager().registerEvents(event, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled.");
    }

}