package fun.slowfeew.multibrain;

import fun.slowfeew.multibrain.Events.BlockPlaceOrBreak;
import fun.slowfeew.multibrain.Game.ServerManager;
import fun.slowfeew.multibrain.Game.ServerStatus;
import fun.slowfeew.multibrain.Game.StatusManager;
import fun.slowfeew.multibrain.Game.TeamsManager;
import fun.slowfeew.multibrain.Utils.DatabaseManager;
import fun.slowfeew.multibrain.WorldManager.ResetBlockAfterRound;
import fun.slowfeew.multibrain.commands.CommandDebug;
import fun.slowfeew.multibrain.commands.CommandMultibrain;
import fun.slowfeew.multibrain.commands.CommandTest;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getScoreboardManager;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main extends JavaPlugin {


    private static Plugin plugin;


    public static Main getInstance;

    private FileConfiguration config;

    private ServerManager serverManager;

    public ServerManager getServerManager() {
        return serverManager;
    }

    private StatusManager statusManager;

    public StatusManager getStatusManager() {
        return statusManager;
    }

    private TeamsManager teamsManager;

    public TeamsManager getTeamsManager() {
        return teamsManager;
    }

    @Override
    public void onEnable() {
        serverManager.setStatus(ServerStatus.STARTING);

        // setup config
        getInstance = this;
        plugin = (Plugin)this;
        saveDefaultConfig();
        config = getConfig();
        ResetBlockAfterRound.blockLocation = new ArrayList<>();
        statusManager = new StatusManager();
        teamsManager = new TeamsManager();
        serverManager = new ServerManager();


        // register commands
        getCommand("multibrain").setExecutor((CommandExecutor)new CommandMultibrain(this));
        getCommand("test").setExecutor((CommandExecutor)new CommandTest(this));
        getCommand("debug").setExecutor((CommandExecutor)new CommandDebug(this));

        getServer().getPluginManager().registerEvents(new BlockPlaceOrBreak(this), this);

        // register events
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);

        getServer().getWorld("world").setGameRuleValue("doDaylightCycle", "false");
        getServer().getWorld("world").setGameRuleValue("doWeatherCycle", "false");
        getServer().getWorld("world").setGameRuleValue("announceAdvancements", "false");
        getServer().getWorld("world").setGameRuleValue("doEntityDrops", "false");
        getServer().getWorld("world").setGameRuleValue("doMobSpawning", "false");
        getServer().getWorld("world").setDifficulty(Difficulty.PEACEFUL);
        getServer().getWorld("world").setAutoSave(false);


        DatabaseManager.initAllDatabaseConnections();


        Bukkit.getScheduler().runTask(this, () -> {
            // Le serveur a terminé de charger
            serverManager.setStatus(ServerStatus.WAITING);
        });

    }


    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers())
            //getScoreboardManager().onLogout(player);
        //getScoreboardManager().onDisable();
        DatabaseManager.closeAllDatabaseConnections();



        super.onDisable();
    }
}