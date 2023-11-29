package project.kakaochatanalyzer.Detail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaChatRoomRepository extends JpaRepository<ChatRoom, Long>, ChatRoomRepository{
    @Override
    Optional<ChatRoom> findByroomNumber(Long room_number);
    @Override
    List<ChatRoom> findByMemberId(Long id);
    @Override
    @Query("SELECT MAX(c.roomNumber) FROM ChatRoom c WHERE c.member.id = :memberId")
    Optional<Long> findMaxRoomNumberByMemberId(Long memberId);
}