package by.sema.socialnetwork.DTO;


import by.sema.socialnetwork.entities.User;
import lombok.Data;

@Data
public class SendMessageDTO {
    private String receiver;
    private String content;
}
