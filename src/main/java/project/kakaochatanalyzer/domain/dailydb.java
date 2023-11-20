package project.kakaochatanalyzer.domain;

import jakarta.persistence.*;

//상세페이지 db
@Entity
@Table(name = "your_table_name")
public class dailydb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @Column(name = "frequently")
    private Integer frequently;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "chattimes")
    private Integer chatTimes;

    @Column(name = "total_message")
    private Integer totalMessage;

    // getters and setters
}