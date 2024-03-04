package fun.slowfeew.multibrain.Events;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fun.slowfeew.multibrain.Game.LobbyTeamSelector;
import fun.slowfeew.multibrain.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class PlayerInteractEvent implements Listener {


    private final Main main;
    public PlayerInteractEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(org.bukkit.event.player.PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();



        if (mainHandItem != null) {
            ItemMeta itemMeta = mainHandItem.getItemMeta();
            if(itemMeta != null) {

                if (mainHandItem.getType() == Material.BLAZE_ROD) {
                    Block clickedBlock = event.getClickedBlock(); // Obtient le bloc cliqué.

                    // Vérifie si le bloc n'est pas null (pour éviter les clics dans l'air).
                    if (clickedBlock != null) {
                        // Obtient la position du bloc.
                        int x = clickedBlock.getX();
                        int y = clickedBlock.getY();
                        int z = clickedBlock.getZ();

                        // Envoie la position du bloc au joueur.
                        player.sendMessage(ChatColor.GREEN + "Position du bloc: " + ChatColor.GOLD + "X: " + x + " Y: " + y + " Z: " + z);
                    }
                }

                if (itemMeta.hasDisplayName()) {
                    if (itemMeta.getDisplayName().equals("§eChoisir une équipe §8(§7Clic-droit§8)") || itemMeta.getDisplayName().equals("§7Changer d'Equipe §8(§7Clic-droit§8)")) {
                        LobbyTeamSelector.openTeamSelectionMenu(player);
                        return;
                    }


                    // GO HUB
                    if (itemMeta.getDisplayName().equals("§cQuitter §8(§7Clic-droit§8)")) {
                        ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
                        byteArrayDataOutput.writeUTF("Connect");
                        byteArrayDataOutput.writeUTF("lobby_01");
                        player.sendPluginMessage(Main.getInstance, "BungeeCord", byteArrayDataOutput.toByteArray());
                        return;
                    }

                }
            }
        }
    }
}
