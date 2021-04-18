package me.greazi.magicvalley;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class TestUtil {
    public static void recursiveDelete(@NotNull File directoryToBeDeleted) {
        if (directoryToBeDeleted.isDirectory()) {
            for (File file : directoryToBeDeleted.listFiles()) {
                recursiveDelete(file);
            }
        }
        directoryToBeDeleted.delete();
    }
}
