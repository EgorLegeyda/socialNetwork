package by.sema.socialnetwork.DTO;


import lombok.Data;

@Data
public class GetMessageDTO {
    private String sender;
    private String receiver;
    private String content;

}
