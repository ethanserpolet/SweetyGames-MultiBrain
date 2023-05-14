package fun.slowfeew.multibrain;

import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketListener;
import fun.slowfeew.multibrain.Events.BlockPlaceOrBreak;
import fun.slowfeew.multibrain.Game.StatusManager;
import fun.slowfeew.multibrain.Game.TeamsManager;
import fun.slowfeew.multibrain.WorldManager.ResetBlockAfterRound;
import fun.slowfeew.multibrain.commands.CommandDebug;
import fun.slowfeew.multibrain.commands.CommandMultibrain;
import fun.slowfeew.multibrain.commands.CommandTest;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main extends JavaPlugin {


    private static Plugin plugin;


    public static Main getInstance;

    public static ProtocolManager protocolManager;
    public static PacketListener packetListener;

    private FileConfiguration config;

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

        // setup config
        getInstance = this;
        plugin = (Plugin)this;
        saveDefaultConfig();
        config = getConfig();
        ResetBlockAfterRound.blockLocation = new ArrayList<>();
        statusManager = new StatusManager();
        teamsManager = new TeamsManager();


        // register commands
        getCommand("multibrain").setExecutor((CommandExecutor)new CommandMultibrain(this));
        getCommand("test").setExecutor((CommandExecutor)new CommandTest(this));
        getCommand("debug").setExecutor((CommandExecutor)new CommandDebug(this));

        getServer().getPluginManager().registerEvents(new BlockPlaceOrBreak(this), this);

        // register events
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);



    }

}