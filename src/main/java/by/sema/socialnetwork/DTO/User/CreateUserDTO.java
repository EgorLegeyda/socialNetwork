package by.sema.socialnetwork.DTO.User;


import lombok.Data;

@Data
public class CreateUserDTO {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
