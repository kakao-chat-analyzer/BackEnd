package project.kakaochatanalyzer.Detail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.entity.dailydb;

import java.util.Optional;

public interface SpringDataJpadailydbRepository extends JpaRepository<dailydb, Long>, DailydbRepository {
    @Override
    Optional<dailydb> findBymemberId(Long memberId);
    @Override
    Optional<dailydb> findBychatRoom(ChatRoom chatRoom);
    @Override
    Optional<dailydb> findByfrequently(Integer frequently);
    @Override
    Optional<dailydb> findBykeyword(String keyword);
    @Override
    Optional<dailydb> findBychatTimes(Integer chatTimes);
    @Override
    Optional<dailydb> findBytotalMessage(Integer totalMessage);
}
