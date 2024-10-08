package by.sema.socialnetwork.servises;


import by.sema.socialnetwork.entities.Message;
import by.sema.socialnetwork.entities.User;
import by.sema.socialnetwork.repositorises.MessageRepository;
import by.sema.socialnetwork.repositorises.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }


//    public void createMessage(SendMessageDTO sendMessageDTO, int senderId) {
//        User fromUser = userRepository.findById(senderId).get();
//        User toUser = userRepository.findByUsername(sendMessageDTO.getReceiver());
//
//        Message message = new Message();
//        message.setSender(fromUser);
//        message.setReceiver(toUser);
//        message.setContent(sendMessageDTO.getContent());
//
//        messageRepository.save(message);
//    }
//
//
//    public List<GetMessageDTO> getAllUserMessages(int userId) {
//        List<Message> messages = messageRepository.findAllBySenderIdOrReceiverId(userId);
//
//        return messages.stream()
//                .map(this::convertMessageToGetMessageDTO)
//                .toList();
//    }


    public Message sendMessage(Integer senderId, Integer receiverId, String content) {
        Message message = new Message();
        message.setSender(userRepository.findById(senderId).get());
        message.setReceiver(userRepository.findById(receiverId).get());
        message.setContent(content);
        return messageRepository.save(message);
    }

    public List<Message> getMessagesBetweenUsers(Integer userId1, Integer userId2) {

        return messageRepository.findAllMessagesBetweenTwoUsers(userId1, userId2);

    }

//    private Message convertMessageDTOToMessage(MessageDTO messageDTO) {
//        Message message = new Message();
//        message.setSender(messageRepository.);
//    }


//    private GetMessageDTO convertMessageToGetMessageDTO(Message message) {
//        GetMessageDTO getMessageDTO = new GetMessageDTO();
//
//        getMessageDTO.setSender(message.getSender().getUsername());
//        getMessageDTO.setReceiver(message.getReceiver().getUsername());
//        getMessageDTO.setContent(message.getContent());
//
//        return getMessageDTO;
//    }

}



