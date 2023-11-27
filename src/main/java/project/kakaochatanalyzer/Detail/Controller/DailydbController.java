package project.kakaochatanalyzer.Detail.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.kakaochatanalyzer.Detail.entity.Dailydb;
import project.kakaochatanalyzer.Detail.service.DailydbService;
import project.kakaochatanalyzer.Login.entity.Member;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DailydbController {

    private DailydbService dailydbService;

    public DailydbController(DailydbService dailydbService) {
        this.dailydbService = dailydbService;
    }

    //디테일 페이지
    @GetMapping("/detail")
    public ResponseEntity<List<Dailydb>> getDailyDb(HttpSession session) {
        // session 가져오기
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        //멤버 ID별로 dailyDb
        List<Dailydb> entities = dailydbService.getAllEntities();
        return ResponseEntity.ok(entities);
    }
    // Other methods as needed
}