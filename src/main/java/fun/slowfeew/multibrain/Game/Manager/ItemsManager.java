package fun.slowfeew.multibrain.Game.Manager;

import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.Utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class ItemsManager {
    public static void giveItem(Player player) {
        Main plugin = Main.getInstance;

        /**
        // Donner une épée en fer tranchant 1 nommée "§bEpee Légendaire"
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
        sword.addEnchantment(Enchantment.KNOCKBACK, 1);
        sword.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setDisplayName(ChatColor.BLUE + "Katana Légendaire");
        sword.setItemMeta(swordMeta);
        player.getInventory().addItem(sword);

        // Donner 16 pommes dorées
        ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE, 16);
        player.getInventory().addItem(goldenApple);

        // Donner une pioche en fer efficacité 1
        ItemStack pickaxe = new ItemStack(Material.IRON_PICKAXE);
        pickaxe.addEnchantment(Enchantment.DIG_SPEED, 2);
        player.getInventory().addItem(pickaxe);

        // Remplir le reste de l'inventaire de smooth sandstone
        ItemStack sandstone = new ItemStack(Material.SANDSTONE);
        sandstone.setAmount(33*64);
            player.getInventory().addItem(sandstone);

        ItemStack sandstone2 = new ItemStack(Material.SANDSTONE);
        sandstone.setAmount(-1);

        player.getInventory().setItemInOffHand(sandstone2);

        // Donner un effet de vitesse pendant 4 secondes
         **/

        if(TeamsManager.getTeam(player.getUniqueId()).equals(TeamsManager.RED)) {

            Color blueColor = Color.RED;

            ItemStack helmet = (new ItemBuilder(Material.LEATHER_HELMET))
                    .setDisplayName("§bCasque Légendaire")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            ItemStack chestplate = (new ItemBuilder(Material.LEATHER_CHESTPLATE))
                    .setDisplayName("§bPlastron Légendaire")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            ItemStack leggings = (new ItemBuilder(Material.LEATHER_LEGGINGS))
                    .setDisplayName("§bJambières Légendaires")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            ItemStack boots = (new ItemBuilder(Material.LEATHER_BOOTS))
                    .setDisplayName("§bBottes Légendaires")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            player.getInventory().setHelmet(helmet);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(leggings);
            player.getInventory().setBoots(boots);

        }

        if(TeamsManager.getTeam(player.getUniqueId()).equals(TeamsManager.BLUE)) {

            Color blueColor = Color.BLUE;

            ItemStack helmet = (new ItemBuilder(Material.LEATHER_HELMET))
                    .setDisplayName("§bCasque Légendaire")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            ItemStack chestplate = (new ItemBuilder(Material.LEATHER_CHESTPLATE))
                    .setDisplayName("§bPlastron Légendaire")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            ItemStack leggings = (new ItemBuilder(Material.LEATHER_LEGGINGS))
                    .setDisplayName("§bJambières Légendaires")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            ItemStack boots = (new ItemBuilder(Material.LEATHER_BOOTS))
                    .setDisplayName("§bBottes Légendaires")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            player.getInventory().setHelmet(helmet);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(leggings);
            player.getInventory().setBoots(boots);

        }

        if(TeamsManager.getTeam(player.getUniqueId()).equals(TeamsManager.YELLOW)) {

            Color blueColor = Color.YELLOW;

            ItemStack helmet = (new ItemBuilder(Material.LEATHER_HELMET))
                    .setDisplayName("§bCasque Légendaire")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            ItemStack chestplate = (new ItemBuilder(Material.LEATHER_CHESTPLATE))
                    .setDisplayName("§bPlastron Légendaire")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            ItemStack leggings = (new ItemBuilder(Material.LEATHER_LEGGINGS))
                    .setDisplayName("§bJambières Légendaires")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            ItemStack boots = (new ItemBuilder(Material.LEATHER_BOOTS))
                    .setDisplayName("§bBottes Légendaires")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            player.getInventory().setHelmet(helmet);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(leggings);
            player.getInventory().setBoots(boots);

        }

        if(TeamsManager.getTeam(player.getUniqueId()).equals(TeamsManager.GREEN)) {

            Color blueColor = Color.GREEN;

            ItemStack helmet = (new ItemBuilder(Material.LEATHER_HELMET))
                    .setDisplayName("§bCasque Légendaire")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            ItemStack chestplate = (new ItemBuilder(Material.LEATHER_CHESTPLATE))
                    .setDisplayName("§bPlastron Légendaire")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            ItemStack leggings = (new ItemBuilder(Material.LEATHER_LEGGINGS))
                    .setDisplayName("§bJambières Légendaires")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            ItemStack boots = (new ItemBuilder(Material.LEATHER_BOOTS))
                    .setDisplayName("§bBottes Légendaires")
                    .setLeatherArmorColor(blueColor)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .build();

            player.getInventory().setHelmet(helmet);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(leggings);
            player.getInventory().setBoots(boots);

        }





        ItemStack sandstone = new ItemStack(Material.SANDSTONE, 1);
        ItemMeta uzu = sandstone.getItemMeta();
        uzu.setDisplayName("§bSeigneur des bâtisseurs");
        sandstone.setItemMeta(uzu);
        sandstone.setAmount(64 * -1); // Set the amount as the positive value of the negative amount

        player.getInventory().setItemInOffHand(sandstone);

        player.getInventory().setItem(0, (new ItemBuilder(Material.IRON_SWORD, 1)).setInfinityDurability().setDisplayName("§bKatana Légendaire").addUnsafeEnchantment(Enchantment.DURABILITY, 10).addEnchant(Enchantment.DAMAGE_ALL, 2).addEnchant(Enchantment.KNOCKBACK, 1).flag(ItemFlag.HIDE_UNBREAKABLE).flag(ItemFlag.HIDE_ATTRIBUTES).build());
        player.getInventory().setItem(1, (new ItemBuilder(Material.IRON_PICKAXE, 1)).setInfinityDurability().setDisplayName("§bPioche des mineurs d'Or").addUnsafeEnchantment(Enchantment.DURABILITY, 10).addEnchant(Enchantment.DIG_SPEED, 3).flag(ItemFlag.HIDE_UNBREAKABLE).flag(ItemFlag.HIDE_ATTRIBUTES).build());
        player.getInventory().setItem(2, (new ItemBuilder(Material.GOLDEN_APPLE, 64)).setInfinityDurability().setDisplayName("§bFruit Interdit").flag(ItemFlag.HIDE_UNBREAKABLE).flag(ItemFlag.HIDE_ATTRIBUTES).build());
        for (int i = 3; i < 36 ; i++) {
            player.getInventory().setItem(i, (new ItemBuilder(Material.SANDSTONE, 64)).setInfinityDurability().setDisplayName("§bSeigneur des bâtisseur").flag(ItemFlag.HIDE_UNBREAKABLE).flag(ItemFlag.HIDE_ATTRIBUTES).build());
        }
    }
}

