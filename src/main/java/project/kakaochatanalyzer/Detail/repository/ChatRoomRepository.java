package project.kakaochatanalyzer.Detail.repository;

import project.kakaochatanalyzer.Detail.entity.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository {

    ChatRoom save(ChatRoom chatRoom);
    Optional<ChatRoom> findByid(Long id);
    Optional<ChatRoom> findByroomNumber(Long findByroomNumber);
    Optional<ChatRoom> findByMemberId(Long id);
    List<ChatRoom> findAll();
    Optional<Long> findMaxRoomNumberByMemberId(Long memberId);

}
