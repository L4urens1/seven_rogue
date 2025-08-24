package me._l4urens_.seven;

import me._l4urens_.seven.commends.*;
import me._l4urens_.seven.listners.onDeath;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import me._l4urens_.seven.manegers.schemticmanger;


public final class Seven extends JavaPlugin {
    @Override
    public void onLoad() {
        saveResource("house.schem", false);
        saveResource("pathx1.schem", false);
        saveResource("pathx2.schem", false);
        saveResource("pathz1.schem", false);
        saveResource("pathz2.schem", false);
    }
    @Override
    public void onEnable() {
        final schemticmanger manager = new schemticmanger(this);

        // Plugin startup logic

//        getServer().getPluginManager().registerEvents(new onDeath(), this);
        
        System.out.println(ChatColor.GREEN+"The plugin had enabled");
        getCommand("boomStart").setExecutor(new startboom());
        getCommand("next").setExecutor(new next());
        getCommand("boom").setExecutor(new boom());
        getCommand("apiTest").setExecutor(new worldEditApiTest(manager));
        getCommand("pathTest").setExecutor(new pathtest(manager));


    }

}
