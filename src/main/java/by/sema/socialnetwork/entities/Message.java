package by.sema.socialnetwork.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonBackReference
    private User sender;

    @ManyToOne()
    @JoinColumn(name = "receiver_id")
    @JsonBackReference
    private User receiver;

    @Column(name = "content")
    private String content;
}
