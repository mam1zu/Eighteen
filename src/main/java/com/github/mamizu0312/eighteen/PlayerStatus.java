package com.github.mamizu0312.eighteen;

import java.util.HashMap;
import java.util.UUID;

public class PlayerStatus {
    public HashMap<UUID, String> ps = new HashMap<>();

    public void putPs(UUID uuid, String status) {
        this.ps.put(uuid, status);
    }
    public void removePs(UUID uuid) {
        this.ps.remove(uuid);
    }

    public boolean isStatusMenu(UUID uuid) {
        if(ps.containsKey(uuid)) {
            if(ps.get(uuid) == "menu") {
                return true;
            }
        }
        return false;
    }
    public boolean isStatusGameCOM(UUID uuid) {
        if(ps.containsKey(uuid)) {
            if(ps.get(uuid) == "inGameCOM") {
                return true;
            }
        }
        return false;
    }

}
