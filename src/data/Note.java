package data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note{
    private LocalDateTime createdDate, lastUpdate;
    private String subject, information;
    private DateTimeFormatter dtf;

    public Note(String subject, String information) {
        this.subject = subject;
        this.information = information;
        createdDate = LocalDateTime.now();
        lastUpdate = createdDate;
        dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    }

    public String getCreatedDate() {
        return dtf.format(createdDate);
    }

    public String getLastUpdate() {
        return dtf.format(lastUpdate);
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
