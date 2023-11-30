package project.kakaochatanalyzer.Detail.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.repository.ChatRoomRepository;
import project.kakaochatanalyzer.Login.entity.Member;

import java.util.Optional;

@Controller
public class MainController {
    private ChatRoomRepository chatRoomRepository;

    @GetMapping("/main")
    public ResponseEntity<ChatRoom> getData(@RequestParam("memberId") Member memberId,
                                            @RequestParam("roomNumber") Long roomNumber) {
        Optional<ChatRoom> result = chatRoomRepository.findByMemberIdAndRoomNumber(memberId, roomNumber);

        return result.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}