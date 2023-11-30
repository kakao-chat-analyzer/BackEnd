package project.kakaochatanalyzer.Detail.repository;

import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Login.entity.Member;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository {

    ChatRoom save(ChatRoom chatRoom);
    Optional<ChatRoom> findByid(Long id);
    Optional<ChatRoom> findByroomNumber(Long findByroomNumber);
    List<ChatRoom> findByMemberId(Long id);
    List<ChatRoom> findAll();
    Optional<Long> findMaxRoomNumberByMemberId(Long memberId);

    static Optional<ChatRoom> findByMemberIdAndRoomNumber(Member memberId, Long roomNumber) {
        return null;
    }

}
