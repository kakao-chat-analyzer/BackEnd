package project.kakaochatanalyzer.Detail.repository;

import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.entity.Dailydb;
import project.kakaochatanalyzer.Login.entity.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailydbRepository{
    Dailydb save(Dailydb dailydb);
    Optional<Dailydb> findById(Long Id);
    Optional<Dailydb> findBymemberId(Long memberId);
    Optional<Dailydb> findBychatRoom(ChatRoom chatRoom);
//    Optional<Dailydb> findBykeyword(String keyword);
    Optional<Dailydb> findBychatTimes(Integer chatTimes);
    Optional<Dailydb> findBytotalMessage(String totalMessage);
    List<Dailydb> findAll();
    Optional<Dailydb> findByDateAndChatRoomIdAndMemberId(LocalDate date, Long chatroomId, Long memberId);
}