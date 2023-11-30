package project.kakaochatanalyzer.Detail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.entity.Dailydb;
import project.kakaochatanalyzer.Detail.repository.ChatRoomRepository;
import project.kakaochatanalyzer.Detail.repository.DailydbRepository;
import project.kakaochatanalyzer.Login.entity.Member;
import project.kakaochatanalyzer.Login.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
public class DailydbService {
    private DailydbRepository dailyDbRepository;
    private ChatRoomRepository chatRoomRepository;
    private MemberRepository memberRepository;

    @Autowired
    public DailydbService(DailydbRepository dailyDbRepository) {
        this.dailyDbRepository = dailyDbRepository;
    }
//    public List<Dailydb> getAllUsers() {return dailyDbRepository.findAll();}
    public List<Dailydb> getAllEntities() {
        return dailyDbRepository.findAll();
    }



    //형진씨 Task 2
    public int saveProcessedData(List<Dailydb> processed_dailydb, Member member, ChatRoom chatRoom) {
        // Dailydb에 processed_dailydb 데이터를 저장
        // data 확인
        System.out.println("여기까지 왔따아아아");
        System.out.println(processed_dailydb.get(0).getFrequently()); //
        System.out.println(processed_dailydb.get(0).getKeyword());
        System.out.println(processed_dailydb.get(0).getChatTimes());
        System.out.println(processed_dailydb.get(0).getTotalMessage());
//        System.out.println(processed_dailydb.get(0).);
        System.out.println(processed_dailydb.get(0).getDate());

        processed_dailydb.forEach(dailydb -> {
            dailydb.setMember(member);
            dailydb.setChatRoom(chatRoom);
            //DB 저장
            dailyDbRepository.save(dailydb);
        });

//        public void updateKeywords(String userId, Long chatroomNum, LocalDate date, List<String> keywords) {
//            // Find the Dailydb entity based on user ID, chat room number, and date
//            Optional<Dailydb> optionalDailydb = dailyDbRepository.findByMemberIdAndChatRoomAndDate(userId, chatroomNum, date);
//
//            optionalDailydb.orElse(dailydb -> {
//                // Update the keywords in the found Dailydb entity
//                dailydb.setKeywords(keywords);
//                // Save the updated entity back to the database
//                dailyDbRepository.save(dailydb);
//            });
//        }

        return 1;
    }
}