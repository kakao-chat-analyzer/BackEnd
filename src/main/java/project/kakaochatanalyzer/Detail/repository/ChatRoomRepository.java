package project.kakaochatanalyzer.Detail.repository;

import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.entity.dailydb;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository {

    ChatRoom save(ChatRoom chatRoom);
    Optional<ChatRoom> findByid(Long id);
    Optional<ChatRoom> findByroomNumber(Long findByroomNumber);
    List<ChatRoom> findAll();

}
