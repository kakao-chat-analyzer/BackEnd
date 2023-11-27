package project.kakaochatanalyzer.Detail.entity;

import jakarta.persistence.*;
import project.kakaochatanalyzer.Detail.repository.ChatRoomRepository;
import project.kakaochatanalyzer.Login.entity.Member;

@Entity
@Table(name = "chatroom")
public class ChatRoom {
    public ChatRoom() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
    @Column(name = "roomNumber")
    private Long roomNumber;

    public ChatRoom(Member member) {
        this.member = member;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Long getId() {
        return id;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
