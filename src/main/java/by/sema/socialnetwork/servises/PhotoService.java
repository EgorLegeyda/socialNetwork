package by.sema.socialnetwork.servises;




import by.sema.socialnetwork.entities.Photo;
import by.sema.socialnetwork.repositorises.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    private final String uploadDir = "src/main/resources/images/"; // Папка для хранения фотографий


    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Photo savePhoto(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String filePath = uploadDir + filename;

        // Сохраняем файл на сервере
        File uploadFile = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(uploadFile)) {
            fos.write(file.getBytes());
        }

        // Сохраняем URL в базе данных
        Photo photo = new Photo(filePath, filename);
        return photoRepository.save(photo);
    }

    public Optional<Photo> getPhoto(Integer id) {
        return photoRepository.findById(id);
    }
}