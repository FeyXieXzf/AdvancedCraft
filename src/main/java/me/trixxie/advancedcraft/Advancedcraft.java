package me.trixxie.advancedcraft;

import com.tchristofferson.configupdater.ConfigUpdater;
import me.trixxie.advancedcraft.commands.mainCommands;
import me.trixxie.advancedcraft.listeners.*;
import me.trixxie.advancedcraft.utils.UpdateChecker;
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

        //UPDATE CHECKER
        new UpdateChecker(this, 99235).getVersion(version -> {
            if (!this.getDescription().getVersion().equals(version)) {
                getLogger().info("There is a new update available.");
                getLogger().info("https://www.spigotmc.org/resources/99235/updates");
            }
        });

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

        //COMMANDS
        this.getCommand("advancedcraft").setExecutor(new mainCommands());
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
