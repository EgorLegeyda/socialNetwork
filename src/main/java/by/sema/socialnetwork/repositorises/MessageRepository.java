package by.sema.socialnetwork.repositorises;

import by.sema.socialnetwork.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query(value = "" +
            "SELECT m " +
            "From Message m " +
            "JOIN m.receiver u " +
            "WHERE ((m.sender.id = :firstUserId AND m.receiver.id = :secondUserId) " +
            "OR  (m.sender.id = :secondUserId AND m.receiver.id = :firstUserId)) ")
    List<Message> findAllMessagesBetweenTwoUsers(@Param("firstUserId") String firstUserId, @Param("secondUserId") String secondUserId);





    @Query(value = "SELECT m FROM Message as m " +
            "WHERE m.sender.id = :userId or m.receiver.id = :userId")
    List<Message> findAllBySenderIdOrReceiverId(@Param("userId") int UserId);


}
