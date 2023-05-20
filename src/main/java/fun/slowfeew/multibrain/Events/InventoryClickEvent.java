package fun.slowfeew.multibrain.Events;

import fun.slowfeew.multibrain.Game.LobbyTeamSelector;
import fun.slowfeew.multibrain.Game.Manager.TeamsManager;
import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.commands.CommandDebug;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClickEvent implements Listener {

    private final Main main;

    public InventoryClickEvent(Main main) {
        this.main = main;
    }

    // Événement appelé lorsqu'un joueur clique dans un inventaire
    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();

        if (inventory != null && inventory.getTitle().equals("§eChoisir une équipe §8(§7Clic-droit§8)")) {
            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null && clickedItem.getType() == Material.STAINED_CLAY) {
                ItemMeta itemMeta = clickedItem.getItemMeta();
                if (itemMeta != null && itemMeta.hasDisplayName()) {
                    String displayName = itemMeta.getDisplayName();

                    if (displayName.equals("§7Rejoindre l'équipe §cRouge")) {
                        if (TeamsManager.getSize(TeamsManager.RED) > 0) {
                            player.sendMessage("§dMultiBrain §8| §cCette équipe est déjà pleine !");
                            return;
                        }

                        ItemStack redClay = LobbyTeamSelector.createColoredClay(DyeColor.RED, "§7Changer d'Equipe §8(§7Clic-droit§8)");
                        player.getInventory().setItem(0, redClay);
                        player.sendMessage("§dMultiBrain §8| §7Vous avez rejoint l'équipe §crouge §7!");
                        TeamsManager.RED.setTeam(player.getUniqueId());

                        TeamsManager.BLUE.removePlayer(player.getUniqueId());
                        TeamsManager.GREEN.removePlayer(player.getUniqueId());
                        TeamsManager.YELLOW.removePlayer(player.getUniqueId());

                        TeamsManager.RED.setTeam(player.getUniqueId());
                    } else if (displayName.equals("§7Rejoindre l'équipe §eJaune")) {
                        if (TeamsManager.YELLOW.getTeamSize() > 0) {
                            player.sendMessage("§dMultiBrain §8| §cCette équipe est déjà pleine !");
                            return;
                        }
                        ItemStack yellowClay = LobbyTeamSelector.createColoredClay(DyeColor.YELLOW, "§7Changer d'Equipe §8(§7Clic-droit§8)");
                        player.getInventory().setItem(0, yellowClay);
                        player.sendMessage("§dMultiBrain §8| §7Vous avez rejoint l'équipe §ejaune §7!");
                        TeamsManager.YELLOW.setTeam(player.getUniqueId());

                        TeamsManager.BLUE.removePlayer(player.getUniqueId());
                        TeamsManager.GREEN.removePlayer(player.getUniqueId());
                        TeamsManager.RED.removePlayer(player.getUniqueId());

                        TeamsManager.YELLOW.setTeam(player.getUniqueId());
                    } else if (displayName.equals("§7Rejoindre l'équipe §2Verte")) {
                        CommandDebug.debugSend(String.valueOf(TeamsManager.GREEN.getTeamSize()), " SIZE GREEN");

                        if (TeamsManager.GREEN.getTeamSize() > 0) {
                            player.sendMessage("§dMultiBrain §8| §cCette équipe est déjà pleine !");
                            return;
                        }
                        ItemStack greenClay = LobbyTeamSelector.createColoredClay(DyeColor.GREEN, "§7Changer d'Equipe §8(§7Clic-droit§8)");
                        player.getInventory().setItem(0, greenClay);
                        player.sendMessage("§dMultiBrain §8| §7Vous avez rejoint l'équipe §2verte §7!");
                        TeamsManager.GREEN.setTeam(player.getUniqueId());

                        TeamsManager.BLUE.removePlayer(player.getUniqueId());
                        TeamsManager.RED.removePlayer(player.getUniqueId());
                        TeamsManager.YELLOW.removePlayer(player.getUniqueId());

                        TeamsManager.GREEN.setTeam(player.getUniqueId());
                    } else if (displayName.equals("§7Rejoindre l'équipe §9Bleue")) {
                        CommandDebug.debugSend(String.valueOf(TeamsManager.BLUE.getTeamSize()), " SIZE BLUE");

                        if (TeamsManager.BLUE.getTeamSize() > 0) {
                            player.sendMessage("§dMultiBrain §8| §cCette équipe est déjà pleine !");
                            return;
                        }

                        ItemStack blueClay = LobbyTeamSelector.createColoredClay(DyeColor.BLUE, "§7Changer d'Equipe §8(§7Clic-droit§8)");
                        player.getInventory().setItem(0, blueClay);
                        player.sendMessage("§dMultiBrain §8| §7Vous avez rejoint l'équipe §9bleue §7!");

                        TeamsManager.RED.removePlayer(player.getUniqueId());
                        TeamsManager.GREEN.removePlayer(player.getUniqueId());
                        TeamsManager.YELLOW.removePlayer(player.getUniqueId());

                        TeamsManager.BLUE.setTeam(player.getUniqueId());
                    }

                    // Remplacez l'élément dans l'inventaire du joueur par la couleur choisie
                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        if (p.getOpenInventory().getTitle().equals("§eChoisir une équipe §8(§7Clic-droit§8)")) {
                            LobbyTeamSelector.openTeamSelectionMenu(p);
                        }
                    }
                }
            }
        }
    }
}
