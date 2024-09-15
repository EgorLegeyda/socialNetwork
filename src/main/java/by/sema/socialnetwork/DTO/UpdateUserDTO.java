package by.sema.socialnetwork.DTO;


import lombok.Data;

@Data
public class UpdateUserDTO {
    private int id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
