package by.sema.socialnetwork.DTO.User;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

@Data
public class ShortUserInfoDTO {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String description;
    private String image;

}
