package com.github.taitaitatata.dominationgame;

import com.google.gson.Gson;
import com.github.taitaitatata.dominationgame.file.Arena;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonLoadTest {

    @Test
    public void loadTest() {
        ClassLoader cl = getClass().getClassLoader();
        Gson gson = new Gson();

        try {
            File file = new File(cl.getResource("arena1.json").getFile());

            Arena a = gson.fromJson(
                    Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8),
                    Arena.class
            );

            System.out.println(a.getDisplayName());
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

}
