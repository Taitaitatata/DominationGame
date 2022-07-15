package com.github.taitaitatata.dominationgame.game;

import com.github.taitaitatata.dominationgame.util.SimpleLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public final class Flag {

    SimpleLocation pos1;
    SimpleLocation pos2;

    SimpleLocation central;

    String name;
    Progress defaults;

    public final static class Progress {
        String capturedTeam;
        long captured;

        public Progress(String ct, long c) {
            this.captured = c;
            this.capturedTeam = ct;
        }

        public void setCapturedTeam(String t) {
            this.capturedTeam = t;
        }

        public void setCaptured(long captured) {
            this.captured = captured;
        }

        public long getCaptured() {
            return captured;
        }

        public String getCapturedTeam() {
            return capturedTeam;
        }
    }

}
