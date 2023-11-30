package project.kakaochatanalyzer.Detail.Controller;

<<<<<<< HEAD
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.service.ChatRoomService;
=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.repository.ChatRoomRepository;
import project.kakaochatanalyzer.Login.entity.Member;

import java.util.Optional;
>>>>>>> a1a0c5bea3f9b243d41543e2da537f8848930a11
=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> parent of a1a0c5b (Main Page Test)

import java.util.Optional;

@RestController
public class MainController {
<<<<<<< HEAD
<<<<<<< HEAD
    private final ChatRoomService chatRoomService;

    public MainController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @GetMapping("/main")
    public ResponseEntity<ChatRoom> getData(@RequestParam("memberId") String memberId,
                                              @RequestParam("roomNumber") Long roomNumber) {
        Optional<ChatRoom> result = ChatRoomService.getDataByMemberIdAndRoomNumber(memberId, roomNumber);
=======
    private ChatRoomRepository chatRoomRepository;

    @GetMapping("/main")
    public ResponseEntity<ChatRoom> getData(@RequestParam("memberId") Member memberId,
                                            @RequestParam("roomNumber") Long roomNumber) {
        Optional<ChatRoom> result = chatRoomRepository.findByMemberIdAndRoomNumber(memberId, roomNumber);
>>>>>>> a1a0c5bea3f9b243d41543e2da537f8848930a11

        return result.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
=======
    @GetMapping("/main")
    public String mainPage() {
        return "main"; // Refers to a Thymeleaf template named main.html
>>>>>>> parent of a1a0c5b (Main Page Test)
    }
}
