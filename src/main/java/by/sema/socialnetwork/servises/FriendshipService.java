package by.sema.socialnetwork.servises;


import by.sema.socialnetwork.DTO.Friendship.FriendshipDTO;
import by.sema.socialnetwork.DTO.Friendship.OfferToBeFriendsDTO;
import by.sema.socialnetwork.entities.Friendship;
import by.sema.socialnetwork.entities.User;
import by.sema.socialnetwork.repositorises.FriendshipRepository;
import by.sema.socialnetwork.repositorises.UserRepository;
import by.sema.socialnetwork.status.FriendshipStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;


    @Autowired
    public FriendshipService(FriendshipRepository friendshipRepository, UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }


    public List<FriendshipDTO> getAllFriends(Integer userId) {
        List<Friendship> friendships = friendshipRepository.findAllByFriendIdAndStatus(userId, FriendshipStatus.ACCEPTED);

        return friendships.stream()
                .map(this::convertFriendshipToDTO)
                .toList();

    }

    public List<OfferToBeFriendsDTO> getAllCandidatesToFriends(Integer userId) {
        List<Friendship> friendships = friendshipRepository.findAllByFriendIdAndStatus(userId, FriendshipStatus.PENDING);



        return friendships.stream()
                .map(friendship -> {
                    OfferToBeFriendsDTO offerToBeFriendsDTO = new OfferToBeFriendsDTO();
                    offerToBeFriendsDTO.setUserId(friendship.getFriend().getId());
                    offerToBeFriendsDTO.setRequestFromId(friendship.getUser().getId());
                    offerToBeFriendsDTO.setRequestFromUsername(friendship.getUser().getUsername());
                    offerToBeFriendsDTO.setRequestFromImageUrl(friendship.getUser().getPhoto().getUrl());
                    return offerToBeFriendsDTO;
                })
                .toList();
    }


    public void createRequestForAddingFriend(Integer userId, Integer friendCandidateId) {

        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);

        User friendCandidate = userRepository.findById(friendCandidateId).orElseThrow(NoSuchElementException::new);

        Friendship friendshipFromDb = friendshipRepository.findByUserIdAndFriendId(userId, friendCandidateId).orElse(null);


        if (friendshipFromDb == null) {
            Friendship friendship = new Friendship();
            friendship.setStatus(FriendshipStatus.PENDING);
            friendship.setUser(user);
            friendship.setFriend(friendCandidate);

            friendshipRepository.save(friendship);
            //return true;
        } else if (friendshipFromDb.getStatus() == FriendshipStatus.REJECTED) {
            friendshipFromDb.setStatus(FriendshipStatus.PENDING);
            friendshipRepository.save(friendshipFromDb);
            //return true;
        }
    }

    public void acceptFriendship(Integer userId, Integer requestFromId) {
        friendshipRepository.changeStatus(requestFromId, userId, FriendshipStatus.ACCEPTED);
    }

    public void rejectFriendship(Integer userId, Integer friendId) {
        friendshipRepository.changeStatus(userId, friendId, FriendshipStatus.REJECTED);
    }

    public void removeFriend(Integer userId1, Integer userId2) {
        friendshipRepository.changeStatusForRemoveFriend(userId1, userId2, FriendshipStatus.REJECTED);
    }


    private FriendshipDTO convertFriendshipToDTO(Friendship friendship) {
        FriendshipDTO friendshipDTO = new FriendshipDTO();

        friendshipDTO.setUserId(friendship.getUser().getId());
        friendshipDTO.setUserUsername(friendship.getUser().getUsername());
        friendshipDTO.setFriendId(friendship.getFriend().getId());
        friendshipDTO.setFriendUsername(friendship.getFriend().getUsername());
        friendshipDTO.setStatus(friendship.getStatus().name());


        User friend = userRepository.getUserById(friendship.getFriend().getId());

        return friendshipDTO;

    }
}
