package project.kakaochatanalyzer.Detail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.entity.Dailydb;
import project.kakaochatanalyzer.Detail.repository.ChatRoomRepository;
import project.kakaochatanalyzer.Detail.repository.DailydbRepository;
import project.kakaochatanalyzer.Login.entity.Member;
import project.kakaochatanalyzer.Login.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class DailydbService {
    private final DailydbRepository userRepository;
    private ChatRoomRepository chatRoomRepository;
    private MemberRepository memberRepository;

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
        // Dailydb에 processed_dailydb 데이터를 저장
        processed_dailydb.setMember(member);
        processed_dailydb.setChatRoom(chatRoom);
        userRepository.save(processed_dailydb);

        // Member 정보를 저장

        Member savedMember = memberRepository.save(new Member());
        Optional<Member> foundMember = memberRepository.findByUserId("someUserId");

        // ChatRoom 정보를 저장
        chatRoomRepository.save(chatRoom);

        // 저장된 Dailydb 엔티티의 ID 반환
        return processed_dailydb.getId().intValue();
    }
}