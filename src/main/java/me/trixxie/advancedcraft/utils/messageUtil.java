package me.trixxie.advancedcraft.utils;

import me.trixxie.advancedcraft.Advancedcraft;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class messageUtil {

    private Advancedcraft plugin = Advancedcraft.get();

    public static void messageWithPrefix(CommandSender sender, String msg){
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAC&r &8» &6" + msg));
    }

    public static void sendError(CommandSender sender, Errors err){
        messageWithPrefix(sender, err.getMessage());
    }

    public static enum Errors {
        ARGS("&cYou are missing some arguments."),
        NO_PERMS("&cYou do not have sufficient permissions.");

        private final String message;

        private Errors(String message) {
            this.message = message;
        }

        public final String getMessage(){
            return this.message;
        }
    }
}
