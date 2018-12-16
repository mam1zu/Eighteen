package com.github.mamizu0312.eighteen;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Eighteen extends JavaPlugin{
    EighteenEvent event = new EighteenEvent();
    String prefix = "§7[§aeighteen§7]§r";
    public static HashMap<UUID, String> ps = new HashMap<UUID, String>();
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