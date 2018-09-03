package no.greenall.shaclvalidation.utils;

import java.io.InputStream;

public class FileUtils {
    public static InputStream readToStream(String filename) {
        return FileUtils.class.getClassLoader().getResourceAsStream(filename);
    }
}
