package com.github.taitaitatata.dominationgame.file;

import com.github.taitaitatata.dominationgame.game.Flag;
import com.github.taitaitatata.dominationgame.game.Kit;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.List;

public final class Arena {

    private final String id;
    private final String worldId;
    private String displayName;

    private PlayerLimits player;
    private int respawnAt;

    List<Kit> kit;
    List<Flag> flag;

    public Arena(String id, String worldId, String displayName, int min, int max, int respawnAt, List<Kit> kit, List<Flag> flag) {
        this.id = id;
        this.worldId = worldId;
        this.displayName = displayName;
        this.player = new PlayerLimits(min, max);
        this.respawnAt = respawnAt;
        this.kit = kit;
        this.flag = flag;
    }

    public static final class PlayerLimits {
        private int min;
        private int max;

        public PlayerLimits(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public int getMin() {
            return this.min;
        }

        public int getMax() {
            return this.max;
        }
    }

    public String getId() {
        return id;
    }

    public List<Flag> getFlags() {
        return flag;
    }

    public List<Kit> getKits() {
        return kit;
    }

    public PlayerLimits getLimits() {
        return player;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getRespawnAt() {
        return respawnAt;
    }

    public void addKit(Kit kit) {
        this.kit.add(kit);
    }

    public void removeKit(Kit kit) {
        this.kit.remove(kit);
    }

    public void removeKit(int i) {
        this.kit.remove(i);
    }

    public void addFlag(Flag flag) {
        this.flag.add(flag);
    }

    public void removeFlag(Flag flag) {
        this.flag.remove(flag);
    }

    public void removeFlag(int i) {
        this.flag.remove(i);
    }

    public void clearKit() {
        this.kit.clear();
    }

    public void clearFlag() {
        this.flag.clear();
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setLimits(PlayerLimits limit) {
        this.player = limit;
    }

    public void setFlags(List<Flag> flags) {
        this.flag = flags;
    }

    public void setKit(List<Kit> kits) {
        this.kit = kits;
    }

    public void setRespawnAt(int respawnAt) {
        this.respawnAt = respawnAt;
    }

    public World getWorld() {
        return Bukkit.getWorld(this.worldId);
    }

    public String getWorldId() {
        return this.worldId;
    }

    public boolean hasDisplayName() {
        return id.equals(displayName);
    }
}
