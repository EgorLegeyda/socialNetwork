package by.sema.socialnetwork.controllers;

import by.sema.socialnetwork.servises.PhotoService;
import by.sema.socialnetwork.entities.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping
    public ResponseEntity<Photo> uploadPhoto(@RequestParam("file") MultipartFile file) {
        try {
            Photo photo = photoService.savePhoto(file);
            return new ResponseEntity<>(photo, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getPhoto(@PathVariable Integer id) {
        Optional<Photo> photo = photoService.getPhoto(id);
        if (photo.isPresent()) {
            try {
                Path path = Paths.get(photo.get().getUrl());
                byte[] image = Files.readAllBytes(path);
                String base64Image = "data:image/png;base64," +  Base64.getEncoder().encodeToString(image);
                return ResponseEntity.ok(base64Image);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading image");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Photo not found");
        }
    }
}