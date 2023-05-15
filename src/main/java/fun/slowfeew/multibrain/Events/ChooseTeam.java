package fun.slowfeew.multibrain.Events;

import fun.slowfeew.multibrain.Game.Teams;
import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.commands.CommandDebug;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import static org.bukkit.Color.*;

public class ChooseTeam implements Listener {

    public void openTeamSelectionMenu(Player player) {
        Inventory teamSelectionMenu = Bukkit.createInventory(null, 9, "Choisir une équipe");

        // Créez les éléments du menu
        ItemStack redClay = createColoredClay(RED, "§cRouge");
        ItemStack yellowClay = createColoredClay(YELLOW, "§eJaune");
        ItemStack greenClay = createColoredClay(GREEN, "§2Verte");
        ItemStack blueClay = createColoredClay(Color.BLUE, "§9Bleue");
        ItemStack exit = createExit("§cFermer");


        // Ajoutez les éléments au menu
        teamSelectionMenu.setItem(0, redClay);
        teamSelectionMenu.setItem(1, yellowClay);
        teamSelectionMenu.setItem(2, greenClay);
        teamSelectionMenu.setItem(3, blueClay);
        teamSelectionMenu.setItem(9, exit);

        // Ouvrez le menu pour le joueur
        player.openInventory(teamSelectionMenu);
    }


    private ItemStack createExit(String displayName) {
        ItemStack clay = new ItemStack(Material.WOODEN_DOOR, 1);
        LeatherArmorMeta clayMeta = (LeatherArmorMeta) clay.getItemMeta();
        clayMeta.setDisplayName(displayName);
        clay.setItemMeta(clayMeta);
        return clay;
    }
    // Méthode utilitaire pour créer une stained clay colorée
    private ItemStack createColoredClay(Color color, String displayName) {
        ItemStack clay = new ItemStack(Material.STAINED_CLAY, 1);
        LeatherArmorMeta clayMeta = (LeatherArmorMeta) clay.getItemMeta();
        clayMeta.setColor(color);
        clayMeta.setDisplayName(displayName);
        clay.setItemMeta(clayMeta);
        return clay;
    }

    // Événement appelé lorsqu'un joueur clique dans un inventaire
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();

        if (inventory != null && inventory.getTitle().equals("§7Choisir une équipe §8(§7Clic-droit§8)")) {
            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null && clickedItem.getType() == Material.STAINED_CLAY) {
                LeatherArmorMeta clayMeta = (LeatherArmorMeta) clickedItem.getItemMeta();
                Color clayColor = clayMeta.getColor();
                CommandDebug.debugSend("clayColor: " + clayColor.toString(), " ");
                switch (clayColor.toString()) {
                    case "RED":
                        ItemStack redClay = createColoredClay(Color.RED, "§7Changer d'Equipe §8(§7Clic-droit§8)");
                        player.getInventory().setItem(0, redClay);
                        player.sendMessage("§dMultiBrain §8| §7Vous avez rejoint l'équipe §crouge §7!");
                        Main.getInstance.getTeamsManager().setTeams(player, Teams.RED);
                        break;
                    case "YELLOW":
                        ItemStack yellowClay = createColoredClay(YELLOW, "§7Changer d'Equipe §8(§7Clic-droit§8)");
                        player.getInventory().setItem(0, yellowClay);
                        player.sendMessage("§dMultiBrain §8| §7Vous avez rejoint l'équipe §ejaune §7!");
                        Main.getInstance.getTeamsManager().setTeams(player, Teams.YELLOW);
                        break;
                    case "GREEN":
                        ItemStack greenClay = createColoredClay(GREEN, "§7Changer d'Equipe §8(§7Clic-droit§8)");
                        player.getInventory().setItem(0, greenClay);
                        player.sendMessage("§dMultiBrain §8| §7Vous avez rejoint l'équipe §2verte §7!");
                        Main.getInstance.getTeamsManager().setTeams(player, Teams.GREEN);
                        break;
                    case "BLUE":
                        ItemStack blueClay = createColoredClay(BLUE, "§7Changer d'Equipe §8(§7Clic-droit§8)");
                        player.getInventory().setItem(0, blueClay);
                        player.sendMessage("§dMultiBrain §8| §7Vous avez rejoint l'équipe §9bleue §7!");
                        Main.getInstance.getTeamsManager().setTeams(player, Teams.BLUE);
                        break;
                }

                // Remplacez l'élément dans l'inventaire du joueur par la couleur choisie
                player.getInventory().setItem(0, clickedItem.clone());
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();

        if (mainHandItem != null && mainHandItem.getType() == Material.STAINED_CLAY && mainHandItem.hasItemMeta()) {
            ItemMeta itemMeta = mainHandItem.getItemMeta();
            if (itemMeta.hasDisplayName()) {
                if(itemMeta.getDisplayName().equals("§aJouer §8(§7Clic-droit§8)") || itemMeta.getDisplayName().equals("§7Changer d'Equipe §8(§7Clic-droit§8)")) {
                    openTeamSelectionMenu(player);
                }
            }
        }
    }
}
