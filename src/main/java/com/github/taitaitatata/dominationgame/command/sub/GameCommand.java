package com.github.taitaitatata.dominationgame.command.sub;

import org.bukkit.command.*;

public final class GameCommand {

    public void run(CommandSender sender, Command command, String label, String[] args) {
        switch (args[1]) {
            case "start":
            case "stop":
            case "info":

            default:
                break;
        }
    }

}
