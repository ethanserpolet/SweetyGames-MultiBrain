package fun.slowfeew.multibrain.Game;

import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.WorldManager.PlaceObsidian;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class EliminateTeam {


    public static void eliminateTeam(Teams team) {

        List<Player> players = team.getPlayers();
        for (Player player : players) {


        }

        PlaceObsidian.out(team.getNom());

        Bukkit.broadcastMessage("§dMultiBrain §8| §cL'équipe " + team.getColor() + team.getNom() + " §ca été éliminée !");

        for (Player player: Bukkit.getOnlinePlayers()) {

            if(Main.getInstance.getStatusManager().getStatus(player) == PlayerStatus.INGAME) {
                player.sendTitle("§cL'équipe " + team.getColor() + " §ca été éliminée !", "§7Prépare toi pour le prochain round !");
            }



        }
    }
}
