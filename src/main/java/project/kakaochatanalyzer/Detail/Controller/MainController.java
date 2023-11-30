import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.service.ChatRoomService;
import project.kakaochatanalyzer.Login.entity.Member;

import java.util.Optional;

@RestController
public class MainController {

    private final ChatRoomService yourEntityService;

    // constructor with dependency injection
    public MainController(ChatRoomService yourEntityService) {
        this.yourEntityService = yourEntityService;
    }

    @GetMapping("/main")
    public ResponseEntity<ChatRoom> getData(@RequestParam("memberId") Member memberId,
                                            @RequestParam("roomNumber") Long roomNumber) {
        Optional<ChatRoom> result = yourEntityService.getDataByMemberIdAndRoomNumber(memberId, roomNumber);

        return result.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}