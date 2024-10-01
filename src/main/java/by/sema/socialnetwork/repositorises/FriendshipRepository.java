package by.sema.socialnetwork.repositorises;

import by.sema.socialnetwork.entities.Friendship;
import by.sema.socialnetwork.status.FriendshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {
    List<Friendship> findAllByUserIdAndStatus(Integer user_id, FriendshipStatus status);
    Optional<Friendship> findByUserIdAndFriendId(Integer userId, Integer friendId);

   // List<Friendship> findAllByUserIdAndStatus(Integer userId, FriendshipStatus status);

    @Modifying
    @Query("UPDATE Friendship f set f.status = ?3 where f.user.id = ?1 and  f.friend.id =?2")
    void changeStatus(Integer userId, Integer requestFromId, FriendshipStatus status);


    @Modifying
    @Query("UPDATE Friendship f set f.status = ?3 where ((f.user.id = ?1 and  f.friend.id =?2) or (f.user.id = ?2 and  f.friend.id =?1)) ")
    void changeStatusForRemoveFriend(Integer userId1, Integer userId2, FriendshipStatus status);

    @Query("SELECT f FROM Friendship f WHERE f.friend.id = :friendId AND f.status = :status")
    List<Friendship> findAllByFriendIdAndStatus(@Param("friendId") Integer friendId, @Param("status") FriendshipStatus status);

}
