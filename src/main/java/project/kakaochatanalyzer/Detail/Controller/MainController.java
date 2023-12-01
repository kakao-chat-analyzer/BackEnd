package project.kakaochatanalyzer.Detail.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.service.ChatRoomService;
import project.kakaochatanalyzer.Login.entity.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@RestController
//@RequestMapping("/api")
//public class MainController {
//
//    private final ChatRoomService chatRoomService;
//
//    // constructor with dependency injection
//    public MainController(ChatRoomService chatRoomService) {
//        this.chatRoomService = chatRoomService;
//    }
//
//    @GetMapping("")
//    public ResponseEntity<ChatRoom> getData(@RequestParam("memberId") Member memberId,
//                                            @RequestParam("roomNumber") Long roomNumber) {
//        Optional<ChatRoom> result = chatRoomService.getDataByMemberIdAndRoomNumber(memberId, roomNumber);
//
//        return result.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//}
@RestController
@RequestMapping("/api")
public class MainController {

    private final ChatRoomService chatRoomService;

    public MainController(ChatRoomService chatRoomService) {this.chatRoomService = chatRoomService;}


//    public ResponseEntity<ChatRoom> getAllChatRooms(HttpSession session) {
//        ChatRoom loggedInUser= (ChatRoom) session.getAttribute("loggedInUser");
//        List<ChatRoom> chatRooms = chatRoomService.getAllChatRooms();
//        return ResponseEntity.ok(loggedInUser);
//    }
    @GetMapping("/chatroom")
    public ResponseEntity<Map<String, Object>> getUserInfo(@RequestParam("userId") Member userId) {
        Map<String, Object> userInfo =  new HashMap<>();

        // Get roomNumber and userName based on userId
        Optional<Long> roomNumber = chatRoomService.getRoomNumberByUserId(userId);
        //String userName = chatRoomService.getUserNameByUserId(userId);

        // Add the information to the response map
        userInfo.put("userId", userId);
        userInfo.put("roomNumber", roomNumber);
        //userInfo.put("userName", userName);

        return ResponseEntity.ok(userInfo);
    }
}


