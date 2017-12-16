import java.io.Serializable;
import java.util.ArrayList;

public class Notes implements Serializable{
    private static final long serialVerionUID = 1L;
    ArrayList<Note> notes;

    public Notes() {
        notes = new ArrayList<Note>();
    }

    public void addNote(Note note){
        notes.add(note);
    }

    public void editNote(int noteIndex, String subject, String information){
        notes.get(noteIndex).edit(subject, information);
    }

    public void removeNote(int noteIndex){
        notes.remove(noteIndex);
    }
}
