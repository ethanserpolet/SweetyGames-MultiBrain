package fun.slowfeew.multibrain.Game;

public enum Teams {
    RED("§cRouge §c", "§cRouge §c", "§cRouge"),
    BLUE("§9Bleu §9", "Bleu", "§9Bleu"),
    YELLOW("§eJaune §e", "Jaune", "§eJaune"),
    GREEN("§2Vert §2", "Vert", "§2Vert");

    private String prefix;
    private String nom;
    private String color;

    Teams(String prefix, String nom, String color) {
        this.prefix = prefix;
        this.nom = nom;
        this.color = color;
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
}