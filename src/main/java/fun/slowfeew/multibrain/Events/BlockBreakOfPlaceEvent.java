package fun.slowfeew.multibrain.Events;

import fun.slowfeew.multibrain.Game.Enum.PlayerStatus;
import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.WorldManager.ResetBrokenBlocks;
import fun.slowfeew.multibrain.WorldManager.ResetPlacedBlocks;
import fun.slowfeew.multibrain.commands.CommandDebug;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockBreakOfPlaceEvent implements Listener {


    private final Main main;
    public BlockBreakOfPlaceEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        Player p = e.getPlayer();


        if(PlayerStatus.getStatus(p.getUniqueId()) != PlayerStatus.INGAME) {
            e.setCancelled(true);
            return;
        }

        if(!e.getBlockPlaced().getType().equals(Material.SANDSTONE)) {
            e.setCancelled(true);
            return;
        }

        if(e.getBlockPlaced().getLocation().getBlockY() >= 70 || e.getBlockPlaced().getLocation().getBlockY() <= 59) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§6[MultiBrain] §cVous ne pouvez pas placer de blocs à cette hauteur !");
            return;
        }

        if(CommandDebug.debug.containsKey(p)) {
                Location placed = e.getBlockPlaced().getLocation();
                p.sendMessage("§6[MultiBrain] §cVous avez placé un bloc ! §8(§7" + placed.toString() + "§8)");
        }

        ResetPlacedBlocks.addBlockLocation(e.getBlockPlaced().getLocation());

    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();

        if(PlayerStatus.getStatus(p.getUniqueId()) != PlayerStatus.INGAME) {
            e.setCancelled(true);
            return;
        }

        BlockBreakEvent blockEvent = (BlockBreakEvent) e;
        Block block = blockEvent.getBlock();

        if(block != null && !block.getType().equals(Material.SANDSTONE)) {
            e.setCancelled(true);
        }


        assert e.getBlock() != null;
        ResetBrokenBlocks.addBlockLocation(e.getBlock().getLocation());

    }
}
