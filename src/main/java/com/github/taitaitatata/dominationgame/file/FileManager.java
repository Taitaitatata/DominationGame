package com.github.taitaitatata.dominationgame.file;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FileManager<T> {


    public T load(String file, Class<T> fileClass) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get(file));
        Gson gson = new Gson();

        T a = gson.fromJson(reader, fileClass);
        reader.close();
        return a;
    }

    public T load(Reader reader, Class<T> fileClass) throws IOException {
        Gson gson = new Gson();

        return gson.fromJson(reader, fileClass);
    }

    public void save(Object obj, String file) throws IOException {
        Writer writer = new PrintWriter(file);
        Gson gson = new Gson();

        gson.toJson(obj, writer);
        writer.close();
    }

    public void save(Object obj, Writer writer) {
        Gson gson = new Gson();

        gson.toJson(obj, writer);
    }

}
