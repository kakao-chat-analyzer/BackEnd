package project.kakaochatanalyzer.Detail.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.entity.Dailydb;
import project.kakaochatanalyzer.Detail.service.ChatRoomService;
import project.kakaochatanalyzer.Detail.service.DailydbService;
import project.kakaochatanalyzer.Login.entity.Member;
import project.kakaochatanalyzer.Login.repository.MemberRepository;
import project.kakaochatanalyzer.Login.service.MemberService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
    private MemberRepository memberRepository;
    private DailydbService dailydbService;

    public MainController(ChatRoomService chatRoomService) {this.chatRoomService = chatRoomService;}


//    public ResponseEntity<ChatRoom> getAllChatRooms(HttpSession session) {
//        ChatRoom loggedInUser= (ChatRoom) session.getAttribute("loggedInUser");
//        List<ChatRoom> chatRooms = chatRoomService.getAllChatRooms();
//        return ResponseEntity.ok(loggedInUser);
//    }
    @GetMapping("/chatroom")
    public ResponseEntity<Map<String, Object>> getUserInfo(HttpSession session) {
        //// DUMMY ////
        Member members = new Member();
        members.setId(13L);
        members.setUserId("123124");
        members.setUserName("GOGOGO");
        members.setUserPw("1234442244");
        members.setUserEmail("123@example.com");
        session.setAttribute("loggedInUser", members);
        //// DUMMY ////
        Map<String, Object> userInfo =  new HashMap<>();
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        System.out.println("asdaasd");
        System.out.println(loggedInUser);
        // Get roomNumber and userName based on userId
        List<Long> roomNumber = chatRoomService.getRoomNumberByUserId(loggedInUser);
        System.out.println(roomNumber);
        //String userName = chatRoomService.getUserNameByUserId(userId);
        // Add the information to the response map
        userInfo.put("roomNumber", roomNumber);

        return ResponseEntity.ok(userInfo);
    }
    @GetMapping("/detail")
    public List<Dailydb> getRandomConversations(HttpSession session,
                                                @RequestParam("chatroomNum") Long chatroomNum) {

        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        Long memberId = loggedInUser.getId();

        // Step 1: Retrieve total message from Dailydb
        List<Dailydb> allConversations = dailydbService.findByChatRoomIdAndMemberId(memberId, chatroomNum);

        // Step 2: Randomly select 4 to 5 days from all dates
        List<LocalDate> selectedDates = getRandomDates(allConversations, 4, 5);

        // Step 3: Filter conversations exceeding an appropriate number for each selected day
        List<Dailydb> filteredConversations = filterConversations(allConversations, selectedDates);

        // Step 4: Randomly select a portion of conversations for each of the five days
        List<Dailydb> finalRandomConversations = getRandomPortion(filteredConversations);

        return finalRandomConversations;
    }

    private List<LocalDate> getRandomDates(List<Dailydb> allConversations, int minDays, int maxDays) {
        // Extract all dates from Dailydb entities
        List<LocalDate> allDates = new ArrayList<>();
        for (Dailydb conversation : allConversations) {
            LocalDate date = conversation.getDate(); // Assuming 'date' is a field in Dailydb
            allDates.add(date);
        }

        // Ensure uniqueness of dates using a Set
        Set<LocalDate> uniqueDates = new HashSet<>(allDates);

        // Shuffle the unique dates to introduce randomness
        List<LocalDate> shuffledDates = new ArrayList<>(uniqueDates);
        Collections.shuffle(shuffledDates);

        // Determine the number of dates to select (between minDays and maxDays)
        int numDatesToSelect = Math.min(maxDays, Math.max(minDays, shuffledDates.size()));

        // Return the randomly selected subset of dates
        return shuffledDates.subList(0, numDatesToSelect);
    }

    private List<Dailydb> filterConversations(List<Dailydb> allConversations, List<LocalDate> selectedDates) {
        // Assuming there is a field named 'numConversations' in Dailydb representing the number of conversations

        // Define a threshold for the number of conversations
        int conversationThreshold = 10; // Adjust according to your requirement

        // Filter conversations based on selected dates and the conversation threshold
        return allConversations.stream()
                .filter(conversation -> selectedDates.contains(conversation.getDate()))
                .filter(conversation -> conversation.getNumConversations() > conversationThreshold)
                .collect(Collectors.toList());
    }

    private List<Dailydb> getRandomPortion(List<Dailydb> conversations) {
        // Assuming you want to select, for example, 50% of conversations randomly

        // Determine the size of the subset to select (50% in this example)
        int subsetSize = Math.max(1, conversations.size() / 2);

        // Shuffle the conversations to introduce randomness
        Collections.shuffle(conversations);

        // Return the randomly selected subset of conversations
        return conversations.subList(0, subsetSize);
    }
}


