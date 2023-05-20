package fun.slowfeew.multibrain.Events;

import fun.slowfeew.multibrain.Game.Enum.PlayerStatus;
import fun.slowfeew.multibrain.Game.Manager.TeamsManager;
import fun.slowfeew.multibrain.Game.StartRound;
import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.WorldManager.SetSpawns;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerMoveEvent implements Listener {

    private final Main main;

    public PlayerMoveEvent(Main main) {
        this.main = main;
    }


    public static List<Player> players = new ArrayList<>();

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        // Check if you want to prevent jumping for this specific player
        if (StartRound.Duration.get(event.getPlayer()) <= 0) {
            event.setCancelled(true);
            player.setAllowFlight(false);
            player.setFlying(false);
        }
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        // Check if you want to prevent sneaking for this specific player
        if (players.contains(player)) {
            event.setCancelled(true);
            player.setSneaking(false);
        }
    }



    double minYCoordinateInSpawn = Main.getInstance.getConfig().getDouble("MinYCords.InSpawn");
    double minYCoordinateInGame = Main.getInstance.getConfig().getDouble("MinYCords.InGame");

    @EventHandler
    public void onMove(org.bukkit.event.player.PlayerMoveEvent e) {


        for (Player player : Bukkit.getOnlinePlayers()) {

            if(PlayerStatus.getStatus(player.getUniqueId()) == PlayerStatus.INSPAWN) {
                // Vérifier si la coordonnée Y du joueur est inférieure à minYCoordinateInSpawn
                if (player.getLocation().getY() <= minYCoordinateInSpawn) {
                    // Exécuter la méthode goToSpawn avec le joueur
                    SetSpawns.goToSpawn(player);
                }
            }

            if(PlayerStatus.getStatus(player.getUniqueId()) == PlayerStatus.INGAME) {
                // Vérifier si la coordonnée Y du joueur est inférieure à minYCoordinateInGame
                if (player.getLocation().getY() <= minYCoordinateInGame) {
                    // Exécuter la méthode goToSpawn avec le joueur

                    player.setHealth(0);

                }
            }

        }

    }
}
