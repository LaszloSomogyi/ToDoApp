package slvbjava.peldak.todoapp.comparators;

import java.util.Comparator;

import slvbjava.peldak.todoapp.model.ToDoItem;

public class PriorityComparator implements Comparator<ToDoItem> {
    @Override
    public int compare(ToDoItem o1, ToDoItem o2) {
        return o1.getPriority()-o2.getPriority();
    }
}
