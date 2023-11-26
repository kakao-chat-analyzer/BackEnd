package project.kakaochatanalyzer.Detail.repository;

import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.entity.dailydb;

import java.util.List;
import java.util.Optional;

public interface DailydbRepository{


    dailydb save(dailydb dailydb);
    Optional<dailydb> findById(Long Id);
    Optional<dailydb> findBymemberId(Long memberId);
    Optional<dailydb> findBychatRoom(ChatRoom chatRoom);
    Optional<dailydb> findByfrequently(Integer frequently);
    Optional<dailydb> findBykeyword(String keyword);
    Optional<dailydb> findBychatTimes(Integer chatTimes);
    Optional<dailydb> findBytotalMessage(Integer totalMessage);
    List<dailydb> findAll();
}