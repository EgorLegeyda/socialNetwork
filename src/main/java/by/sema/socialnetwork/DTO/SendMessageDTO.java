package by.sema.socialnetwork.DTO;


import lombok.Data;

@Data
public class SendMessageDTO {
    private String receiver;
    private String content;
}
