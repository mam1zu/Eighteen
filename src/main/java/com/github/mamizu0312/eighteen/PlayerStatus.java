package com.github.mamizu0312.eighteen;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerStatus {
    UUID uuid;
    String status;

    public PlayerStatus(UUID uuid, String status) {
        this.uuid = uuid;
        this.status = status;
    }
    public PlayerStatus(String status) {
        this.status = status;
    }
    public static boolean isStatusGameCOM(UUID uuid) {
        if(Eighteen.ps.containsKey(uuid)) {
            if(Eighteen.ps.get(uuid) == "inGameCOM") {
                return true;
            }
        }
        return false;
    }

}
