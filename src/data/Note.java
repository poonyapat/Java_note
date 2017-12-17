package data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note implements Serializable {
    private LocalDateTime createdDate, lastUpdate;
    private String subject, information;

    public Note(String subject, String information) {
        this.subject = subject;
        this.information = information;
        createdDate = LocalDateTime.now();
        lastUpdate = createdDate;
    }

    public String getCreatedDate() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm").format(createdDate);
    }

    public String getLastUpdate() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm").format(lastUpdate);
    }

    public String getSubject() {
        return subject;
    }

    public String getInformation() {
        return information;
    }

    void edit(String subject, String information){
        this.subject = subject;
        this.information = information;
        lastUpdate = LocalDateTime.now();
    }
}
