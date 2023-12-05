package project.kakaochatanalyzer.Detail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.entity.Dailydb;
import project.kakaochatanalyzer.Login.entity.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SpringDataJpadailydbRepository extends JpaRepository<Dailydb, Long>, DailydbRepository {
    @Override
    Optional<Dailydb> findBymemberId(Long memberId);
    @Override
    Optional<Dailydb> findBychatRoom(ChatRoom chatRoom);
//    @Override
//    Optional<Dailydb> findBykeyword(String keyword);
    @Override
    Optional<Dailydb> findBychatTimes(Integer chatTimes);
    @Override
    Optional<Dailydb> findBytotalMessage(String totalMessage);
    @Override
    Optional<Dailydb> findByDateAndChatRoomIdAndMemberId(LocalDate date, Long chatroomId, Long memberId);
    @Override
    List<Dailydb> findByChatRoomIdAndMemberId(Long memberId, Long chatroomId);
}
