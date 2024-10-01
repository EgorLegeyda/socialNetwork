package by.sema.socialnetwork.controllers;


import by.sema.socialnetwork.DTO.Message.MessageResponseDTO;
import by.sema.socialnetwork.entities.Message;
import by.sema.socialnetwork.entities.User;
import by.sema.socialnetwork.servises.MessageService;
import by.sema.socialnetwork.servises.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import by.sema.socialnetwork.DTO.Message.MessageDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
public class MessageController {


    private final MessageService messageService;

    private final UserService userService;
    private final ModelMapper modelMapper;

    public MessageController(MessageService messageService, UserService userService, ModelMapper modelMapper) {
        this.messageService = messageService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody MessageDTO messageDTO) {


        Message message = messageService.sendMessage(messageDTO.getSenderId(),
                messageDTO.getReceiverId(), messageDTO.getContent());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/between/{userId1}/{userId2}")
    public ResponseEntity<List<MessageResponseDTO>> getMessagesBetweenUsers(@PathVariable Integer userId1, @PathVariable Integer userId2) {
        List<Message> messages = messageService.getMessagesBetweenUsers(userId1, userId2);

        List<MessageResponseDTO> messageResponseDTOS = messages.stream()
                .map(message -> new MessageResponseDTO(message.getId(), message.getSender().getId(), message.getReceiver().getId(), message.getContent()))
                .collect(Collectors.toList());


        return ResponseEntity.ok(messageResponseDTOS);
    }

}
