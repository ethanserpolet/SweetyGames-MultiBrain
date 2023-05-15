package fun.slowfeew.multibrain.Game;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public enum Teams {
    RED("§cRouge §c", "§cRouge §c", "§cRouge"),
    BLUE("§9Bleu §9", "Bleu", "§9Bleu"),
    YELLOW("§eJaune §e", "Jaune", "§eJaune"),
    GREEN("§2Vert §2", "Vert", "§2Vert");

    private String prefix;
    private String nom;
    private String color;
    private List<Player> players;

    Teams(String prefix, String nom, String color) {
        this.prefix = prefix;
        this.nom = nom;
        this.color = color;
        this.players = new ArrayList<>();
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