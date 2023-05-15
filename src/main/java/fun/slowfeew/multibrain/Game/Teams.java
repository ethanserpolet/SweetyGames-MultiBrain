package fun.slowfeew.multibrain.Game;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public enum Teams {
    RED("§cRouge §c", "Red", "§cRouge"),
    BLUE("§9Bleu §9", "Blue", "§9Bleu"),
    YELLOW("§eJaune §e", "Yellow", "§eJaune"),
    GREEN("§2Vert §2", "Green", "§2Vert");

    private String prefix;
    private String nom;
    private String color;
    private List<Player> players;

    private int points;

    Teams(String prefix, String nom, String color) {
        this.prefix = prefix;
        this.nom = nom;
        this.color = color;
        this.players = new ArrayList<>();
        this.points = 0; // initialize points to 0
    }


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    public String getPrefix() {
        return prefix;
    }

    public String getNom() {
        return nom;
    }

    public String getColor() {
        return color;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }
}