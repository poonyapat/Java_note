package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Notes implements Serializable {
    private ArrayList<Note> notes;

    public Notes() {
        notes = new ArrayList<>();
    }

    public void addNote(Note note){
        notes.add(note);
    }

    public int getSize(){
        return notes.size();
    }

    public void editNote(int noteIndex, String subject, String information){
        notes.get(noteIndex).edit(subject, information);
    }

    public void removeNote(int noteIndex){
        notes.remove(noteIndex);
    }

    public Note getNote(int noteIndex){
        return notes.get(noteIndex);
    }
}
