package project.kakaochatanalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/your-entities")
public class DailydbController {

    @Autowired
    private YourEntityService service;

    //디테일 페이지
    @GetMapping("detail")
    public ResponseEntity<List<YourEntity>> getAllEntities() {
        List<YourEntity> entities = service.getAllEntities();
        return ResponseEntity.ok(entities);
    }

    // Other methods as needed
}