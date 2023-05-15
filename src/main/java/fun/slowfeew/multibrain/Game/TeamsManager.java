package fun.slowfeew.multibrain.Game;

import org.bukkit.entity.Player;
import java.util.HashMap;

public class TeamsManager {

    private HashMap<Player, Teams> playerTeams;

    public TeamsManager() {
        playerTeams = new HashMap<>();
    }

    public Teams getTeams(Player player) {
        return playerTeams.get(player);
    }

    public static Teams getTeamByName(String name) {
        for (Teams team : Teams.values()) {
            if (team.getNom().equals(name)) {
                return team;
            }
        }
        return null; // Si aucune équipe ne correspond au nom spécifié
    }

    public void setTeams(Player player, Teams Teams) {
        playerTeams.put(player, Teams);
    }
}
