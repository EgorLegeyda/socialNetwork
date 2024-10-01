package by.sema.socialnetwork.DTO.Message;


import lombok.Data;

@Data
public class MessageDTO {
    private Integer senderId;
    private Integer receiverId;
    private String content;
}
