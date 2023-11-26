package project.kakaochatanalyzer.Detail.repository;

import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.entity.dailydb;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository {
    static Long findMaxRoomNumber() {
        return null;
    }

    static ChatRoom save(ChatRoom chatRoom) {
        return null;
    }
    Optional<ChatRoom> findByid(Long id);
    Optional<ChatRoom> findByroomNumber(Long findByroomNumber);
    List<ChatRoom> findAll();

    void save(dailydb dailyDbEntity);
}
