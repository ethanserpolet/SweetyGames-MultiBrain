package fun.slowfeew.multibrain.commands;

import fun.slowfeew.multibrain.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandMultibrain implements CommandExecutor {
    public CommandMultibrain(Main main) {
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        //getname
        if(command.getName().equalsIgnoreCase("multibrain")) {


        }

        return false;
    }
}
