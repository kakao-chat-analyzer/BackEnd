package project.kakaochatanalyzer.Detail.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.service.ChatRoomService;

import java.util.Optional;

@RestController
public class MainController {
    private final ChatRoomService chatRoomService;

    public MainController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @GetMapping("/main")
    public ResponseEntity<ChatRoom> getData(@RequestParam("memberId") String memberId,
                                              @RequestParam("roomNumber") Long roomNumber) {
        Optional<ChatRoom> result = ChatRoomService.getDataByMemberIdAndRoomNumber(memberId, roomNumber);

        return result.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
