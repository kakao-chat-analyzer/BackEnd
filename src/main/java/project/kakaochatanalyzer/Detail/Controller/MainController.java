package project.kakaochatanalyzer.Detail.Controller;

<<<<<<< HEAD
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.repository.ChatRoomRepository;
import project.kakaochatanalyzer.Login.entity.Member;

import java.util.Optional;
<<<<<<< HEAD
>>>>>>> a1a0c5bea3f9b243d41543e2da537f8848930a11
=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> parent of a1a0c5b (Main Page Test)
=======
>>>>>>> 45a77cb27633e0795b02fd978b56d14f734404bc

@Controller
public class MainController {
<<<<<<< HEAD
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
=======
>>>>>>> 45a77cb27633e0795b02fd978b56d14f734404bc
    private ChatRoomRepository chatRoomRepository;

    @GetMapping("/main")
    public ResponseEntity<ChatRoom> getData(@RequestParam("memberId") Member memberId,
                                            @RequestParam("roomNumber") Long roomNumber) {
        Optional<ChatRoom> result = chatRoomRepository.findByMemberIdAndRoomNumber(memberId, roomNumber);

        return result.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
=======
    @GetMapping("/main")
    public String mainPage() {
        return "main"; // Refers to a Thymeleaf template named main.html
>>>>>>> parent of a1a0c5b (Main Page Test)
    }
}