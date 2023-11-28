package project.kakaochatanalyzer.Detail.entity;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import project.kakaochatanalyzer.Login.entity.Member;

import java.time.LocalDate;
import java.util.List;

//상세페이지 db
@Entity
@Table(name = "dailydb")
public class Dailydb {
    public Dailydb() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "chatroomId")
    private ChatRoom chatRoom;
    @Column(name ="date")
    private LocalDate date;
    @Column(name = "frequently")
    private Integer frequently;
    @Column(name = "dailyMessages",columnDefinition = "TEXT",length = 100000)
    @Convert(converter = StringListConverter.class)
    private List<String> dailyMessages;

    @Column(name = "dailyUser",columnDefinition = "TEXT",length = 1000000)
    @Convert(converter = StringListConverter.class)
    private List<String> dailyUser;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "chattimes")
    private Integer chatTimes;

    @Column(name = "total_message",columnDefinition = "TEXT",length = 100000)
    private String totalMessage;


    // constructor
    // 생성자
    public Dailydb(Member member, ChatRoom chatRoom) {
        this.member = member;
        this.chatRoom = chatRoom;
    }
    // getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public Integer getFrequently() {
        return frequently;
    }

    public void setFrequently(Integer frequently) {
        this.frequently = frequently;
    }

    public List<String> getDailyMessages() {
        return dailyMessages;
    }

    public void setDailyMessages(List<String> dailyMessages) {
        this.dailyMessages = dailyMessages;
    }

    public List<String> getDailyUser() {
        return dailyUser;
    }

    public void setDailyUser(List<String> dailyUser) {
        this.dailyUser = dailyUser;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getChatTimes() {
        return chatTimes;
    }

    public void setChatTimes(Integer chatTimes) {
        this.chatTimes = chatTimes;
    }

    public String getTotalMessage() {
        return totalMessage;
    }

    public void setTotalMessage(String totalMessage) {
        this.totalMessage = totalMessage;
    }
}