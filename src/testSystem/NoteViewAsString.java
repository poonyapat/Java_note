package testSystem;

import data.Note;

public class NoteViewAsString {
    public void view(Note note){
        System.out.println(
                "CreatedDate: " + note.getCreatedDate() +
                "\nLastUpdate: " + note.getLastUpdate() +
                "\n\nSubject: " + note.getSubject() +
                "\n\n" + note.getInformation()
        );
    }
}
