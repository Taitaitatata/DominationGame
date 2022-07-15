package com.github.taitaitatata.dominationgame.util;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public final class RespawnSession extends BukkitRunnable {

    private Player player;
    private long seconds;

    public RespawnSession(Player player, long seconds) {
        this.player = player;
        this.seconds = seconds;
    }

    @Override
    public void run() {
        if (seconds <= 0) {
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 0f);

            player.resetTitle();
            player.setGameMode(GameMode.ADVENTURE);
            cancel();
            return;
        }

        player.sendTitle("§c死んでしまった!", seconds + " §7秒でリスポーンします。", 0, 25, 0);
        seconds--;
    }

}
