package fun.slowfeew.multibrain.Events;

import fun.slowfeew.multibrain.Game.EndingRound;
import fun.slowfeew.multibrain.Game.Teams;
import fun.slowfeew.multibrain.Game.TeamsManager;
import fun.slowfeew.multibrain.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class BedInteraction {

    static FileConfiguration config = Main.getInstance.getConfig();
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();


        // BED INTERACTION
        // Vérifie si le joueur est à moins d'un bloc d'un lit
        if (player.getLocation().getBlock().getRelative(0, 1, 0).getType() == Material.BED_BLOCK) {

            // Récupère la configuration du plugin
            // Boucle sur toutes les équipes
            for (String team : config.getConfigurationSection("Team.Base").getKeys(false)) {
                // Récupère la position du lit pour cette équipe
                double x = config.getDouble("Team.Base." + team + ".Bed.X");
                double y = config.getDouble("Team.Base." + team + ".Bed.Y");
                double z = config.getDouble("Team.Base." + team + ".Bed.Z");
                Location bedLocation = new Location(player.getWorld(), x, y, z);

                Teams loser = TeamsManager.getTeamByName(team);

                // Vérifie si le lit appartient à l'équipe dans une zone de 3 blocs
                if (player.getLocation().distance(bedLocation) <= 3) {
                    assert loser != null;
                    EndingRound.endRound(loser, Main.getInstance.getTeamsManager().getTeams(player));
                    break; // Arrête la boucle car nous avons trouvé l'équipe
                }
            }
        }
    }
}