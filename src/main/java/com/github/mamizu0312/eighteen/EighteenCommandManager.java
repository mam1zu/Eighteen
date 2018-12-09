package com.github.mamizu0312.eighteen;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class EighteenCommandManager implements CommandExecutor {
    String prefix = "§7[§aeighteen§7]§r";
    PlayerStatus ps;
    EighteenBattleManager ebm;
    String menu = "menu";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player)sender;
        if(args.length == 0) {
            Inventory inv = Bukkit.createInventory(null, 9, prefix);
            ItemStack item = new ItemStack(Material.DIAMOND_HOE, 1, (short)0);
            ItemMeta itemm = item.getItemMeta();
            itemm.setDisplayName("プレイ");
            itemm.setUnbreakable(true);
            itemm.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            List<String> lore = new ArrayList<>();
            lore.add("現在準備中");
            itemm.setLore(lore);
            item.setItemMeta(itemm);
            inv.setItem(0, item);
            ItemStack item2 = new ItemStack(Material.DIAMOND_AXE, 1, (short)0);
            ItemMeta itemm2 = item2.getItemMeta();
            itemm2.setDisplayName("COMとプレイを開始します。難易度設定不可");
            List<String> lore2 = new ArrayList<>();
            lore2.add("現在準備中...");
            inv.setItem(1, item2);
            ItemStack item3 = new ItemStack(Material.DIAMOND_SWORD, 1, (short)0);
            ItemMeta itemm3 = item.getItemMeta();
            itemm3.setDisplayName("ヘルプ");
            item.setItemMeta(itemm3);
            inv.setItem(2, item3);
            ps.ps.put(p.getUniqueId(), menu);
            p.openInventory(inv);
            return true;
        }
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("help")) {
                HelpCommand(p);
                return true;
            }
            if(args[0].equalsIgnoreCase("COM")) {
                ebm.onGameCOM(p);
                ps.ps.remove(p.getUniqueId());
                ps.ps.put(p.getUniqueId(), "inGameCOM");
                return true;
            }
            //プレイヤーとの対戦
        }
        return true;
    }
    public static void HelpCommand(Player p) {
        p.sendMessage("§e---§a18(Eighteen)§e---");
        p.sendMessage("/eighteen MCID:指定した人にeighteenを招待します");
        p.sendMessage("/");
    }
    public String callPrefix() {
        return prefix;
    }
}