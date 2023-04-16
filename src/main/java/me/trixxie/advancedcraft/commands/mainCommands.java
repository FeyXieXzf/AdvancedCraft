package me.trixxie.advancedcraft.commands;

import me.trixxie.advancedcraft.AdvancedCraft;
import me.trixxie.advancedcraft.utils.messageUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class mainCommands implements CommandExecutor {

    private final AdvancedCraft plugin = AdvancedCraft.get();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length > 0){
            if(args[0].equalsIgnoreCase("reload")){
                if(sender instanceof Player){
                    Player p = (Player) sender;
                    if(p.hasPermission("advancedcraft.admin")){
                        plugin.reloadConfig();

                        messageUtil.messageWithPrefix(p, "Config reloaded!");
                    }
                }
            }
        } else {
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(p.hasPermission("advancedcraft.admin")){
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAdvancedCraft help"));
                    p.sendMessage(ChatColor.GRAY + "/ac reload - Reloads plugin config");
                }
            }
        }
        return true;
    }
}
