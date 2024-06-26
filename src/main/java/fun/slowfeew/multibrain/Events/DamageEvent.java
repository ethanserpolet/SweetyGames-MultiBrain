package fun.slowfeew.multibrain.Events;

import fun.slowfeew.multibrain.Game.Enum.PlayerStatus;
import fun.slowfeew.multibrain.Game.Enum.ServerStatus;
import fun.slowfeew.multibrain.Game.Manager.ItemsManager;
import fun.slowfeew.multibrain.Game.Manager.PlayerManager;
import fun.slowfeew.multibrain.Game.Manager.TeamsManager;
import fun.slowfeew.multibrain.Main;
import net.minecraft.server.v1_9_R2.PacketPlayOutGameStateChange;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class DamageEvent implements Listener {

    private final Main main;
    public DamageEvent(Main main) {
        this.main = main;
    }


    @EventHandler
    public void onDamage(EntityDamageByBlockEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {

        if (ServerStatus.getStatus() == ServerStatus.STOPPING) {
            e.setDamage(0);
            e.setCancelled(true);
        }


        if (ServerStatus.getStatus() == ServerStatus.WAITING) {
            e.setDamage(0);
            e.setCancelled(true);
        }

        if (ServerStatus.getStatus() == ServerStatus.STARTING) {
            e.setDamage(0);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        // Check if the teleport cause is not another plugin or command
        if (event.getCause() != PlayerTeleportEvent.TeleportCause.PLUGIN && event.getCause() != PlayerTeleportEvent.TeleportCause.COMMAND) {
            // Cancel the damage if the player teleported
            event.getPlayer().setFallDistance(0);
        }
    }
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {

            Player p = (Player) e.getEntity();

            if (ServerStatus.getStatus() == ServerStatus.STOPPING) {
                e.setDamage(0);
                e.setCancelled(true);
            }


            if (ServerStatus.getStatus() == ServerStatus.WAITING) {
                e.setDamage(0);
                e.setCancelled(true);
            }

            if (ServerStatus.getStatus() == ServerStatus.STARTING) {
                e.setDamage(0);
                e.setCancelled(true);
            }

        }
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null); // Disable death messages if desired
        Player p = event.getEntity();
        event.getDrops().clear();

        new BukkitRunnable(){

            public void run() {

                p.spigot().respawn();
                ItemsManager.giveItem(p);
            }
        }.runTaskLater(Main.getInstance, 1L);

    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        ItemsManager.giveItem(p);
        Location loc = TeamsManager.getSpawn(Objects.requireNonNull(TeamsManager.getTeam(p.getUniqueId())));

        p.teleport(loc);

    }
}
