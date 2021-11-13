package com.example.online_store;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageUtils {

    public static String saveImage(Part part) {
        String imageFolder = ApplicationProperties.getInstance().getProperty("images-path");
        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        Matcher matcher = Pattern.compile("^.+(\\..+)$").matcher(fileName);
        if (matcher.find()) {
            try (InputStream fileContent = part.getInputStream()) {
                String uuid = UUID.randomUUID().toString();
                String extension = matcher.group(1);
                File file = new File(imageFolder, uuid + extension);
                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                return uuid + extension;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
