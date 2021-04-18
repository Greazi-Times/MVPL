package net.shatteredlands.shatt.backup;

import me.greazi.magicvalley.MVpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipLibrary {
    private static final String BACKUP_DIRECTORY = MVpl.getMainDirectory() + "backup" + File.separator;
    private static final File BACKUP_DIR = new File(BACKUP_DIRECTORY);
    private static final File FLAT_FILE_DIRECTORY = new File(MVpl.getFlatFileDirectory());
    private static final File CONFIG_FILE = new File(MVpl.getMainDirectory() + "config.yml");

    public static void mcMMOBackup() throws IOException {
        try {
            if (BACKUP_DIR.mkdir()) {
                MVpl.p.debug("Created Backup Directory.");
            }
        }
        catch (Exception e) {
            MVpl.p.getLogger().severe(e.toString());
        }

        // Generate the proper date for the backup filename
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        File fileZip = new File(BACKUP_DIRECTORY + File.separator + dateFormat.format(date) + ".zip");

        // Create the Source List, and add directories/etc to the file.
        List<File> sources = new ArrayList<>();

        sources.add(FLAT_FILE_DIRECTORY);
        sources.add(CONFIG_FILE);

        // Actually do something
        MVpl.p.debug("Backing up your MVpl Configuration... ");

        packZip(fileZip, sources);
    }

    private static void packZip(File output, List<File> sources) throws IOException {
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(output));
        zipOut.setLevel(Deflater.DEFAULT_COMPRESSION);

        for (File source : sources) {
            if (source.isDirectory()) {
                zipDir(zipOut, "", source);
            }
            else {
                zipFile(zipOut, "", source);
            }
        }

        zipOut.flush();
        zipOut.close();
        MVpl.p.debug("Backup Completed.");
    }

    private static String buildPath(String path, String file) {
        if (path == null || path.isEmpty()) {
            return file;
        }

        return path + File.separator + file;
    }

    private static void zipDir(ZipOutputStream zos, String path, File dir) throws IOException {
        if (!dir.canRead()) {
            MVpl.p.getLogger().severe("Cannot read " + dir.getCanonicalPath() + " (Maybe because of permissions?)");
            return;
        }

        File[] files = dir.listFiles();
        path = buildPath(path, dir.getName());

        for (File source : files) {
            if (source.isDirectory()) {
                zipDir(zos, path, source);
            }
            else {
                zipFile(zos, path, source);
            }
        }
    }

    private static void zipFile(ZipOutputStream zos, String path, File file) throws IOException {
        if (!file.canRead()) {
            MVpl.p.getLogger().severe("Cannot read " + file.getCanonicalPath() + "(File Permissions?)");
            return;
        }

        zos.putNextEntry(new ZipEntry(buildPath(path, file.getName())));

        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[4092];
        int byteCount;

        while ((byteCount = fis.read(buffer)) != -1) {
            zos.write(buffer, 0, byteCount);
        }

        fis.close();
        zos.closeEntry();
    }
}
