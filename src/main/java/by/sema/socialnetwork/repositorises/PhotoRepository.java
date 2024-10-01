package by.sema.socialnetwork.repositorises;


import by.sema.socialnetwork.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    Optional<Photo> findByUserId(Integer userId);
}
