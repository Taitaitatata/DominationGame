package com.github.taitaitatata.dominationgame.game;

import com.github.taitaitatata.dominationgame.file.Arena;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Match {

    private List<Player> players = new ArrayList<>();
    private final Arena arena;
    private MatchStatus status = MatchStatus.WAITING;

    public Match(Arena arena) {
        this.arena = arena;
    }

    public void forceStop(boolean save) {
        status = MatchStatus.FORCE_STOP;
    }

}
