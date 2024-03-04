package fun.slowfeew.multibrain.commands;

import com.sun.jndi.toolkit.dir.ContainmentFilter;
import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.WorldManager.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMultibrain implements CommandExecutor {
    private Main main;
    public CommandMultibrain(Main main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


        //getname
        if(command.getName().equalsIgnoreCase("multibrain")) {

            if(!(commandSender instanceof Player)) return false;

            Player p = (Player) commandSender;



            if(strings.length == 0) {
                commandSender.sendMessage("§cUsage: /multibrain <setspawn|setbed>");
                CommandDebug.debugSend("1 = no string", ", no string");
                return true;
            }

            if(strings[0].equalsIgnoreCase("setspawn")) {

                    CommandDebug.debugSend("T2 = strings[1]", strings[0]);


                            CommandDebug.debugSend("T3 = strings[1]", strings[0]);
                            if(strings.length == 2) {


                                CommandDebug.debugSend("T4 = strings[1]", strings[1]);
                                if(strings[1].equals("Red") || strings[1].equals("Blue") || strings[1].equals("Green") || strings[1].equals("Yellow")) {


                                    CommandDebug.debugSend("T5 = strings[1]", strings[1]);
                                    Config.setLocation(p.getLocation(), "Teams.Base." + strings[1] + ".Spawn");

                                    commandSender.sendMessage("§6[MultiBrain] §aLe spawn de l'équipe " + strings[1] + " a été défini !");


                                    return true;
                                }

                            }

                            commandSender.sendMessage("§cUsage: /multibrain setspawn <red|blue|green|yellow>");
                            return true;
            }



            if(strings[0].equalsIgnoreCase("setbed")) {

                CommandDebug.debugSend("T2 = strings[1]", strings[0]);


                CommandDebug.debugSend("T3 = strings[1]", strings[0]);
                if(strings.length == 2) {


                    CommandDebug.debugSend("T4 = strings[1]", strings[1]);
                    if(strings[1].equals("Red") || strings[1].equals("Blue") || strings[1].equals("Green") || strings[1].equals("Yellow")) {


                        CommandDebug.debugSend("T5 = strings[1]", strings[1]);
                        Config.setLocation(p.getLocation(), "Teams.Base." + strings[1] + ".Bed");

                        commandSender.sendMessage("§6[MultiBrain] §aLe lit de l'équipe " + strings[1] + " a été défini !");


                        return true;
                    }

                }

                commandSender.sendMessage("§cUsage: /multibrain setspawn <red|blue|green|yellow>");
                return true;
            }
        }

        return false;
    }
}
