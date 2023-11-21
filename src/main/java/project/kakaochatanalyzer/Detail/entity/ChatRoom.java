package project.kakaochatanalyzer.Detail.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chatroom")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long roomNumber;

}
