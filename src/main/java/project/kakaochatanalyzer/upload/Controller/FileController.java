package project.kakaochatanalyzer.upload.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.entity.Dailydb;
import project.kakaochatanalyzer.Detail.service.ChatRoomService;
import project.kakaochatanalyzer.Detail.service.DailydbService;
import project.kakaochatanalyzer.Login.entity.Member;
import project.kakaochatanalyzer.Login.repository.MemberRepository;
import project.kakaochatanalyzer.Login.service.MemberService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FileController {


    private RestTemplate restTemplate;

    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private DailydbService dailydbService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @PostMapping("/file")
    public int sendTextToFastAPI(@RequestParam("file") MultipartFile file, HttpSession session) {
        //// DUMMY ////
        Member members = new Member();
        members.setUserId("123124");
        members.setUserName("GOGOGO");
        members.setUserPw("1234442244");
        members.setUserEmail("123@example.com");
        session.setAttribute("loggedInUser", members);
        //// DUMMY ////


        //ResponseEntity<Dailydb>

        String URL = "http://localhost:8000/api/file";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        //파일 fastapi로 post로 보내기
        ResponseEntity<List<Dailydb>> responseEntity = restTemplate.exchange(
                URL,
                HttpMethod.POST,
                requestEntity,
//                Dailydb.class  //Dailydb class로 변환
                new ParameterizedTypeReference<List<Dailydb>>() {
                }
        );
        System.out.println("asfasfasfaf");
        //fastapi의 response 받기
        List<Dailydb> processedData = responseEntity.getBody();
        System.out.println("asdasF?AF?ASF");
        //session 정보
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        String userId = loggedInUser.getUserId();
        Optional<Member> userData = memberRepository.findByuserId(userId); //Optional은 NPE(NullPointerException)를 방지
        Member member = userData.orElse(null);
        //DB 저장
        ChatRoom newChatRoom = chatRoomService.saveChatRoom(member);
        dailydbService.saveProcessedData(processedData, member, newChatRoom);


//      return ResponseEntity.ok(processedData);
        return 200;
    }

    @PostMapping("/keyword")
    public int sendTextToFastAPI(HttpSession session,
                                 @RequestParam("date") LocalDate date,
                                 @RequestParam("chatroomNum") Long chatroomNum) {
        //키워드 추출 할 때 누름..
        //위에 fastapi에 보내는 방식을 그대로 활용해서
        // post로 데이터를 보내서 받아온 값을 반환 및 db업데이트를 한다.

        //1. 지금 현재 dailydb에 keyword칸이 비어있다. -> 이것을 모델을 돌려서 결과로 채워 넣는 것이다.
        //2. session으로 memberid가 나오고 (세션을 사용하는 예제 바로 위에 있다.)
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        String userId = loggedInUser.getUserId();
        //3. dailydbController에서의 get랑 논리가 비슷한데 위의 date,chatroonNum,session 정보를 활용해서
        //4. dailyDb에서 해당 데이터의 totalmessage를 뽑아낸다.
        Optional<Dailydb> dailyData = dailydbService.getDailyData(loggedInUser.getId(), chatroomNum, date);

        //5. 이 totalmessage를 fastapi로 post로 보내는 것이다.
        if (dailyData.isPresent()) {
            // 검색된 Dailydb에서 totalMessage 추출
            String totalMessage = dailyData.get().getTotalMessage();

            // totalMessage를 FastAPI로 보내고 키워드 수신
            String URL = "http://localhost:8000/api/keyword";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("totalMessage", totalMessage);

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

            List<String> keywords = getKeywordsFromFastAPI(URL, restTemplate, requestEntity);

            // 키워드를 데이터베이스에 업데이트
            // dailydbService.updateKeywords(userId, chatroomNum, date, keywords);
            //dailydbService.saveProcessedData(processedData, member, newChatRoom);

            // 성공 상태 코드 1 반환
            return 1;
        } else {
            // 데이터가 없을 경우 실패 상태 코드 0 반환
            return 0;
        }
    }

    private List<String> getKeywordsFromFastAPI(String URL, RestTemplate restTemplate,
                                                HttpEntity<Map<String, Object>> requestEntity) {
        ResponseEntity<List<String>> responseEntity = restTemplate.exchange(
                URL,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<String>>() {
                }
        );

        //6. 그럼 보내고 받아온 데이터는 키워드가 될 것인데
        //7. 그 db에서 조회한 그 부분의 keyword에 채워 넣어야 한다  (db의 비어있는 keyword 채워넣기)
        //8. 끝

              return responseEntity.getBody();


       // return 1;
    }

}