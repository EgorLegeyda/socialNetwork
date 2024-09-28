package by.sema.socialnetwork.controllers;


import by.sema.socialnetwork.DTO.User.CreateUserDTO;
import by.sema.socialnetwork.DTO.User.ShortUserInfoDTO;
import by.sema.socialnetwork.DTO.User.UpdateUserDTO;
import by.sema.socialnetwork.entities.User;
import by.sema.socialnetwork.repositorises.UserRepository;
import by.sema.socialnetwork.servises.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<ShortUserInfoDTO>> getAllUsers() {
        List<ShortUserInfoDTO> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public  ResponseEntity<ShortUserInfoDTO> UserById(@PathVariable int id) {
        ShortUserInfoDTO shortUserInfoDTO = userService.getUserById(id);

        return new ResponseEntity<>(shortUserInfoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDTO) {
        userService.createUser(createUserDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody UpdateUserDTO updateUserDTO, @PathVariable int id) {
        userService.updateUser(id, updateUserDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }



}
