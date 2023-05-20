package fun.slowfeew.multibrain;

import fun.slowfeew.multibrain.Game.Enum.ServerStatus;
import fun.slowfeew.multibrain.Game.Manager.ServerManager;
import fun.slowfeew.multibrain.SQL.config.DatabaseManager;
import fun.slowfeew.multibrain.WorldManager.RegisterCommandAndEvents;
import fun.slowfeew.multibrain.WorldManager.ResetPlacedBlocks;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main extends JavaPlugin {


    public static CharSequence maxPlayer =  "4";
    private static Plugin plugin;


    public static Main getInstance;

    private FileConfiguration config;

    private ServerManager serverManager;

    public ServerManager getServerManager() {
        return serverManager;
    }



    int count = 0;
    int secondsLeft = 10;

    @Override
    public void onEnable() {

        // setup config
        getInstance = this;
        plugin = (Plugin)this;

        saveDefaultConfig();
        config = getConfig();

        ResetPlacedBlocks.blockLocation = new ArrayList<>();

        serverManager = new ServerManager();


        ServerStatus.setStatus(ServerStatus.WAITING);


        super.onEnable();


        DatabaseManager.initAllDatabaseConnections();


        Bukkit.getScheduler().runTask(this, RegisterCommandAndEvents::main);

    }

    @Override
    public void onDisable() {
        DatabaseManager.closeAllDatabaseConnections();
        super.onDisable();
    }
}