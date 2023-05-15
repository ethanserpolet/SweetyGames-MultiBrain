package fun.slowfeew.multibrain.Game;

import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.WorldManager.ResetBlockAfterRound;
import fun.slowfeew.multibrain.commands.CommandDebug;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class EndingRound {

    public static void winnerTeam(Teams t) {


    }
    public static void loserTeam(Teams t) {

    }

    public static void endRound(Teams winner, Teams loser) {

        // Increment the points of the winner team and decrement the points of the loser team

        CommandDebug.debugSend(// debug
                "winner: " + winner.getNom() + " " + winner.getPoints() + " points" + "\n" +
                        "loser: " + loser.getNom() + " " + loser.getPoints() + " points", " info");





        ResetBlockAfterRound.ResetMap();
        TeamsManager.getTeamByName(loser.getNom()).setPoints(winner.getPoints() + 1); // increment the points of team t
        loser.setPoints(loser.getPoints() - 1); // decrement the points of team t

        // Check if any team has reached 0 points
        for (Teams team : Teams.values()) {
            if (team.getPoints() == 0) {
                System.out.println("Team " + team.getNom() + " has reached 0 points!");
                EliminateTeam.eliminateTeam(team);
                announceWinnerEliminated(winner, loser);
                return;
            }
        }

        announceWinner(winner, loser);
                //ComboFFA.setup(player);
    }

    private static void announceWinnerEliminated(Teams winner, Teams loser) {
        String winnercolor = winner.getColor() + winner.getColor();
        List<Player> winerplayer = winner.getPlayers();

        String losercolor = winner.getColor() + winner.getColor();
        List<Player> loserplayer = winner.getPlayers();


        for(Player p : loserplayer) {
            p.sendTitle("§cVous avez été éliminé", "§7Vous êtes désormais spectateur !");

        }

        for(Player p : winerplayer) {
            p.sendTitle("§7Vous avez éliminé l'équipe" + losercolor + loser.getNom(), "§7Continuez comme ça !");

        }


        List<Player> otherTeamsPlayers = new ArrayList<>();

        for (Teams team : Teams.values()) {
            if (team != winner && team != loser) {
                otherTeamsPlayers.addAll(team.getPlayers());
            }
        }

        // Maintenant, otherTeamsPlayers contient tous les joueurs des deux autres équipes.
        // Vous pouvez les parcourir avec une boucle for :

        for (Player p2 : otherTeamsPlayers) {
            p2.sendTitle("§cL'équipe " + loser.getColor() + loser.getNom() + " §ca perdu 1 vie !", "§7Prépare toi pour le prochain round !");
        }
    }

    private static void announceWinner(Teams winner, Teams loser) {

        String winnercolor = winner.getColor() + winner.getColor();
        List<Player> winerplayer = winner.getPlayers();

        String losercolor = winner.getColor() + winner.getColor();
        List<Player> loserplayer = winner.getPlayers();


        for(Player p : loserplayer) {
            p.sendTitle("§cVous avez perdu 1 vie", "§7Il faut faire attention !");

        }

        for(Player p : winerplayer) {
            p.sendTitle("Vous avez retiré 1 points à l'équipe" + losercolor + loser.getNom(), "§7Continuez comme ça !");

        }


        List<Player> otherTeamsPlayers = new ArrayList<>();

        for (Teams team : Teams.values()) {
            if (team != winner && team != loser) {
                otherTeamsPlayers.addAll(team.getPlayers());
            }
        }

        // Maintenant, otherTeamsPlayers contient tous les joueurs des deux autres équipes.
        // Vous pouvez les parcourir avec une boucle for :

        for (Player p2 : otherTeamsPlayers) {
            p2.sendTitle("§cL'équipe " + loser.getColor() + loser.getNom() + " §ca perdu 1 vie !", "§7Prépare toi pour le prochain round !");
        }
    }
}
