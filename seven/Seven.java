package me._l4urens_.seven;

import me._l4urens_.seven.commends.boom;
import me._l4urens_.seven.commends.next;
import me._l4urens_.seven.commends.startboom;
import me._l4urens_.seven.commends.worldEditApiTest;
import me._l4urens_.seven.listners.onDeath;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Seven extends JavaPlugin {
    @Override
    public void onLoad() {
        saveResource("house.schem", false);
    }
    @Override
    public void onEnable() {

        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new onDeath(), this);
        System.out.println(ChatColor.GREEN+"The plugin had enabled");
        getCommand("boomStart").setExecutor(new startboom());
        getCommand("next").setExecutor(new next());
        getCommand("boom").setExecutor(new boom());
        getCommand("wapiTest").setExecutor(new worldEditApiTest(this));


    }

}
