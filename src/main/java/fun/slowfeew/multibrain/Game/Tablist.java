package fun.slowfeew.multibrain.Game;

import fun.slowfeew.multibrain.Game.Manager.TeamsManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class Tablist {

    public static void updateTablist(Player player) {
        Scoreboard scoreboard = player.getScoreboard();

        TeamsManager group = TeamsManager.getTeam(player.getUniqueId());

        int order = group.getOrder();

        String newprefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(group.getPrefix() + " "));

        Team team = scoreboard.getTeam(String.valueOf(group));

        if (team == null) {
            team = scoreboard.registerNewTeam(String.valueOf(group));
            team.setPrefix(newprefix);
            team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.FOR_OTHER_TEAMS);
        }

        team.addEntry(player.getName());
        player.setPlayerListName(newprefix + player.getName());

        for (int i = 0; i <= order; i++) {
            String spaces = new String(new char[i]).replace("\0", "");
            player.setPlayerListName(spaces + newprefix + player.getName());
        }
    }

}
