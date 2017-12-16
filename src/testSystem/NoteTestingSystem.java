package testSystem;

import data.Note;
import data.Notes;

import java.util.Scanner;

public class NoteTestingSystem {

    private Scanner scanner = new Scanner(System.in);

    public void run(Notes notes){
        boolean running = true;
        String command;
        while (running){
            command = selectCommand();
            switch (command) {
                case "A":
                    if (!add(notes)){
                        running = false;
                    }
                    break;
                case "E":
                    if (!edit(notes)){
                        running = false;
                    }
                    break;
                case "R":
                    if (!remove(notes)){
                        running = false;
                    }
                    break;
                default:
                    running = false;

            }
            if (running){
                for (int index = 0; index < notes.getSize(); index++){
                    System.out.println("-----------------------------------");
                    new NoteViewAsString().view(notes.getNote(index));
                    System.out.println("-----------------------------------");
                }
            }
        }
    }

    private String selectCommand(){
        System.out.print("Select command: [A] add [E] edit [R] remove: ");
        return scanner.nextLine();
    }

    private boolean add(Notes notes){
        String subjectTemp, infoTemp;
        System.out.print("Subject: ");
        subjectTemp = scanner.nextLine();
        System.out.print("Info: ");
        infoTemp = scanner.nextLine();

        if (subjectTemp.equals("") || infoTemp.equals("")) {
            return false;
        }

        notes.addNote(
                new Note(subjectTemp, infoTemp)
        );
        return true;
    }

    private boolean edit(Notes notes){
        int index;
        String subject, info;
        System.out.printf("Select Note Index [0-%d]: ",notes.getSize()-1);
        index = scanner.nextInt();
        scanner.nextLine();
        if (index >= notes.getSize() || index < 0)
            return false;

        Note noteTemp = notes.getNote(index);
        System.out.print("Edit Subject [If don't want to edit press 'ENTER' again]: ");
        subject = scanner.nextLine();
        System.out.print("Edit Info [If don't want to edit press 'ENTER' again]: ");
        info = scanner.nextLine();
        if (!subject.isEmpty() && !info.isEmpty())
            notes.editNote(index, subject, info);
        else if (!subject.isEmpty())
            notes.editNote(index, subject, noteTemp.getInformation());
        else if (!info.isEmpty())
            notes.editNote(index, noteTemp.getSubject(), info);
        return true;
    }

    private boolean remove(Notes notes){
        int index;
        System.out.printf("Select Remove Index [0-%d]: ", notes.getSize()-1);
        index = scanner.nextInt();
        scanner.nextLine();
        if (index >= notes.getSize() || index < 0)
            return false;
        notes.removeNote(index);
        return true;
    }
}
