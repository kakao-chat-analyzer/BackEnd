package project.kakaochatanalyzer.Detail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.entity.Dailydb;

import java.util.Optional;

public interface SpringDataJpadailydbRepository extends JpaRepository<Dailydb, Long>, DailydbRepository {
    @Override
    Optional<Dailydb> findBymemberId(Long memberId);
    @Override
    Optional<Dailydb> findBychatRoom(ChatRoom chatRoom);
    @Override
    Optional<Dailydb> findByfrequently(Integer frequently);
    @Override
    Optional<Dailydb> findBykeyword(String keyword);
    @Override
    Optional<Dailydb> findBychatTimes(Integer chatTimes);
    @Override
    Optional<Dailydb> findBytotalMessage(Integer totalMessage);
}
