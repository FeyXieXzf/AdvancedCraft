package me.trixxie.advancedcraft.commands;

import me.trixxie.advancedcraft.Advancedcraft;
import me.trixxie.advancedcraft.utils.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class mainCommands implements CommandExecutor {

    private Advancedcraft plugin = Advancedcraft.get();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length > 0){
            if(args[0].equalsIgnoreCase("update")){
                if(sender instanceof Player) {
                    Player p = (Player) sender;
                    if(p.hasPermission("advancedcraft.admin")) {
                        new UpdateChecker(plugin, 99235).getVersion(version -> {
                            if (!plugin.getDescription().getVersion().equals(version)) {
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAC&r &8» &aThere is a new update available."));
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&n&ohttps://www.spigotmc.org/resources/99235/updates"));
                            } else {
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAC&r &8» &aNo update found!"));
                            }
                        });
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAC&r &8» &cYou don't have permission to use this command."));
                    }
                } else {
                    new UpdateChecker(plugin, 99235).getVersion(version -> {
                        if (!plugin.getDescription().getVersion().equals(version)) {
                            getLogger().warning("There is a new update available for AdvancedCraft.");
                            getLogger().warning("https://www.spigotmc.org/resources/99235/updates");
                        } else {
                            getLogger().info("No update found for AdvancedCraft!");
                        }
                    });
                }
            }
            if(args[0].equalsIgnoreCase("reload")){
                if(sender instanceof Player){
                    Player p = (Player) sender;
                    if(p.hasPermission("advancedcraft.admin")){
                        plugin.reloadConfig();
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAC&r &8» &aConfig reloaded!"));
                    }
                }
            }
        } else {
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(p.hasPermission("advancedcraft.admin")){
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAdvancedcraft help"));
                    p.sendMessage(ChatColor.GRAY + "/ac reload - Reloads plugin's config");
                    p.sendMessage(ChatColor.GRAY + "/ac update - Checks for updates");
                }
            }
        }
        return true;
    }
}
