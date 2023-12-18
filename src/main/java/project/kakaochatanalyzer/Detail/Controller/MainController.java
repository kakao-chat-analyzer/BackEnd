package project.kakaochatanalyzer.Detail.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.kakaochatanalyzer.Detail.entity.Dailydb;
import project.kakaochatanalyzer.Detail.service.ChatRoomService;
import project.kakaochatanalyzer.Detail.service.DailydbService;
import project.kakaochatanalyzer.Login.entity.Member;
import java.util.*;
@RestController
@RequestMapping("/api")
public class MainController {
    private final ChatRoomService chatRoomService;
    @Autowired
    private DailydbService dailydbService;
    public MainController(ChatRoomService chatRoomService) {this.chatRoomService = chatRoomService;}
    @GetMapping("/chatroom")
    public ResponseEntity<Map<String, Object>> getUserInfo(HttpSession session) {
        //// DUMMY ////
//        Member members = new Member();
//        members.setId(13L);
//        members.setUserId("123124");
//        members.setUserName("GOGOGO");
//        members.setUserPw("1234442244");
//        members.setUserEmail("123@example.com");
//        session.setAttribute("loggedInUser", members);
        //// DUMMY ////

        Map<String, Object> userInfo =  new HashMap<>();
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        List<Long> roomNumber = chatRoomService.findIdByMemberId(loggedInUser);

        userInfo.put("chatroomNum", roomNumber);

        return ResponseEntity.ok(userInfo);
    }
    @GetMapping("/detail")
    public ResponseEntity<List<ShuffleConversation>> getRandomConversations(HttpSession session,
                                                @RequestParam("chatroomNum") Long chatroomNum) {
        System.out.println("안녕");
        //// DUMMY ////
//        Member members = new Member();
//        members.setId(13L);
//        members.setUserId("123124");
//        members.setUserName("GOGOGO");
//        members.setUserPw("1234442244");
//        members.setUserEmail("123@example.com");
//        session.setAttribute("loggedInUser", members);
        //// DUMMY ////
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        Long memberId = loggedInUser.getId();
        // chatroom에 해당되는 채팅방 추출
        List<Dailydb> allConversations = dailydbService.findByMemberIdAndChatRoomId(memberId, chatroomNum);
        // 채팅방 채팅 횟수 거르기
        List<Dailydb> extractConversations = extractChat(allConversations);
        // 랜덤하게 4개의 채팅방 및 대화 추출
        List<ShuffleConversation> randomConversations = randomConv(extractConversations);
        System.out.println("3");
        System.out.println(randomConversations);
        return ResponseEntity.ok(randomConversations);
    }
    public List<Dailydb> extractChat(List<Dailydb> allConversation){
        // 채팅 수 15개 이상의 채팅방만 추출하기
        List<Dailydb> newConversation = new ArrayList<>();

        for (Dailydb conversation : allConversation) {
            int chatTime = conversation.getChatTimes();
            // 'chatTime'이 15 이상인 대화만 선택
            if (chatTime >= 15) {
                newConversation.add(conversation);
            }
        }
        return newConversation;
    }
    public List<ShuffleConversation> randomConv(List<Dailydb> extractConversations){
        //데이터 랜덤 셔플
        Collections.shuffle(extractConversations);
        // 앞 4개 채팅방 요소만 뽑기
        System.out.println("asdasd");
        System.out.println(extractConversations);
        System.out.println("Index Error");
        List<Dailydb> shuffleConversation;
        try{
            shuffleConversation = extractConversations.subList(0,4);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Index Error");
            shuffleConversation = extractConversations.subList(0,2);
        }

        // 채팅방의 대화를 랜덤하게 추출
        List<ShuffleConversation> shuffleResult = randomMessage(shuffleConversation);
        return shuffleResult;
    }
    public List<ShuffleConversation> randomMessage(List<Dailydb> shuffleConv){
        List<ShuffleConversation> totalConversation = new ArrayList<>();
        for(Dailydb conversation : shuffleConv) {
            Random random = new Random();
            ShuffleConversation shuf = new ShuffleConversation();
            int num = conversation.getDailyUser().size(); // user 최대 횟수 저장
            int idx = random.nextInt(num); //랜덤한 수 뽑기
            // 랜덤한 수로 부터 10개의 대화 뽑기
            try{
                List<String> resultMessage = getRange(conversation.getDailyMessages(), idx);
                List<String> resultUser = getRange(conversation.getDailyUser(), idx);
                // 뽑은 데이터 저장
                shuf.setDate(conversation.getDate());
                shuf.setShuffleMessage(resultMessage);
                shuf.setShuffleUser(resultUser);
                totalConversation.add(shuf);
            } catch (IndexOutOfBoundsException e){
                System.out.println("Index Error");
            }
        }
        return totalConversation;
    }
    public List<String> getRange(List<String> temp, int size){
        //size~ size+8 idx 리스트 추출
        List<String> listTemp = new ArrayList<>();
        for (int i = size; i<size+8; i++){
            listTemp.add(temp.get(i));
        }
        return listTemp;
    }


}


