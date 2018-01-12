//package GUI_Javafx;
//
//import Storage.ObjectIOStream;
//import Storage.UserData;
//import data.Note;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.GridPane;
//
//public class NoteManageController {
//    @FXML
//    Button readNoteButton, writeNoteButton;
//    @FXML
//    GridPane readPartGridPane;
//    @FXML
//    AnchorPane writePartAnchorPane;
//    @FXML
//    GridPane subjectGridPane;
//    @FXML
//    AnchorPane detailAnchorPane;
//    @FXML
//    Label createdDateLabel, subjectLabel, detailLabel, lastUpdateLabel;
//    @FXML
//    TextField addedSubject;
//    @FXML
//    TextArea addedDetail;
//    private UserData userData;
//
//    public void setUser(UserData userData) {
//        this.userData = userData;
//    }
//
//    @FXML
//    public void actionReadNote() {
//        subjectGridPane.getChildren().removeAll(subjectGridPane.getChildren());
//        for (int i = 0; i < userData.notes.getSize(); i++) {
//            subjectGridPane.addRow(i, createSubjectRow(userData.notes.getSize() - 1 - i));
//        }
//        changePart(PagePart.READ);
//    }
//
//    private Label createSubjectRow(int index) {
//        Note note = userData.notes.getNote(index);
//        Label label = new Label(
//                "Last Update: " + note.getLastUpdate() + "\n" +
//                        "Subject: " + note.getSubject()
//        );
//        label.setPrefSize(196, 50);
//        label.setOnMouseClicked(event -> {
//            createdDateLabel.setText("Created : " + note.getCreatedDate());
//            lastUpdateLabel.setText("Last Update : " + note.getLastUpdate());
//            subjectLabel.setText("Subject: " + note.getSubject());
//            detailLabel.setText(note.getInformation());
//        });
//        return label;
//    }
//
//    @FXML
//    public void actionWriteNote() {
//        changePart(PagePart.WRITE);
//    }
//
//    @FXML
//    private void actionAddSubmit() {
//        if (addedSubject.getText().isEmpty() || addedDetail.getText().isEmpty()) {
//            new Alert(Alert.AlertType.ERROR, "You have to add text before SUBMIT").show();
//            return;
//        }
//        userData.notes.addNote(new Note(addedSubject.getText(), addedDetail.getText()));
//        new ObjectIOStream().writeObject(userData, userData.getUsername() + ".ser");
//
//        actionAddCancel();
//    }
//
//    @FXML
//    private void actionAddCancel() {
//        addedDetail.setText("");
//        addedSubject.setText("");
//    }
//
//    @FXML
//    public void actionSignOut() {
//        PageChanger.changePage(this, readNoteButton, "loginPage.fxml");
//    }
//
//    @FXML
//    public void actionExit() {
//        System.exit(0);
//    }
//
//    @FXML
//    private void changePart(PagePart pagePart) {
//        switch (pagePart) {
//            case READ:
//                readNoteButton.setDisable(true);
//                writeNoteButton.setDisable(false);
//                readPartGridPane.setVisible(true);
//                writePartAnchorPane.setVisible(false);
//                break;
//            case WRITE:
//                writeNoteButton.setDisable(true);
//                readNoteButton.setDisable(false);
//                writePartAnchorPane.setVisible(true);
//                readPartGridPane.setVisible(false);
//                break;
//        }
//    }
//
//    private enum PagePart {READ, WRITE}
//}
