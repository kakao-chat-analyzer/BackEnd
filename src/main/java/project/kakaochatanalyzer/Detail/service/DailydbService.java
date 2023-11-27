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
    private DailydbRepository dailyDbRepository;
    private ChatRoomRepository chatRoomRepository;
    private MemberRepository memberRepository;

    @Autowired
    public DailydbService(DailydbRepository dailyDbRepository) {
        this.dailyDbRepository = dailyDbRepository;
    }
    public List<Dailydb> getAllUsers() {
        return dailyDbRepository.findAll();
    }
    public List<Dailydb> getAllEntities() {
        return dailyDbRepository.findAll();
    }

    //형진씨 Task 2
    public int saveProcessedData(List<Dailydb> processed_dailydb, Member member, ChatRoom chatRoom) {
        // Dailydb에 processed_dailydb 데이터를 저장
        //data 가져오기
        //data 확인
        System.out.println("여기까지 왔따아아아");
        System.out.println(processed_dailydb.get(0).getFrequently()); //
        System.out.println(processed_dailydb.get(0).getKeyword());
        System.out.println(processed_dailydb.get(0).getChatTimes());
        System.out.println(processed_dailydb.get(0).getTotalMessage());
        System.out.println(processed_dailydb.get(0).getDailyMessages());
        System.out.println(processed_dailydb.get(0).getDate());


        processed_dailydb.forEach(dailydb -> {
            dailydb.setMember(member);
            dailydb.setChatRoom(chatRoom);
            //DB 저장
            dailyDbRepository.save(dailydb);
        });

//        for (Dailydb dailydb: processed_dailydb){
            //data 객체에 넣기
//            Dailydb newDailyDb = new Dailydb(member, chatRoom);

//            dailyDbRepository.save(dailydb);
//        }

        // 저장된 Dailydb 엔티티의 ID 반환
        return 1;
    }
}
//            newDailyDb.setFrequently(dailydb.getFrequently());
//            newDailyDb.setKeyword(dailydb.getKeyword());
//            newDailyDb.setChatTimes(dailydb.getChatTimes());
//            newDailyDb.setTotalMessage(dailydb.getTotalMessage());