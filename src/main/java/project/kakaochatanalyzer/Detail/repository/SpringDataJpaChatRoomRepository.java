package project.kakaochatanalyzer.Detail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;

import java.util.Optional;

public interface SpringDataJpaChatRoomRepository extends JpaRepository<ChatRoom, Long>, ChatRoomRepository{
    @Override
    Optional<ChatRoom> findByroomNumber(Long room_number);
}
