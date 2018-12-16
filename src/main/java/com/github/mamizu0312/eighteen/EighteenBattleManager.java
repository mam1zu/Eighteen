package com.github.mamizu0312.eighteen;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


public class EighteenBattleManager {
    int p1Finger = 18;
    int COMFinger = 18;
    int p1putoutFinger;
    int COMputoutFinger;

    int p1Score;
    int COMScore;
    int p1LastScore;
    int COMLastScore;
    int round = 1;
    int backround;
    String event;
    public EighteenBattleManager(String event) {
        this.event = event;
    }
    public void onGameCOM(Player p) {
        PlayerStatus ps = new PlayerStatus(p.getUniqueId(), "inGameCOM");
        Inventory inv = Bukkit.createInventory(null, 27, p.getName()+" VS COM");
        ItemStack item = new ItemStack(Material.STONE, 1, (short)1);
        ItemMeta itemm = item.getItemMeta();
        itemm.setDisplayName("グー");
        List<String> lore = new ArrayList<>();
        lore.add("グーを選択します。使う指:0");
        itemm.setLore(lore);
        item.setItemMeta(itemm);
        inv.setItem(3, item);

        ItemStack item2 = new ItemStack(Material.SHEARS, 1, (short)1);
        ItemMeta itemm2 = item2.getItemMeta();
        itemm2.setDisplayName("チョキ");
        List<String> lore2 = new ArrayList<>();
        lore2.add("チョキを選択します。使う指:2");
        itemm2.setLore(lore2);
        item2.setItemMeta(itemm2);
        inv.setItem(12, item2);

        ItemStack item3 = new ItemStack(Material.PAPER, 1, (short)1);
        ItemMeta itemm3 = item3.getItemMeta();
        itemm3.setDisplayName("パー");
        List<String> lore3 = new ArrayList<>();
        lore3.add("パーを選択します。使う指:5");
        itemm3.setLore(lore3);
        item2.setItemMeta(itemm3);
        inv.setItem(21, item3);

        ItemStack p1Skull = new ItemStack(Material.SKULL, 1);
        SkullMeta p1SkullMeta = (SkullMeta) p1Skull.getItemMeta();
        p1SkullMeta.setDisplayName(p.getName());
        OfflinePlayer poffline = Bukkit.getOfflinePlayer(p.getUniqueId());
        p1SkullMeta.setOwningPlayer(poffline);
        List<String> p1SkullLore = new ArrayList<>();
        p1SkullLore.add("Score: " + p1Score);
        p1SkullMeta.setLore(p1SkullLore);
        p1Skull.setItemMeta(p1SkullMeta);
        inv.setItem(0, p1Skull);

        ItemStack COMSkull = new ItemStack(Material.SKULL_ITEM, 1);
        SkullMeta COMSkullMeta = (SkullMeta) COMSkull.getItemMeta();
        COMSkullMeta.setDisplayName("COM");
        List<String> COMSkullLore = new ArrayList<>();
        COMSkullLore.add("Score: "+COMScore);
        COMSkullMeta.setLore(COMSkullLore);
        COMSkull.setItemMeta(COMSkullMeta);
        inv.setItem(8, COMSkull);

        ItemStack item4 = new ItemStack(Material.WATCH, 1, (short)1);
        ItemMeta itemm4 = item4.getItemMeta();
        itemm4.setDisplayName(round + "回戦");
        item4.setItemMeta(itemm4);
        inv.setItem(26, item4);
        p.openInventory(inv);
        Random r = new Random();
        if (COMFinger < 2) {
            backround++;
        }
        if (COMFinger < 5) {
            int putout = r.nextInt(2);
            if (putout == 0) {
                backround++;
            }
            if (putout == 2) {
                COMputoutFinger = 2;
                backround++;
            }
        }
        int putout = r.nextInt(3);
        if (putout == 0) {
            backround++;
        }
        if (putout == 1) {
            COMFinger = 2;
            backround++;
        }
        if (putout == 2) {
            COMFinger = 5;
            backround++;
        }
        if(backround / 2 == 0) {
            if(backround == 24) {
                p1LastScore = p1Score - p1Finger;
                COMLastScore = COMScore - COMFinger;
                if(p1LastScore > COMLastScore) {
                    ps.ps.remove(p.getUniqueId());
                    p.sendMessage("おめでとうございます！あなたは You:"+ p1LastScore +":"+COMLastScore+":COM でCOMに勝利しました！" );
                    p.closeInventory();
                } else {
                    ps.ps.remove(p.getUniqueId());
                    p.sendMessage("あなたは You:"+p1LastScore + ":"+COMLastScore + ":COM でCOMに敗北しました...");
                    p.closeInventory();
                }
            }
            switch (p1putoutFinger) {
                case 0:
                    switch(COMputoutFinger) {
                        case 0:
                            p1putoutFinger = 0;
                            COMputoutFinger = 0;
                            round++;
                            break;
                        case 2:
                            if(isRound6or10()) {
                                p1Score += 2;
                            } else {
                                p1Score++;
                            }
                            p1putoutFinger = 0;
                            COMputoutFinger = 0;
                            round++;
                            break;
                        case 5:
                            if(isRound6or10()) {
                                COMScore += 2;
                            } else {
                                COMScore++;
                            }
                            p1putoutFinger = 0;
                            COMputoutFinger = 0;
                            round++;
                            break;
                    }
                case 2:
                    if(!(p1Finger >= 2)) {
                        p.sendMessage("指の数が足りません！");
                        return;
                    }
                    switch(COMputoutFinger) {
                        case 0:
                            if(isRound6or10()) {
                                COMScore+=2;
                            } else {
                                COMScore++;
                            }
                            p1putoutFinger = 0;
                            COMputoutFinger = 0;
                            round++;
                            break;
                        case 2:
                            p1putoutFinger = 0;
                            COMputoutFinger = 0;
                            round++;
                            break;
                        case 5:
                            if(isRound6or10()) {
                                p1Score+=2;
                            } else {
                                p1Score++;
                            }
                            p1putoutFinger = 0;
                            COMputoutFinger = 0;
                            round++;
                            break;
                    }
                case 5:
                    if(!(p1Finger >= 5)) {
                        p.sendMessage("指の数が足りません！");
                        return;
                    }
                    switch(COMputoutFinger) {
                        case 0:
                            if(isRound6or10()) {
                                p1Score+=2;
                            } else {
                                p1Score++;
                            }
                            p1putoutFinger = 0;
                            COMputoutFinger = 0;
                            round++;
                            break;
                        case 2:
                            if(isRound6or10()) {
                                COMScore+=2;
                            } else {
                                COMScore++;
                            }
                            p1putoutFinger = 0;
                            COMputoutFinger = 0;
                            round++;
                            break;
                        case 5:
                            p1putoutFinger = 0;
                            COMputoutFinger = 0;
                            round++;
                            break;
                    }
            }
        }
    }
    public boolean isRound6or10() {
        if(round == 6 || round == 10) {
            return true;
        }
        return false;
    }
}
