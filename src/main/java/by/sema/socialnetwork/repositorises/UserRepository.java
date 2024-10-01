package by.sema.socialnetwork.repositorises;


import by.sema.socialnetwork.entities.User;
import by.sema.socialnetwork.status.FriendshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


     User findByUsername(String username);

     User getUserById(Integer id);

}
