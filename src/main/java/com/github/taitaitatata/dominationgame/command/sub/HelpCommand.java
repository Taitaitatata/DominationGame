package com.github.taitaitatata.dominationgame.command.sub;

import org.bukkit.command.*;

import java.util.HashMap;
import java.util.Map;

public final class HelpCommand {

    private Map<String, String> helpList = new HashMap();

    public HelpCommand() {
        helpList.put("arena", "アリーナを管理するコマンドです。\n" +
                "§7§m--------------------------------------------------§r\n" +
                " §7/dg arena create §b<id> §e<world> §a[<displayName>]\n" +
                " §7- §fアリーナを作成します。\n" +
                " §7/dg arena remove §b<id>\n" +
                " §7- §fアリーナを削除します。\n" +
                " §7/dg arena edit <min|max|flag|kit|displayName> §b[<args>]\n" +
                " §7- §fアリーナを編集します。 (もっと詳細: /dg help arena-edit)\n" +
                " §7/dg arena list\n" +
                " §7- §f登録されているアリーナのリストを表示します。");

        helpList.put("game", "ゲームを管理するコマンドです。\n" +
                "§7§m--------------------------------------------------§r\n" +
                " §7/dg game start §b<id>\n" +
                " §7- §fアリーナを開始します。\n" +
                " §7/dg game stop §b<id>\n" +
                " §7- §fゲームを強制停止します。\n" +
                " §7/dg game info\n" +
                " §7- §fアリーナを情報を参照します。");

        helpList.put("arena-edit", "アリーナの設定を管理します。\n" +
                "§7§m--------------------------------------------------§r\n" +
                " §7/... min §b<int>\n" +
                " §7- §fアリーナの最小参加人数を設定\n" +
                " §7/... max §b<int>\n" +
                " §7- §fアリーナの最大参加人数を設定\n" +
                " §7/... flag <add|remove|setDefault> §b<flag> §e[<args>]\n" +
                " §7- §fアリーナを情報を参照\n" +
                " §7/... kit <add|remove|edit> §b<id> §e[<args>]\n" +
                " §7- §fアリーナのキットを管理\n" +
                " §7/... displayName §b<name>");
    }

    public void run(CommandSender sender, Command command, String label, String[] args) {
        switch (args[1]) {
            case "arena":
                sender.sendMessage(helpList.get("arena"));
                break;

            case "game":
                sender.sendMessage(helpList.get("game"));
                break;

            case "arena-edit":
                sender.sendMessage(helpList.get("arena-edit"));
                break;

            default:
                sender.sendMessage("§cそのようなコマンドのヘルプは登録されていません。");
                break;
        }
    }

}
