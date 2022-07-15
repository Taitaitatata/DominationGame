package com.github.taitaitatata.dominationgame.command;

import com.github.taitaitatata.dominationgame.DominationGamePlugin;

import com.github.taitaitatata.dominationgame.command.sub.*;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class MainCommand implements CommandExecutor, TabCompleter {

    private DominationGamePlugin instance = DominationGamePlugin.get();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length <= 1) {
            sendUsage(sender);
            return true;
        }

        switch (args[0]) {
            case "help":
                new HelpCommand().run(sender, command, label, args);
                break;
            case "arena":
                new ArenaCommand().run(sender, command, label, args);
                break;
            case "game":

                break;

            default:
                sendUsage(sender);
                break;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tab = new ArrayList<>();

        String[] subCommand = new String[]{"arena", "game", "help"};

        String[] arenaSubCommand = new String[]{"create", "remove", "edit", "list"};
        String[] gameSubCommand = new String[]{"start", "stop", "info"};



        if (sender.hasPermission("dominationgame.admin") && (sender instanceof Player || sender instanceof ConsoleCommandSender)) {
            if (args.length == 1) {
                for (String t1:subCommand) {
                    if (args[0].toLowerCase().startsWith(t1.toLowerCase())) tab.add(t1);
                }
            } else if (args.length == 2) {

            }
        }

        return tab;
    }

    public static void sendUsage(CommandSender s) {
        String usage = "§eDomination Game §8v1.0\n" +
                "§7§m--------------------------------------------------§r\n" +
                " §7/dg help §b[<cmd>]\n" +
                " §7- §fこのプラグインのヘルプを表示\n" +
                " §7/dg arena <create|remove|edit|list> §b[<args>]\n" +
                " §7- §fアリーナを管理します。(詳細: /dg help arena)\n" +
                " §7/dg game <start|stop|info> §b<id>\n" +
                " §7- §fゲームの開始/停止/情報を表示します。 (詳細: /dg help game)";

        s.sendMessage(usage);

    }

}
