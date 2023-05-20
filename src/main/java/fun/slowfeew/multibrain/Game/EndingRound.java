package fun.slowfeew.multibrain.Game;

import fun.slowfeew.multibrain.Game.Enum.PlayerStatus;
import fun.slowfeew.multibrain.Game.Manager.TeamsManager;
import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.WorldManager.ResetBrokenBlocks;
import fun.slowfeew.multibrain.WorldManager.ResetPlacedBlocks;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EndingRound {

    public static void endRound(TeamsManager winner, TeamsManager loser) {
        // Votre implémentation pour la fin du round

        TeamsManager.decreasePoints(loser); // Incrémentez les points de l'équipe perdante

        // Vérifiez si une équipe a atteint 0 points
        if (TeamsManager.points.get(loser) == 0) {
            System.out.println("L'équipe " + loser + " a atteint 0 point(s) !");
            EliminateTeam.eliminateTeam(loser);
            announceWinnerEliminated(winner, loser);
            ResetPlacedBlocks.ResetMap();
            ResetBrokenBlocks.ResetMap();
            StartRound.round++;

            for (Player p : Bukkit.getOnlinePlayers()) {

                if(TeamsManager.getTeam(p.getUniqueId()) != null) {
                    p.setGameMode(GameMode.SPECTATOR);
                }
            }
            Main.getInstance.getServer().getScheduler().runTaskLater(Main.getInstance, new Runnable() {

                @Override
                public void run() {
                    for (Player p :Bukkit.getOnlinePlayers()) {

                        if (PlayerStatus.getStatus(p.getUniqueId()) == PlayerStatus.INGAME) {
                            StartRound.start(p);
                        }
                    }
                }
            }, 1L);
            return;
        }

        announceWinner(winner, loser);
        ResetPlacedBlocks.ResetMap();
        ResetBrokenBlocks.ResetMap();
        StartRound.round++;


        Main.getInstance.getServer().getScheduler().runTaskLater(Main.getInstance, new Runnable() {
            @Override
            public void run() {
                for (Player p :Bukkit.getOnlinePlayers()) {

                    if (PlayerStatus.getStatus(p.getUniqueId()) == PlayerStatus.INGAME) {
                        StartRound.start(p);
                    }
                }
            }
        }, 1L);
        //ComboFFA.setup(player);
    }

    private static void announceWinnerEliminated(TeamsManager winner, TeamsManager loser) {
        String winnerColor = winner.getPrefix();
        List<UUID> winnerPlayers = winner.getPlayers();

        String loserColor = loser.getPrefix();
        List<UUID> loserPlayers = loser.getPlayers();

        for (UUID playerUUID : loserPlayers) {
            Player p = Bukkit.getPlayer(playerUUID);
            p.sendTitle("§cVous êtes éliminé", "§7Vous êtes désormais spectateur !");
        }

        for (UUID playerUUID : winnerPlayers) {
            Player p = Bukkit.getPlayer(playerUUID);
            p.sendTitle("", "§7Vous avez éliminé l'équipe " + loserColor);
        }

        List<UUID> otherTeamsPlayers = new ArrayList<>();

        for (TeamsManager team : TeamsManager.values()) {
            if (team != winner && team != loser) {
                otherTeamsPlayers.addAll(TeamsManager.getPlayers(team));

            }
        }

        for (UUID playerUUID : otherTeamsPlayers) {
            Player p2 = Bukkit.getPlayer(playerUUID);
            p2.sendTitle("", "§cL'équipe " + loserColor + " §ca été éliminé par " + winnerColor);
        }
    }


    private static void announceWinner(TeamsManager winner, TeamsManager loser) {
        String winnerColor = winner.getPrefix();
        List<UUID> winnerPlayers = winner.getPlayers();

        String loserColor = loser.getPrefix();
        List<UUID> loserPlayers = loser.getPlayers();

        for (UUID playerUUID : loserPlayers) {
            Player p = Bukkit.getPlayer(playerUUID);
            p.sendTitle("-", "§cVous avez perdu 1 vie");
        }

        for (UUID playerUUID : winnerPlayers) {
            Player p = Bukkit.getPlayer(playerUUID);
            p.sendTitle("", "§7Vous avez prélevé une vie à l'équipe " + loserColor);
        }

        List<UUID> otherTeamsPlayers = new ArrayList<>();

        for (TeamsManager team : TeamsManager.values()) {
            if (team != winner && team != loser) {
                otherTeamsPlayers.addAll(TeamsManager.getPlayers(team));

            }
        }

        for (UUID playerUUID : otherTeamsPlayers) {
            Player p2 = Bukkit.getPlayer(playerUUID);
            p2.sendTitle("", "§7Les " + loserColor + " §7ont perdu une vie !");
        }

    }
}
