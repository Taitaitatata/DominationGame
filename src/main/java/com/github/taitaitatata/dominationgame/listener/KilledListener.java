package com.github.taitaitatata.dominationgame.listener;

import com.github.taitaitatata.dominationgame.DominationGamePlugin;
import com.github.taitaitatata.dominationgame.util.RespawnSession;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public final class KilledListener implements Listener {

    private DominationGamePlugin instance = DominationGamePlugin.get();

    @EventHandler
    public void onKilled(PlayerDeathEvent e) {
        if (e.getEntity() == null) return;

        String r = getReason(e);
        e.setDeathMessage(r);

        RespawnSession rs = new RespawnSession(e.getEntity(), 5);
        rs.runTaskTimer(instance, 20L, 20L);
        e.getEntity().setGameMode(GameMode.SPECTATOR);
    }

    private String getReason(PlayerDeathEvent e) {
        Player killed = e.getEntity();
        Player killer = e.getEntity().getKiller();
        ItemStack item = killer != null ? killer.getInventory().getItemInMainHand() : null;

        String reasonFormat;

        String killerName = killer != null ? killer.getDisplayName() : "";
        String killedName = killed != null ? killed.getDisplayName() : "";
        String usedItem = item != null && item.hasItemMeta() ? (
                item.getItemMeta().hasDisplayName() ? " " + item.getItemMeta().getDisplayName() : (
                        item.getItemMeta().hasLocalizedName() ? item.getItemMeta().getLocalizedName() : ""
                        )
        ) : "";

        switch (killed.getLastDamageCause().getCause()) {

            case FALL: reasonFormat = "§7[落下] §r%killed_player%";break;
            case FIRE: reasonFormat = "§7[炎上] §r%killed_player%";break;
            case LAVA: reasonFormat = "§7[溶岩] §r%killed_player%";break;
            case VOID: reasonFormat = "§7[奈落] §r%killed_player%";break;
            case CUSTOM: reasonFormat = "§7[不明] §r%killed_player%";break;
            case DRYOUT: reasonFormat = "§7[酸素不足] §r%killed_player%";break;
            case THORNS: reasonFormat = "§7[カウンター] §r%killed_player%";break;
            case POISON:
            case WITHER:
            case MAGIC: reasonFormat = "§7[魔法] §r%killed_player%";break;
            case CONTACT: reasonFormat = "§7[とげ]§r %killed_player%";break;
            case MELTING: reasonFormat = "§7[溶解] §r%killed_player%";break;
            case SUICIDE: reasonFormat = "§7[自殺] §r%killed_player%";break;
            case CRAMMING: reasonFormat = "§7[圧縮] §r%killed_player%";break;
            case DROWNING: reasonFormat = "§7[溺死] §r%killed_player%";break;
            case FIRE_TICK: reasonFormat = "§7[火傷] §r%killed_player%";break;
            case HOT_FLOOR: reasonFormat = "§7[危険地帯] §r%killed_player%";break;
            case LIGHTNING: reasonFormat = "§7[雷] §r%killed_player%";break;
            case PROJECTILE: reasonFormat = "%killer_player% §7[飛び道具%item%] §r%killed_player%";break;
            case STARVATION: reasonFormat = "§7[飢え] §r%killed_player%";break;
            case SUFFOCATION: reasonFormat = "§7[窒息] §r%killed_player%";break;
            case DRAGON_BREATH: reasonFormat = "§7[ドラゴンの呼吸] §r%killed_player%";break;
            case ENTITY_ATTACK: reasonFormat = "%killer_player% §7[通常攻撃%item%] §r%killed_player%";break;
            case FALLING_BLOCK: reasonFormat = "§7[押し潰し] §r%killed_player%";break;
            case FLY_INTO_WALL: reasonFormat = "§7[運動エネルギー] §r%killed_player%";break;
            case BLOCK_EXPLOSION: reasonFormat = "%killer_player% §7[爆発] §r%killed_player%";break;
            case ENTITY_EXPLOSION: reasonFormat = "§7[爆発] %killed_player%";break;
            case ENTITY_SWEEP_ATTACK: reasonFormat = "%killer_player% §7[薙ぎ払い%item%] §r%killed_player%";break;

            default: reasonFormat = "§7[不明] %killed_player%";break;

        }

        if (killer != null) {
            killer.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§7KILLED §c"+killer.getDisplayName() + " §7LAST DAMAGE: §c" + String.format("%.1f", killed.getLastDamageCause().getDamage())));
        }

        return reasonFormat
                .replaceAll("%killed_player%", killedName)
                .replaceAll("%killer_player%", killerName)
                .replaceAll("%item%", usedItem + "§7");
    }

}
