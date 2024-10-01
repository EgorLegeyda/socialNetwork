package by.sema.socialnetwork.servises;




import by.sema.socialnetwork.entities.Photo;
import by.sema.socialnetwork.entities.User;
import by.sema.socialnetwork.repositorises.PhotoRepository;
import by.sema.socialnetwork.repositorises.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    private final String uploadDir = "src/main/resources/images/"; // Папка для хранения фотографий


    @Autowired
    public PhotoService(PhotoRepository photoRepository, UserRepository userRepository) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    public Photo savePhoto(MultipartFile file, Integer userId) throws IOException {
        String filename = file.getOriginalFilename();
        String filePath = uploadDir + filename;

        // Сохраняем файл на сервере
        File uploadFile = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(uploadFile)) {
            fos.write(file.getBytes());
        }


        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);

        // Сохраняем URL в базе данных
        Photo photo = new Photo(filePath, filename, user);
        //photo.setUser(userRepository.findById(userId).get());

        return photoRepository.save(photo);
    }

    public Optional<Photo> getPhoto(Integer userId) {
        return photoRepository.findByUserId(userId);
    }
}