/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Persad
 */

package ucf.todolistapp;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ucf.todolistapp.Data;
import ucf.todolistapp.InitializeItems;
import java.time.LocalDate;
public class Controller {
    @FXML
    private TextField shortDescriptionField;

    @FXML
    private TextArea detailsArea;

    @FXML
    private DatePicker deadlinePicker;

    public InitializeItems processResults() {
        String shortDescription = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadlineValue = deadlinePicker.getValue();

        InitializeItems newItem = new InitializeItems(shortDescription, details, deadlineValue);
        Data.getInstance().addTodoItem(newItem);
        return newItem;
    }
}
