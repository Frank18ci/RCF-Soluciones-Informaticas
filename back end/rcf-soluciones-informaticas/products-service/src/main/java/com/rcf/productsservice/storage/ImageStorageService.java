package com.rcf.productsservice.storage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageStorageService {
    public static final Path root = Paths.get("resources","uploads");

    public void saveImage(MultipartFile image, String fileName) throws IOException {
        if (image == null || image.isEmpty()) {
            throw new RuntimeException("Image file is empty");
        }
        Files.createDirectories(root);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(root, fileName + ".*")) {
            for (Path oldFile : stream) {
                Files.deleteIfExists(oldFile);
            }
        }

        BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
        if (bufferedImage == null) {
            throw new RuntimeException("Failed to read image file");
        }

        String fileNameWithExt = fileName + ".jpg";
        Path rutaCompleta = root.resolve(fileNameWithExt);

        ImageIO.write(bufferedImage, "jpg", rutaCompleta.toFile());
    }

}
