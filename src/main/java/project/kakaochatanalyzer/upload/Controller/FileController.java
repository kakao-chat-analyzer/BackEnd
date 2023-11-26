package project.kakaochatanalyzer.upload.Controller;

import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import project.kakaochatanalyzer.Detail.entity.ChatRoom;
import project.kakaochatanalyzer.Detail.repository.ChatRoomRepository;
import project.kakaochatanalyzer.Detail.repository.DailydbRepository;
import project.kakaochatanalyzer.Detail.service.ChatRoomService;
import project.kakaochatanalyzer.Detail.service.DailydbService;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api")
public class FileController {

    private final String fastApiUrl = "http://your-fastapi-url:port/endpoint";
    private RestTemplate restTemplate;

    private ChatRoomService chatRoomService;
    private DailydbService dailydbService;

    @PostMapping("/file")
    public ResponseEntity<String> sendTextToFastAPI(@RequestBody String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(text, headers);

        // Send POST request to FastAPI
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(fastApiUrl, requestEntity, String.class);

        // Extract processed data from the FastAPI response
        String processedData = responseEntity.getBody();

        //processedData 받아온 값으로 변환해야한다..


        //DB 저장
        //chatRoomService.saveChatRoom(Member member);
        //dailydbService.saveProcessedData(Dailydb dailydb, Member member, ChatRoom chatRoom);

        return ResponseEntity.ok(processedData);
    }
}