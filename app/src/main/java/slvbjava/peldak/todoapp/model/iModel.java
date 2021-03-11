package slvbjava.peldak.todoapp.model;

import java.util.List;

public interface iModel {

    List<ToDoItem> getAllToDoItems();
    void removeToDoItem(ToDoItem tditem);
    void removeAllToDoItems();
    void saveToDoItem(ToDoItem tditem);

}
