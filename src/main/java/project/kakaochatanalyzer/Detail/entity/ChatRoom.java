package project.kakaochatanalyzer.Detail.entity;

import jakarta.persistence.*;
import project.kakaochatanalyzer.Detail.repository.ChatRoomRepository;

@Entity
@Table(name = "chatroom")
public class ChatRoom {
    public ChatRoomRepository DailydbRepository;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roomNumber;

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

}
