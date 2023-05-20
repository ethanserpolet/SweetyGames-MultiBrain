package fun.slowfeew.multibrain.WorldManager;

import fun.slowfeew.multibrain.Events.*;
import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.commands.CommandDebug;
import fun.slowfeew.multibrain.commands.CommandMultibrain;
import fun.slowfeew.multibrain.commands.CommandTest;
import org.bukkit.Difficulty;
import org.bukkit.command.CommandExecutor;

public class RegisterCommandAndEvents {
    
    
    public static void main() {

        Main.getInstance.getCommand("multibrain").setExecutor((CommandExecutor)new CommandMultibrain(Main.getInstance));
        Main.getInstance.getCommand("test").setExecutor((CommandExecutor)new CommandTest(Main.getInstance));
        Main.getInstance.getCommand("debug").setExecutor((CommandExecutor)new CommandDebug(Main.getInstance));

        Main.getInstance.getServer().getPluginManager().registerEvents(new BlockBreakOfPlaceEvent(Main.getInstance), Main.getInstance);
        Main.getInstance.getServer().getPluginManager().registerEvents(new BedInteractionEvent(Main.getInstance), Main.getInstance);
        Main.getInstance.getServer().getPluginManager().registerEvents(new InventoryClickEvent(Main.getInstance), Main.getInstance);
        Main.getInstance.getServer().getPluginManager().registerEvents(new World(Main.getInstance), Main.getInstance);
        Main.getInstance.getServer().getPluginManager().registerEvents(new PluginDisableEvent(Main.getInstance), Main.getInstance);
        Main.getInstance.getServer().getPluginManager().registerEvents(new DamageEvent(Main.getInstance), Main.getInstance);
        Main.getInstance.getServer().getPluginManager().registerEvents(new PlayerMoveEvent(Main.getInstance), Main.getInstance);
        Main.getInstance.getServer().getPluginManager().registerEvents(new PlayerJoinEvent(Main.getInstance), Main.getInstance);
        Main.getInstance.getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(Main.getInstance), Main.getInstance);
        Main.getInstance.getServer().getPluginManager().registerEvents(new PlayerInteractEvent(Main.getInstance), Main.getInstance);

        // register events
        Main.getInstance.getServer().getPluginManager().registerEvents(new PluginDisableEvent(Main.getInstance), Main.getInstance);

        Main.getInstance.getServer().getWorld("world").setGameRuleValue("doDaylightCycle", "false");
        Main.getInstance.getServer().getWorld("world").setGameRuleValue("doWeatherCycle", "false");
        Main.getInstance.getServer().getWorld("world").setGameRuleValue("doImmediateRespawn", "true");
        Main.getInstance.getServer().getWorld("world").setGameRuleValue("logAdminCommands", "false");
        Main.getInstance.getServer().getWorld("world").setGameRuleValue("sendCommandFeedback", "false");
        Main.getInstance.getServer().getWorld("world").setGameRuleValue("showDeathMessages", "false");
        Main.getInstance.getServer().getWorld("world").setGameRuleValue("announceAdvancements", "false");

        Main.getInstance.getServer().getWorld("world").setGameRuleValue("announceAdvancements", "false");
        Main.getInstance.getServer().getWorld("world").setGameRuleValue("doEntityDrops", "false");
        Main.getInstance.getServer().getWorld("world").setGameRuleValue("doMobSpawning", "false");
        Main.getInstance.getServer().getWorld("world").setDifficulty(Difficulty.HARD);
        Main.getInstance.getServer().getMessenger().registerOutgoingPluginChannel(Main.getInstance, "BungeeCord");
        Main.getInstance.getServer().getWorld("world").setAutoSave(false);



    }
}
