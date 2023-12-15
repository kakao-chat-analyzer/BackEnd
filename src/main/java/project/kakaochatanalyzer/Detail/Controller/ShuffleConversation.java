package project.kakaochatanalyzer.Detail.Controller;

import java.time.LocalDate;
import java.util.List;

public class ShuffleConversation {
    private List<String> shuffleMessage;
    private List<String> shuffleUser;
    private LocalDate date;


    public List<String> getShuffleMessage() {
        return shuffleMessage;
    }

    public void setShuffleMessage(List<String> shuffleMessage) {
        this.shuffleMessage = shuffleMessage;
    }

    public List<String> getShuffleUser() {
        return shuffleUser;
    }

    public void setShuffleUser(List<String> shuffleUser) {
        this.shuffleUser = shuffleUser;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
