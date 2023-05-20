package fun.slowfeew.multibrain.Events;

import fun.slowfeew.multibrain.Game.EndingRound;
import fun.slowfeew.multibrain.Game.Enum.PlayerStatus;
import fun.slowfeew.multibrain.Game.Enum.ServerStatus;
import fun.slowfeew.multibrain.Game.Manager.TeamsManager;
import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.WorldManager.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BedInteractionEvent implements Listener {

    private final Main main;


    public BedInteractionEvent(Main main) {
        this.main = main;
    }

    static FileConfiguration config = Main.getInstance.getConfig();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if(!ServerStatus.isStatus(ServerStatus.IN_GAME)) return;

        Player player = event.getPlayer();
        UUID playerID = player.getUniqueId();
        String playerTeam = Objects.requireNonNull(TeamsManager.getTeam(playerID)).getTeamName();

        List<String> teaM = new ArrayList<>();
        teaM.add("Red");
        teaM.add("Blue");
        teaM.add("Green");
        teaM.add("Yellow");

        if(PlayerStatus.getStatus(playerID) != PlayerStatus.INGAME) return;

        // BED INTERACTION

            // Loop over all teams
        for (String team : teaM) {

                if(team.equals(playerTeam)) continue;

                Location bedLocation = Config.getLocation("Teams.Base." + team + ".Bed");

                // Check if the bed belongs to the team within a range of 10 blocks
                if (player.getLocation().distance(bedLocation) <= 1) {
                    ServerStatus.setStatus(ServerStatus.ROUNDUP);
                    EndingRound.endRound(TeamsManager.getTeam(playerID), TeamsManager.getTeamFromName(team));
                    continue;
                }
        }
    }
}