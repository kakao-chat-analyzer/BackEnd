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
<<<<<<< HEAD

    static Optional<ChatRoom> findByMemberIdAndRoomNumber(String memberId, Long roomNumber) {
        return null;
    }

=======
    Optional<ChatRoom> findByMemberIdAndRoomNumber(Member memberId, Long roomNumber);
>>>>>>> a1a0c5bea3f9b243d41543e2da537f8848930a11
}
