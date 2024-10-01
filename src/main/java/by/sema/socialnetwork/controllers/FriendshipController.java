package by.sema.socialnetwork.controllers;


import by.sema.socialnetwork.DTO.Friendship.FriendshipDTO;
import by.sema.socialnetwork.DTO.Friendship.OfferToBeFriendsDTO;
import by.sema.socialnetwork.DTO.Friendship.RemoveFriendDTO;
import by.sema.socialnetwork.servises.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/friendship")
public class FriendshipController {

    private final FriendshipService friendshipService;

    @Autowired
    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @GetMapping("/friends/{userId}")
    public ResponseEntity<List<FriendshipDTO>> findAllFriendships(@PathVariable Integer userId) {
        List<FriendshipDTO> friendshipDTOList = friendshipService.getAllFriends(userId);
        return ResponseEntity.ok(friendshipDTOList);

    }


    @GetMapping("/candidates/{userId}")
    public ResponseEntity<List<OfferToBeFriendsDTO>> findAllCandidatesFriendships(@PathVariable Integer userId) {
        List<OfferToBeFriendsDTO> offerToBeFriendsDTOS = friendshipService.getAllCandidatesToFriends(userId);

        return ResponseEntity.ok(offerToBeFriendsDTOS);
    }


    @PostMapping("/addFriend")
    public ResponseEntity<Void> createRequestForAddingFriendship(@RequestBody Map<String, Object> body) {

        Integer userId = (Integer) body.get("userId");
        Integer friendId = (Integer) body.get("friendCandidateId");
        friendshipService.createRequestForAddingFriend(userId, friendId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/removeFriend")
    public ResponseEntity<Void> removeFriendship(@RequestBody RemoveFriendDTO removeFriendDTO) {
        Integer userId1 = removeFriendDTO.getUserId1();
        Integer userId2 = removeFriendDTO.getUserId2();

        friendshipService.removeFriend(userId1, userId2);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/acceptFriend")
    public ResponseEntity<Void> acceptFriend(@RequestBody Map<String, Object> body) {
        Integer userId = (Integer) body.get("userId");
        Integer requestFromId = (Integer) body.get("requestFromId");

        friendshipService.acceptFriendship(userId, requestFromId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancelRequest")
    public ResponseEntity<Void> cancelRequest(@RequestBody Map<String, Object> body) {
        Integer userId = (Integer) body.get("userId");
        Integer requestFromId = (Integer) body.get("requestFromId");

        friendshipService.rejectFriendship(requestFromId, userId);

        return ResponseEntity.ok().build();
    }



}
