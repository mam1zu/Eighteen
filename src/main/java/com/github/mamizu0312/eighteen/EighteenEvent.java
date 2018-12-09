package com.github.mamizu0312.eighteen;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import com.github.mamizu0312.eighteen.PlayerStatus;
import org.bukkit.event.player.PlayerQuitEvent;

public class EighteenEvent implements Listener {
    PlayerStatus ps = new PlayerStatus();
    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e) {
        if(ps.isStatusGameCOM(e.getPlayer().getUniqueId())) {
            Player p = (Player) e.getPlayer();
            new PlayerStatus().removePs(p.getUniqueId());
            p.sendMessage("試合を放棄しました");
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(ps.isStatusMenu(e.getWhoClicked().getUniqueId())) {
            e.setCancelled(true);
            if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
                return;
            }
            Player p = (Player) e.getWhoClicked();
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
        if(ps.isStatusGameCOM(e.getWhoClicked().getUniqueId())) {
            e.setCancelled(true);
            if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
                return;
            }
            Player p = (Player) e.getWhoClicked();
            if(e.getSlot() == 3) {
                new EighteenBattleManager().backround++;
            }
            if(e.getSlot() == 12) {
                EighteenBattleManager ebm = new EighteenBattleManager();
                ebm.p1Finger = ebm.p1Finger - 2;
                ebm.p1putoutFinger = 2;
                ebm.backround++;
            }
            if(e.getSlot() == 21) {
                EighteenBattleManager ebm = new EighteenBattleManager();
                ebm.p1Finger = ebm.p1Finger - 5;
                ebm.p1putoutFinger= 5;
                ebm.backround++;
            }

        }
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {

    }

}
