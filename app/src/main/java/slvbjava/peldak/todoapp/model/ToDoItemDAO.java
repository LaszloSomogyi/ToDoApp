package slvbjava.peldak.todoapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ToDoItemDAO implements iModel {

    ToDoItemDBHelper helper;

    public ToDoItemDAO(Context context) {
        helper = new ToDoItemDBHelper(context);
    }

    @Override
    public List<ToDoItem> getAllToDoItems() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM todoitem", null);
        List<ToDoItem> items = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String details = cursor.getString(cursor.getColumnIndex("details"));
            int year = cursor.getInt(cursor.getColumnIndex("year"));
            int month = cursor.getInt(cursor.getColumnIndex("month"));
            int day = cursor.getInt(cursor.getColumnIndex("day"));
            int priority = cursor.getInt(cursor.getColumnIndex("priority"));

            ToDoItem tditem = new ToDoItem(id, name, details, year, month, day, priority);
            items.add(tditem);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return items;
    }

    @Override
    public void removeToDoItem(ToDoItem tditem) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM todoitem WHERE _id=?", new Integer[]{tditem.getId()});
        db.close();
    }

    @Override
    public void removeAllToDoItems() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM todoitem");
        db.close();
    }

    @Override
    public void saveToDoItem(ToDoItem tditem) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", tditem.getName());
        cv.put("details", tditem.getDetails());
        cv.put("year", tditem.getYear());
        cv.put("month", tditem.getMonth());
        cv.put("day", tditem.getDay());
        cv.put("priority", tditem.getPriority());

        int id = (int) db.insert("todoitem", null, cv);
        tditem.setId(id);
        db.close();
    }


}
