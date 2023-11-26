package project.kakaochatanalyzer.Detail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.entity.Dailydb;
import project.kakaochatanalyzer.Detail.repository.DailydbRepository;
import project.kakaochatanalyzer.Login.entity.Member;

import java.util.List;

@Transactional
public class DailydbService {
    private final DailydbRepository userRepository;
    @Autowired
    public DailydbService(DailydbRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<Dailydb> getAllUsers() {
        return userRepository.findAll();
    }
    public List<Dailydb> getAllEntities() {
        return userRepository.findAll();
    }


    //형진씨 Task 2
    public int saveProcessedData(Dailydb processed_dailydb, Member member, ChatRoom chatRoom) {
        // Dailydb에 processed_dailydb 데이터를 저장해줘(save)
        // MemberId랑 chatRoom은 저기서 받아서 저장.

        return 0;
    }
}