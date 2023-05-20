package fun.slowfeew.multibrain.Game;

import fun.slowfeew.multibrain.Game.Enum.PlayerStatus;
import fun.slowfeew.multibrain.Game.Enum.ServerStatus;
import fun.slowfeew.multibrain.Game.Manager.ItemsManager;
import fun.slowfeew.multibrain.Game.Manager.TeamsManager;
import fun.slowfeew.multibrain.WorldManager.Config;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class StartGame {
        public static void running(List<Player> players) {

            ServerStatus.setStatus(ServerStatus.IN_GAME);


            ServerStatus.setStatus(ServerStatus.ROUNDUP);
            for (Player p : players) {
                if (TeamsManager.getTeam(p.getUniqueId()) != null) {
                    String teamName = String.valueOf(Objects.requireNonNull(TeamsManager.getTeam(p.getUniqueId())).getTeamName());
                    if (teamName != null) {

                        PlayerStatus.setStatus(PlayerStatus.INGAME, p.getUniqueId());
                        StartRound.round = 0;
                        StartRound.start(p);
                        ServerStatus.setStatus(ServerStatus.IN_GAME);
                        TeamsManager.points.put(TeamsManager.getTeam(p.getUniqueId()), 5);



                    } else {
                        System.out.println("Le nom de l'équipe est nul pour le joueur : " + p.getName());
                    }
                } else {
                    System.out.println("L'équipe n'a pas été attribuée au joueur : " + p.getName());
                }
            }
        }
    }


