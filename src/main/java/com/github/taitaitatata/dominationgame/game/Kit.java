package com.github.taitaitatata.dominationgame.game;

import com.github.taitaitatata.dominationgame.util.SimpleLocation;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.*;

public final class Kit {

    private String name;
    private String content;


    public Kit(String name, ItemStack[] dContent) {
        this.name = name;
        this.content = encode(dContent);


    }

    public String encode(ItemStack[] dContent) {
        String outc = null;
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(bytes);

            out.writeObject(dContent);

            outc = Base64Coder.encodeLines(bytes.toByteArray());
        } catch (IOException ignored) {}

        return outc;
    }

    public ItemStack[] decode(String decodeLines) {
        Object obj;
        ItemStack[] is = null;
        try {
            ByteArrayInputStream bytes = new ByteArrayInputStream(Base64Coder.decodeLines(decodeLines));
            BukkitObjectInputStream in = new BukkitObjectInputStream(bytes);

            obj = in.readObject();


            if (obj instanceof ItemStack[]) is = (ItemStack[]) obj;
        } catch (IOException | ClassNotFoundException ignored) {}

        return is;
    }

    public void setContent(String c) {
        this.content = c;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
