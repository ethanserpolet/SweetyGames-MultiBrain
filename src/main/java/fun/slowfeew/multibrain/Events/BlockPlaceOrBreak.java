package fun.slowfeew.multibrain.Events;

import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.commands.CommandDebug;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceOrBreak implements Listener {


    private final Main main;
    public BlockPlaceOrBreak(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        Player p = e.getPlayer();

        if(CommandDebug.debug.containsKey(p)) {
                Location placed = e.getBlockPlaced().getLocation();
                p.sendMessage("§dMultiBrain §8| §cVous avez placé un bloc ! §8(§7" + placed.toString() + "§8)");
        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();

        if(CommandDebug.debug.containsKey(p)) {
            Location placed = e.getBlock().getLocation();
            p.sendMessage("§dMultiBrain §8| §cVous avez cassé un bloc ! §8(§7" + placed.toString() + "§8)");
        }

    }
}
