package fun.slowfeew.multibrain.commands;

import fun.slowfeew.multibrain.Main;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandDebug implements CommandExecutor {

    public static HashMap<Player, Boolean> debug = new HashMap<>();

    private Main main;
    public CommandDebug(Main main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;

            if (command.getName().equalsIgnoreCase("debug")) {
                if (debug.containsKey(p)) {
                    debug.remove((Player) p);
                    p.sendMessage("§dMultiBrain §8| §cLe mode debug est désactivé !");
                    debug.put((Player) p, true);
                    p.sendMessage("§dMultiBrain §8| §cLe mode debug est activé !");

                } else {
                    debug.put((Player) p, true);
                    p.sendMessage("§dMultiBrain §8| §cLe mode debug est activé !");
                }
            }
        }

        return false;
    }


    public static void debugSend(String message1, String message2) {
        String callerMethodName = new Exception().getStackTrace()[1].getMethodName();
        String message = "Méthode appelante: " + callerMethodName + ", Message1: " + message1 + ", Message2: " + message2;

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (CommandDebug.debug.containsKey(player)) {
                String coucou = "§dDEBUG §6| §7" + message;
                player.sendMessage(coucou);
            }
        }
    }
}
