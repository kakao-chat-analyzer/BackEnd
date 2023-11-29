package project.kakaochatanalyzer.Detail.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.kakaochatanalyzer.Detail.entity.Dailydb;
import project.kakaochatanalyzer.Detail.service.DailydbService;
import project.kakaochatanalyzer.Login.entity.Member;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DailydbController {

    private DailydbService dailydbService;

    public DailydbController(DailydbService dailydbService) {
        this.dailydbService = dailydbService;
    }

    //디테일 페이지
    @GetMapping("/detail")
    public ResponseEntity<Optional<Dailydb>> getDailyDb(HttpSession session,
                                                        @RequestParam("date") LocalDate date,
                                                        @RequestParam("chatroomId") Long chatroomId) {
        //"/getDailyDb?date=2023-01-01&chatroomNum=123" 이 url으로 보내진다.
        // session 가져오기
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        //멤버 ID랑, chatroomNum와 date에 해당하는 값을 dailyDb에서 가져온다.
        //가져온 데이터를 return해준다.
        //밑에서 부터 작성하면 된다.

        //멤버Id, chatroomId, date) 가져오기
        Optional<Dailydb> entities = dailydbService.getDailyData(loggedInUser.getId(), chatroomId, date);
        return ResponseEntity.ok(entities);
    }
    // Other methods as needed
}