package fun.slowfeew.multibrain.Game;

import fun.slowfeew.multibrain.Game.Manager.TeamsManager;
import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.Utils.ItemBuilder;
import fun.slowfeew.multibrain.Utils.LuckPermsAPI;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

public class LobbyTeamSelector {

    public static String lore;

    public static void openTeamSelectionMenu(Player player) {
        Inventory teamSelectionMenu = Bukkit.createInventory(null, 9, "§eChoisir une équipe §8(§7Clic-droit§8)");
        // Créez les éléments du menu


        ItemStack redClay = createColoredClay(DyeColor.RED, "§7Rejoindre l'équipe §cRouge");
        ItemStack yellowClay = createColoredClay(DyeColor.YELLOW, "§7Rejoindre l'équipe §eJaune");
        ItemStack greenClay = createColoredClay(DyeColor.GREEN, "§7Rejoindre l'équipe §2Verte");
        ItemStack blueClay = createColoredClay(DyeColor.BLUE, "§7Rejoindre l'équipe §9Bleue");

        ItemStack goldenApple = new ItemStack(Material.WOODEN_DOOR, 1);
        ItemMeta meta = goldenApple.getItemMeta();
        meta.setDisplayName("§cFermer");
        goldenApple.setItemMeta(meta);


        // Ajoutez les éléments au menu
        teamSelectionMenu.setItem(0, redClay);
        teamSelectionMenu.setItem(1, yellowClay);
        teamSelectionMenu.setItem(2, greenClay);
        teamSelectionMenu.setItem(3, blueClay);
        teamSelectionMenu.setItem(8, goldenApple);

        // Ouvrez le menu pour le joueur
        player.openInventory(teamSelectionMenu);

    }
    public static ItemStack createColoredClay(DyeColor color, String displayName) {
        TeamsManager currentTeam = null;
        String lore = "§f- ";

        switch (color) {
            case RED:
                currentTeam = TeamsManager.RED;
                break;
            case YELLOW:
                currentTeam = TeamsManager.YELLOW;
                break;
            case GREEN:
                currentTeam = TeamsManager.GREEN;
                break;
            case BLUE:
                currentTeam = TeamsManager.BLUE;
                break;
        }

        if (currentTeam != null) {
            List<UUID> players = TeamsManager.getPlayers(currentTeam);

            if (players.size() == 1) {
                UUID player = players.get(0);  // get first player instead of the second
                lore = "§f- " + LuckPermsAPI.getPrefix(Bukkit.getPlayer(players.toString())) + Bukkit.getPlayer(players.toString()).getName();
            }
        }

        return new ItemBuilder(Material.STAINED_CLAY)
                .setDurability(color.getWoolData())
                .lore(lore)
                .setDisplayName(displayName)
                .build();
    }
}
