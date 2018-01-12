package data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Note implements Serializable {
    private LocalDateTime createdDate, lastUpdate;
    private String subject, information, category;
    private ArrayList<String> tag;

    public Note(String subject, String information, String category, List<String> tag) {
        this.tag = new ArrayList<>();
        this.subject = subject;
        this.information = information;
        createdDate = LocalDateTime.now();
        lastUpdate = createdDate;
        this.category = category;
        this.tag.addAll(tag);
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<String> getTag() {
        return tag;
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

    void edit(String subject, String information, String category) {
        this.subject = subject;
        this.information = information;
        lastUpdate = LocalDateTime.now();
        this.category = category;
    }
}
