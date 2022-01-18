package me.trixxie.advancedcraft;

import com.tchristofferson.configupdater.ConfigUpdater;
import me.trixxie.advancedcraft.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public final class Advancedcraft extends JavaPlugin {

    private static Advancedcraft plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        //CONFIG SHIT UPDATER
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        File configFile = new File(getDataFolder(), "config.yml");
        try {
            ConfigUpdater.update(plugin, "config.yml", configFile, Arrays.asList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        reloadConfig();
        //ENDS SHIT UPDATER (not really shitty, really well working thingy)

        //METRICS
        int pluginId = 13983;
        Metrics metrics = new Metrics(this, pluginId);

        //EVENTS & LISTENERS
        this.getServer().getPluginManager().registerEvents(new advancedMining(), this);
        this.getServer().getPluginManager().registerEvents(new easySmelt(), this);
        this.getServer().getPluginManager().registerEvents(new zombieInfect(), this);
        this.getServer().getPluginManager().registerEvents(new enchantmentStorage(), this);
        this.getServer().getPluginManager().registerEvents(new slimeScoop(), this);

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        plugin = null;
    }

    public static Advancedcraft get(){
        return plugin;
    }
}
