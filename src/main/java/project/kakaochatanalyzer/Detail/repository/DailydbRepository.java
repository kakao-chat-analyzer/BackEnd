package project.kakaochatanalyzer.Detail.repository;

import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.entity.Dailydb;
import java.util.List;
import java.util.Optional;

public interface DailydbRepository{
    Dailydb save(Dailydb dailydb);
    Optional<Dailydb> findById(Long Id);
    Optional<Dailydb> findBymemberId(Long memberId);
    Optional<Dailydb> findBychatRoom(ChatRoom chatRoom);
    Optional<Dailydb> findByfrequently(Integer frequently);
    Optional<Dailydb> findBykeyword(String keyword);
    Optional<Dailydb> findBychatTimes(Integer chatTimes);
    Optional<Dailydb> findBytotalMessage(String totalMessage);
    List<Dailydb> findAll();
}