package project.kakaochatanalyzer.Detail.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.kakaochatanalyzer.Detail.entity.dailydb;
import project.kakaochatanalyzer.Detail.service.ChatRoomService;
import project.kakaochatanalyzer.Detail.service.DailydbService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DailydbController {

    private DailydbService service;
    private ChatRoomService chatservice;

    public DailydbController(DailydbService service, ChatRoomService chatservice) {
        this.service = service;
        this.chatservice = chatservice;
    }

    //디테일 페이지
    @GetMapping("/detail")
    public ResponseEntity<List<dailydb>> getAllEntities() {
        List<dailydb> entities = service.getAllEntities();
        return ResponseEntity.ok(entities);
    }
    // Other methods as needed
}