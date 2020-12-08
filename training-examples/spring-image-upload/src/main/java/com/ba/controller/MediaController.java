package com.ba.controller;

import com.ba.model.Media;
import com.ba.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/file")
public class MediaController {

    private static final String JPG_EXTENSION = ".jpg";
    private static final String PNG_EXTENSION = ".png";
    private static final String BMP_EXTENSION = ".bmp";

    private static final String BMP_CONTENT = "image/bmp";
    private static final String PNG_CONTENT = "image/png";

    @Value("${file.upload.directory}")
    private String uploadDir;

    @Autowired
    private MediaRepository repository;

    @GetMapping("/list")
    public List<Media> getWholeFiles() {
        return repository.findAll();
    }

    @PostMapping("/add")
    public String addFile(@RequestParam("file") MultipartFile file, @RequestParam String imageName) throws IOException {

        Files.createDirectories(Paths.get(uploadDir));
        String filePath = generateFullFilePath(file);
        Path targetLocation = FileSystems.getDefault().getPath(filePath);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        byte[] bytes = file.getBytes();

        Media media = new Media();
        media.setFileContent(bytes);
        media.setName(imageName);

        repository.save(media);

        return imageName + " eklendi";
    }

    private String generateUUID() {
        return String.valueOf(java.util.UUID.randomUUID());
    }

    private String generateFullFilePath(MultipartFile file) {

        String extension = JPG_EXTENSION;

        if (BMP_CONTENT.equals(file.getContentType())) {
            extension = BMP_EXTENSION;
        } else if (PNG_CONTENT.equals(file.getContentType())) {
            extension = PNG_EXTENSION;
        }

        return uploadDir + generateUUID() + extension;
    }

}
