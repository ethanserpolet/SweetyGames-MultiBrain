package fun.slowfeew.multibrain;

import fun.slowfeew.multibrain.commands.CommandMultibrain;
import org.bukkit.plugin.java.JavaPlugin;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        // setup config
        saveDefaultConfig();

        // register commands
        getCommand("multibrain").setExecutor(new CommandMultibrain(this));

        // register events
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);



    }

}