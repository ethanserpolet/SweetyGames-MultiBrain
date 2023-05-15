package fun.slowfeew.multibrain.commands;

import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.WorldManager.PlaceObsidian;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandTest implements CommandExecutor {

    private Main main;
    public CommandTest(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(command.getName().equalsIgnoreCase("test")) {

            if (strings.length == 0) {
                commandSender.sendMessage("§cUsage: /test <message>");
                return true;
            }

            if(strings[0].equalsIgnoreCase("outRed")) {
                commandSender.sendMessage("§cTEST: " + strings[0]);
                PlaceObsidian.out("Red");
                return true;
            }


        }
        return false;
    }
}
