package me.trixxie.advancedcraft;

import com.tchristofferson.configupdater.ConfigUpdater;
import me.trixxie.advancedcraft.commands.mainCommands;
import me.trixxie.advancedcraft.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public final class AdvancedCraft extends JavaPlugin {

    private static AdvancedCraft plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // Config updates
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        File configFile = new File(getDataFolder(), "config.yml");
        try {
            ConfigUpdater.update(plugin, "config.yml", configFile, Collections.emptyList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        reloadConfig();


        // Events
        this.getServer().getPluginManager().registerEvents(new advancedMining(), this);
        this.getServer().getPluginManager().registerEvents(new zombieInfect(), this);
        this.getServer().getPluginManager().registerEvents(new slimeScoop(), this);


        // Commands
        this.getCommand("advancedcraft").setExecutor(new mainCommands());
    }


    @Override
    public void onDisable() {
        plugin = null;
    }

    public static AdvancedCraft get(){
        return plugin;
    }
}
