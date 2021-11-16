/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Persad
 */

package ucf.todolistapp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
public class Data {
    private static Data instance = new Data();
    private static String filename = "TodoList.txt";

    private ObservableList<InitializeItems> GetItems;
    private DateTimeFormatter formatter;

    public static Data getInstance() {
        return instance;
    }

    private Data() {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public ObservableList<InitializeItems> getTodoItems() {
        return GetItems;
    }

    public void addTodoItem(InitializeItems item) {
        GetItems.add(item);
    }

    public void loadTodoItems() throws IOException {

        GetItems = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];

                LocalDate date = LocalDate.parse(dateString, formatter);
                InitializeItems todoItem = new InitializeItems(shortDescription, details, date);
                GetItems.add(todoItem);
            }

        } finally {
            if(br != null) {
                br.close();
            }
        }
    }

    public void storeTodoItems() throws IOException {

        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<InitializeItems> iter = GetItems.iterator();
            while(iter.hasNext()) {
                InitializeItems item = iter.next();
                bw.write(String.format("%s\n%s\n%s\n",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline().format(formatter)));
                bw.newLine();
            }

        } finally {
            if(bw != null) {
                bw.close();
            }
        }
    }

    public void deleteTodoItem(InitializeItems items) {
        GetItems.remove(items);
    }

}

