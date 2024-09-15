package by.sema.socialnetwork.controllers;


import by.sema.socialnetwork.DTO.GetMessageDTO;
import by.sema.socialnetwork.DTO.SendMessageDTO;
import by.sema.socialnetwork.servises.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {


    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @PostMapping("/users/{senderId}/messages")
    public void sendMessage(@RequestBody SendMessageDTO sendMessageDTO, @PathVariable int senderId){
        messageService.createMessage(sendMessageDTO, senderId);
    }


    @GetMapping("/users/{senderId}/messages")
    public List<GetMessageDTO> getAllUserMessages(@PathVariable int senderId){
        return messageService.getAllUserMessages(senderId);
    }
}
