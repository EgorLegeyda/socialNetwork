package by.sema.socialnetwork.DTO.Message;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponseDTO {
    Integer messageId;
    Integer senderId;
    Integer receiverId;
    String content;
}
