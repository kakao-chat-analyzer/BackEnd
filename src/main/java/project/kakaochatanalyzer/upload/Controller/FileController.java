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
import project.kakaochatanalyzer.Detail.entity.dailydb;
import project.kakaochatanalyzer.Detail.repository.ChatRoomRepository;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api")
public class FileController {

    private final String fastApiUrl = "http://your-fastapi-url:port/endpoint"; // Replace with your FastAPI URL

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ChatRoomRepository DailydbRepository;
    @Autowired
    private ChatRoom dailydb;

    @PostMapping("/sendTextToFastAPI")
    public ResponseEntity<String> sendTextToFastAPI(@RequestBody String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(text, headers);

        // Send POST request to FastAPI
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(fastApiUrl, requestEntity, String.class);

        // Extract processed data from the FastAPI response
        String processedData = responseEntity.getBody();

        // Increment roomNumber
        Long incrementedRoomNumber = incrementRoomNumber();

        // Save processed data into MySQL using the dailydb entity
        saveProcessedData(processedData);

        return ResponseEntity.ok(processedData);
    }

    private Long incrementRoomNumber() {
        // Get the latest roomNumber from the database and increment it
        Long latestRoomNumber = ChatRoomRepository.findMaxRoomNumber();
        return (latestRoomNumber != null) ? latestRoomNumber + 1 : 1L;
    }

    private void saveProcessedData(String processedData) {
        // Parse the processed data and extract relevant information
        // Create a dailydb entity and set its attributes
        dailydb dailyDbEntity = new dailydb();
        ChatRoom chatRoomEntity = new ChatRoom();
        chatRoomEntity.setRoomNumber(incrementRoomNumber());
        // Set attributes based on the processed data
        // Example: dailyDbEntity.setMemberId(memberId);
        // Example: dailyDbEntity.setChatRoom(chatRoom);
        dailyDbEntity.setId(dailyDbEntity.getId());
        dailyDbEntity.setMemberId(dailyDbEntity.getMemberId());
        dailyDbEntity.setChatRoom(dailyDbEntity.getChatRoom());
        dailyDbEntity.setFrequently(dailyDbEntity.getFrequently());
        dailyDbEntity.setKeyword(dailyDbEntity.getKeyword());
        dailyDbEntity.setChatTimes(dailyDbEntity.getChatTimes());
        dailyDbEntity.setTotalMessage(dailyDbEntity.getTotalMessage());

        // Save the entity to the database
        DailydbRepository.save(dailyDbEntity);
        ChatRoomRepository.save(chatRoomEntity);
    }
}