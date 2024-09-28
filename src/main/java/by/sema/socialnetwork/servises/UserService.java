package by.sema.socialnetwork.servises;


import by.sema.socialnetwork.DTO.User.CreateUserDTO;
import by.sema.socialnetwork.DTO.User.ShortUserInfoDTO;
import by.sema.socialnetwork.DTO.User.UpdateUserDTO;
import by.sema.socialnetwork.DTO.User.FullUserInfoDTO;
import by.sema.socialnetwork.entities.User;
import by.sema.socialnetwork.repositorises.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<ShortUserInfoDTO> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> modelMapper.map(user, ShortUserInfoDTO.class))
                .toList();

    }

    public ShortUserInfoDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        ShortUserInfoDTO userDTO = new ShortUserInfoDTO();

        modelMapper.map(user, userDTO);

        return userDTO;
    }


    public FullUserInfoDTO getFullUserInfoById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        FullUserInfoDTO fullUserInfoDTO = new FullUserInfoDTO();

        modelMapper.map(user, fullUserInfoDTO);

        return fullUserInfoDTO;
    }


    public void createUser(CreateUserDTO createUserDTO) {
        User user = new User();

        modelMapper.map(createUserDTO, user);

        userRepository.save(user);
    }


    public void updateUser(int id, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        modelMapper.map(updateUserDTO, user);
        userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }




}

