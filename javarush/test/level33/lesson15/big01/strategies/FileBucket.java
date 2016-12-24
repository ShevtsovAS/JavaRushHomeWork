package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by sas on 03.12.16.
 */
public class FileBucket {
    Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        return 0;
    }

    public void putEntry(Entry entry) {
        try {
            new ObjectOutputStream(new FileOutputStream(path.toFile())).writeObject(entry);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry() {
        if (getFileSize() == 0) return null;
        Entry result = null;
        try {
            result = (Entry) new ObjectInputStream(new FileInputStream(path.toFile())).readObject();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return result;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }
}
