package com.github.taitaitatata.dominationgame.command.sub;

import com.github.taitaitatata.dominationgame.DominationGamePlugin;
import com.github.taitaitatata.dominationgame.file.Arena;
import com.github.taitaitatata.dominationgame.file.Config;
import com.github.taitaitatata.dominationgame.file.FileManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ArenaCommand {

    private DominationGamePlugin instance = DominationGamePlugin.get();
    private Config cfg = instance.getCfg();

    public void run(CommandSender sender, Command command, String label, String[] args) {
        switch (args[1]) {
            case "create":
                create(sender, command, label, args);
                break;
            case "remove":
                remove(sender, command, label, args);
                break;
            case "edit":
            case "list":
                list(sender, command, label, args);
                break;
            default:
                break;
        }
    }

    private void create(CommandSender sender, Command command, String label, String[] args) {
        if (!(args.length >= 4)) {
            sender.sendMessage("§c引数が不足しています。");
            return;
        }

        if (Bukkit.getWorld(args[3]) == null) {
            sender.sendMessage("§cそのようなワールドは存在しません。");
            return;
        }

        String id = args[2];
        String worldId = args[3];

        String displayName;
        if (args.length >= 5) {
            List<String> b = new ArrayList<>();
            Collections.addAll(b, args);

            b.remove(0);
            b.remove(0);
            b.remove(0);
            b.remove(0);

            displayName = ChatColor.translateAlternateColorCodes('&', String.join(" ", b));
        } else displayName = args[2];

        int min = cfg.getDEFAULTS_ARENA_PLAYERS_MIN();
        int max = cfg.getDEFAULTS_ARENA_PLAYERS_MAX();
        int respawnAt = cfg.getDEFAULTS_ARENA_RESPAWNAT();

        Arena a = new Arena(
                id, worldId, displayName, min, max, respawnAt, new ArrayList<>(), new ArrayList<>()
        );

        try {
            File saveTarget = new File(instance.getArenaDir(), id + ".json");

            if (saveTarget.exists()) {
                sender.sendMessage("§cすでにアリーナのファイルが存在しているため、" + id + ".json.backup として旧ファイルをコピーし新規のアリーナに置き換えます。");

                File copyTo = new File(saveTarget+".backup");
                Files.copy(saveTarget.toPath(), copyTo.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            FileManager<Arena> fm = new FileManager<>();

            Writer writer = new FileWriter(saveTarget);
            fm.save(a, writer);
            writer.flush();
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        sender.sendMessage("保存完了");
    }

    private void remove(CommandSender sender, Command command, String label, String[] args) {
        File removeTarget = new File(instance.getArenaDir(), args[2] + ".json");

        if (!(removeTarget.exists())) {
            sender.sendMessage("§cそのようなアリーナは存在しないため削除できませんでした。");
            return;
        }

        removeTarget.delete();
        sender.sendMessage("§cアリーナを削除しました。");
    }

    private void list(CommandSender sender, Command command, String label, String[] args) {
        File[] files = instance.getArenaDir().listFiles();
        List<Arena> arena = new ArrayList<>();
        Gson gson = new Gson();

        for (File f : files) {
            Bukkit.broadcastMessage(f.getName());
            if (!f.getName().endsWith(".json")) continue;

            try {
                arena.add(gson.fromJson(Files.newBufferedReader(f.toPath(), StandardCharsets.UTF_8), Arena.class));
            } catch (IOException | JsonSyntaxException i) {
                i.printStackTrace();
            }
        }

        sender.sendMessage(String.format("§7[§cDomination§7] §r現在%s個のアリーナが作成済み", arena.size()));
        for (Arena ar : arena) {
            sender.sendMessage(" " + ar.getId() + (ar.hasDisplayName() ? " §7(" + ar.getDisplayName() + "§7)": ""));
            sender.sendMessage(
                    " §7- W:" + ar.getWorldId() +  " F:" + ar.getFlags().size() + " K:" + ar.getKits().size()
                    + " Mn:" + ar.getLimits().getMin() + " Mx:" + ar.getLimits().getMax() + " R:" + ar.getRespawnAt()
            );
        }
    }

}
