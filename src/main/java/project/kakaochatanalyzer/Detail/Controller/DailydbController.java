package project.kakaochatanalyzer.Detail.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.kakaochatanalyzer.Detail.entity.Dailydb;
import project.kakaochatanalyzer.Detail.service.DailydbService;
import project.kakaochatanalyzer.Login.entity.Member;
import project.kakaochatanalyzer.Login.repository.MemberRepository;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DailydbController {

    @Autowired
    private DailydbService dailydbService;
    @Autowired
    private MemberRepository memberRepository;

    public DailydbController(DailydbService dailydbService) {
        this.dailydbService = dailydbService;
    }

    //디테일 페이지`
    @GetMapping("/analysis")
    public ResponseEntity<Optional<Dailydb>> getDailyDb(HttpSession session,
                                                        @RequestParam("date") LocalDate date,
                                                        @RequestParam("chatroomNum") Long chatRoomId) {
        //// DUMMY ////
        Member members = new Member();
        members.setId(13L);
        members.setUserId("123124");
        members.setUserName("GOGOGO");
        members.setUserPw("1234442244");
        members.setUserEmail("123@example.com");
        session.setAttribute("loggedInUser", members);
        //// DUMMY ////

        //"/getDailyDb?date=2023-01-01&chatroomNum=123" 이 url으로 보내진다.
        // session 가져오기
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        String userId = loggedInUser.getUserId();
        Optional<Member> realUser = memberRepository.findByuserId(userId);
        Long realId = realUser.get().getId();

        Optional<Dailydb> dailydbOptional = dailydbService.findByDateAndChatRoomIdAndMemberId(date, chatRoomId, realId);

//        List<Dailydb> entities = dailydbService.getAllEntities();
        return ResponseEntity.ok(dailydbOptional);
    }
    // Other methods as needed
}