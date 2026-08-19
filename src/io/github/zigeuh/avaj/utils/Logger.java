package io.github.zigeuh.avaj.utils;

import java.io.*;

public class Logger {
    private static PrintWriter writer;

    private Logger() {}

    public static void init(String filename) throws IOException {
        writer = new PrintWriter(new FileWriter(filename), true);
    }

    public static void log(String str) {
        if (writer != null) {
            writer.println(str);
        }
    }

    public static void close() {
        if (writer != null)
            writer.close();
    }
}
