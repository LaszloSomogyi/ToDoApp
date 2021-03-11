package slvbjava.peldak.todoapp.comparators;

import java.util.Comparator;

import slvbjava.peldak.todoapp.model.ToDoItem;

public class DateComparator implements Comparator<ToDoItem> {
    @Override
    public int compare(ToDoItem o1, ToDoItem o2) {
        int yearDif = o1.getYear()-o2.getYear();
        if (yearDif!=0) {
            return yearDif;
        } else {
            int monthdif = o1.getMonth()-o2.getMonth();
            if (monthdif!=0) {
                return monthdif;
            } else {
                int dayDif = o1.getDay()-o2.getDay();
                return dayDif;
            }
        }
    }
}
