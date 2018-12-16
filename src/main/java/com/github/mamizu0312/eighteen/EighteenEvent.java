package com.github.mamizu0312.eighteen;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class EighteenEvent implements Listener {
    EighteenBattleManager ebm = new EighteenBattleManager("inEighteenEvent");
    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e) {
        if(e.getPlayer() == null) {
            return;
        }
        if(Eighteen.ps.get(e.getPlayer().getUniqueId()).equalsIgnoreCase("menu")) {
            Player p = (Player) e.getPlayer();
            Eighteen.ps.remove(p.getUniqueId());
        }
        if(Eighteen.ps.get(e.getPlayer().getUniqueId()).equalsIgnoreCase("inGameCOM")) {
            Player p = (Player) e.getPlayer();
            Eighteen.ps.remove(p.getUniqueId());
            p.sendMessage("試合を放棄しました");
        }

    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        if(p == null || e.getClickedInventory() == null) {
            return;
        }
        if(Eighteen.ps.get(p.getUniqueId()).equalsIgnoreCase("menu")) {
            e.setCancelled(true);
            if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
                return;
            }
            if(e.getSlot() == 0) {
                p.sendMessage("現在準備中");
            }
            if(e.getSlot() == 1) {
                p.chat("/eighteen COM");
            }
            if(e.getSlot() == 2) {
                p.chat("/eighteen help");
            }
            p.closeInventory();
        }
        if(PlayerStatus.isStatusGameCOM(e.getWhoClicked().getUniqueId())) {
            e.setCancelled(true);
            if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
                return;
            }
            if(e.getSlot() == 3) {
                ebm.backround++;
            }
            if(e.getSlot() == 12) {
                ebm.p1Finger = ebm.p1Finger - 2;
                ebm.p1putoutFinger = 2;
                ebm.backround++;
            }
            if(e.getSlot() == 21) {
                ebm.p1Finger = ebm.p1Finger - 5;
                ebm.p1putoutFinger= 5;
                ebm.backround++;
            }
        }
    }

}
