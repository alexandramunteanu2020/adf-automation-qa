package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Utility {
    private static final String FILE_NAME = "resume";
    private static final int WAITING_TIME = 3000;
    public static String downloadPath;

    public static void setDownloadPath() {
        downloadPath = System.getProperty("user.dir") + "\\data\\download";
    }

    public static DesiredCapabilities getCapabilities() {
        setDownloadPath();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadPath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        return cap;
    }

    public static void cleanDirectory() {
        try {
            FileUtils.cleanDirectory(new File(downloadPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFileDownloaded() {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();

        for (File dirContent : dirContents) {
            if (!dirContent.getName().contains(FILE_NAME))
                continue;

            if (dirContent.getName().endsWith("pdf")) {
                return true;
            } else if (dirContent.getName().endsWith("jpeg")) {
                return true;
            } else return dirContent.getName().endsWith(".doc");
        }
        return false;
    }

    public static void uploadFile(String scriptPath) throws InterruptedException {
        try {
            Thread.sleep(WAITING_TIME);
            Runtime.getRuntime().exec(scriptPath);
            Thread.sleep(WAITING_TIME);
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public static Date convertStringToDate(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }
}