package me.trixxie.advancedcraft.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class messageUtil {
    public static void messageWithPrefix(CommandSender sender, String msg){
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAC&r &8» &6" + msg));
    }
}
