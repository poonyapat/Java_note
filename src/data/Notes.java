package data;

import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Notes implements Serializable {
    private ArrayList<Note> notes;
    private Set<String> categories;

    public Notes() {
        notes = new ArrayList<>();
        categories = new TreeSet<>();
    }

    public void addNote(Note note){
        notes.add(note);
    }

    public int getSize(){
        return notes.size();
    }

    public void editNote(int noteIndex, String subject, String information, String category) {
        notes.get(noteIndex).edit(subject, information, category);
    }

    public void removeNote(int noteIndex){
        notes.remove(noteIndex);
    }

    public Note getNote(int noteIndex){
        return notes.get(noteIndex);
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(ObservableList<String> categories) {
        this.categories.addAll(categories);
        this.categories.remove("Add New Category");
    }
}
