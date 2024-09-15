package by.sema.socialnetwork.servises;


import by.sema.socialnetwork.DTO.CreateUserDTO;
import by.sema.socialnetwork.entities.User;
import by.sema.socialnetwork.DTO.UpdateUserDTO;
import by.sema.socialnetwork.repositorises.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        //return usersRepository.getReferenceById(id);

        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public void createUser(CreateUserDTO createUserDTO) {
        User user = new User();

        user.setUsername(createUserDTO.getUsername());
        user.setPassword(createUserDTO.getPassword());
        user.setEmail(createUserDTO.getEmail());
        user.setFirstName(createUserDTO.getFirstName());
        user.setLastName(createUserDTO.getLastName());

        userRepository.save(user);
    }

    public void updateUser(int id, UpdateUserDTO updateUserDTO) {
        User userToUpdate = getUserById(id);
        convertDTOToUser(updateUserDTO, userToUpdate);

    }
    public void deleteUser(int id) {
        userRepository.delete(getUserById(id));
    }

    private User convertDTOToUser(UpdateUserDTO updateUserDTO, User user) {
        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());
        user.setEmail(updateUserDTO.getEmail());
        user.setPassword(updateUserDTO.getPassword());
        return user;
    }
}
