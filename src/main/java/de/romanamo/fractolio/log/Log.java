package de.romanamo.fractolio.log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {

    public static final String NAME = "fractolio";

    public static final String LOG_FILE_NAME = "fractolio";

    public static final String LOG_FOLDER_NAME = "logs";

    public static Logger logger = Logger.getLogger(NAME);

    public static LogManager manager = LogManager.getLogManager();

    public static void initialize(String location) {
        try {
            final File answer;
            Path path = Paths.get(".\\logs\\"+ LOG_FILE_NAME + ".log");
            Path parent = path.getParent();
            if (parent != null && Files.notExists(parent)) {
                Files.createDirectories(parent);
            }
            if (Files.notExists(path)) {
                logger.info("Target file \"" + location + "\" will be created.");
                answer = Files.createFile(path).toFile();
            }
        } catch (IOException e) {
            throw new RuntimeException();

        }

        try {
            FileHandler fileHandler = new FileHandler("logs\\" + LOG_FILE_NAME + ".log");
            logger.addHandler(fileHandler);
            System.setProperty("java.util.logging.SimpleFormatter.format",
                    "[%4$s] [%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS] %2$s() >> %5$s%6$s%n");
        } catch (IOException |
                 SecurityException e) {
            logger.warning("Achtung");
            throw new RuntimeException(e);
        }

    }
}
