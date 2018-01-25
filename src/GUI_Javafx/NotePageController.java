package GUI_Javafx;

import Storage.ObjectIOStream;
import Storage.UserData;
import data.Note;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NotePageController {
    @FXML
    TextArea noteBodyTextArea;
    @FXML
    TextField noteHeaderTextField;
    @FXML
    ComboBox<String> noteCategoryComboBox, filterComboBox;
    @FXML
    GridPane subjectGridPane;

    private UserData userData;
    private ObservableList<String> noteCategories, filterCategories;
    private int selectedIndex;

    @FXML
    public void initialize() {
        noteCategories = FXCollections.observableArrayList();
        filterCategories = FXCollections.observableArrayList();
        selectedIndex = -1;
        filterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> updateSubjectRow(newValue));
    }

    public void setUser(UserData userData) {
        this.userData = userData;
        noteCategories.addAll(userData.notes.getCategories());
        filterCategories.addAll(userData.notes.getCategories());
        noteCategories.add("Add New Category");
        filterCategories.add("All");
        noteCategoryComboBox.setItems(noteCategories);
        filterComboBox.setItems(filterCategories);
        filterComboBox.getSelectionModel().select(filterCategories.indexOf("All"));
    }

    @FXML
    void updateSubjectRow(String filterCate) {
        subjectGridPane.getChildren().removeAll(subjectGridPane.getChildren());
        int index = 0;
        for (int i = 0; i < userData.notes.getSize(); i++) {
            if (filterCate.equals(userData.notes.getNote(userData.notes.getSize() - 1 - i).getCategory()) || filterCate.equals("All")) {
                subjectGridPane.addRow(index, createSubjectRow(userData.notes.getSize() - 1 - i));
                index++;
            }
        }
    }

    private Label createSubjectRow(int index) {
        Note note = userData.notes.getNote(index);
        Label label = new Label(
                setSubjectRowText(note)
        );
        label.setPrefSize(225, 50);
        label.setOnMouseClicked(event -> {
            if (selectedIndex != index)
                selectSubject(index, note);
        });
        return label;
    }

    private void selectSubject(int index, Note note) {
        selectedIndex = index;
        noteHeaderTextField.setText(note.getSubject());
        noteBodyTextArea.setText(note.getInformation());
        noteCategoryComboBox.getSelectionModel().select(note.getCategory());
        System.out.println(index);
    }

    private String setSubjectRowText(Note note) {
        return "Last Update: " + note.getLastUpdate() + "\n" +
                "Subject: " + note.getSubject();
    }

    @FXML
    private void actionNewNote() {
        selectedIndex = -1;
        noteBodyTextArea.setText("");
        noteHeaderTextField.setText("");
    }

    @FXML
    private void actionSaveNote() {
        if (noteHeaderTextField.getText().isEmpty() || noteBodyTextArea.getText().isEmpty() || noteCategoryComboBox.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Please add text to Header, Body and Select Category before SAVE").show();
            return;
        }
        if (selectedIndex != -1) {
            userData.notes.editNote(selectedIndex, noteHeaderTextField.getText(), noteBodyTextArea.getText(), noteCategoryComboBox.getValue());
        } else {
            userData.notes.addNote(new Note(noteHeaderTextField.getText(), noteBodyTextArea.getText(), noteCategoryComboBox.getValue(), new ArrayList<>()));
            selectedIndex = userData.notes.getSize() - 1;
        }
        userData.notes.setCategories(noteCategories);
        new ObjectIOStream().writeObject(userData, userData.getUsername() + ".ser");
        updateSubjectRow(filterComboBox.getValue());
    }

    @FXML
    private void actionSaveNewNote() {
        userData.notes.addNote(new Note(noteHeaderTextField.getText(), noteBodyTextArea.getText(), noteCategoryComboBox.getValue(), new ArrayList<>()));
        selectedIndex = userData.notes.getSize() - 1;
        userData.notes.setCategories(noteCategories);
        new ObjectIOStream().writeObject(userData, userData.getUsername() + ".ser");
        updateSubjectRow(filterComboBox.getValue());
    }

    @FXML
    private void actionAddNewCategory() {
        if (noteCategoryComboBox.getSelectionModel().getSelectedItem().equals("Add New Category")) {
            TextField newCate = new TextField();
            newCate.setPromptText("Add new Category");
            newCate.setPrefWidth(200);
            newCate.setTranslateX(100);
            newCate.setTranslateY(50);
            Button confirmButton = new Button("Confirm");
            confirmButton.setPrefWidth(100);
            confirmButton.setTranslateX(150);
            confirmButton.setTranslateY(125);
            confirmButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (EventHandler<Event>) event -> {
                if (noteCategories.contains(newCate.getText())) {
                    noteCategoryComboBox.getSelectionModel().select(newCate.getText());
                    new Alert(Alert.AlertType.WARNING, "This Category is already in your categories list").show();
                } else {
                    noteCategories.add(noteCategories.size() - 1, newCate.getText());
                    filterCategories.add(filterCategories.size() - 1, newCate.getText());
                    noteCategoryComboBox.getSelectionModel().selectPrevious();
                }
                noteCategoryComboBox.getParent().getParent().getParent().setDisable(false);
                ((Stage) confirmButton.getScene().getWindow()).close();
            });
            Parent root = new Pane(newCate, confirmButton);
            Stage stage = new Stage();
            stage.setTitle("New Category");
            stage.setScene(new Scene(root, 400, 200));
            stage.show();

            noteCategoryComboBox.getParent().getParent().getParent().setDisable(true);
        }
    }

    @FXML
    private void actionSignOut() {
        PageChanger.changePage(this, noteBodyTextArea, "loginPage.fxml");
    }

    @FXML
    private void actionExit() {
        System.exit(0);
    }

    @FXML
    private void deleteNote() {
        userData.notes.removeNote(selectedIndex);
        selectedIndex--;
        if (selectedIndex < 0)
            selectedIndex = 0;
        if (userData.notes.getSize() == 0)
            actionNewNote();
        else
            selectSubject(selectedIndex, userData.notes.getNote(selectedIndex));
        new ObjectIOStream().writeObject(userData, userData.getUsername() + ".ser");
        updateSubjectRow(filterComboBox.getValue());
    }
}
