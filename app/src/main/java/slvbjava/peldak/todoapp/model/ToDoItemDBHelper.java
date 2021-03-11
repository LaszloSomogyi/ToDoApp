package slvbjava.peldak.todoapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ToDoItemDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "todoitemlist.db";
    private static final int DB_VERSION = 1;

    public ToDoItemDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE todoitem (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, details TEXT, year INTEGER, month INTEGER, day INTEGER, priority INTEGER)");
        db.execSQL("INSERT INTO todoitem (name, details, year, month, day, priority) " +
                "VALUES ('Android vizsga', 'nagyon jól megírni', 2021, 1, 24, 1)");
        db.execSQL("INSERT INTO todoitem (name, details, year, month, day, priority) " +
                "VALUES ('GIT+GitHub', 'megtanulni mindkettőt', 2021, 2, 30, 3)");
        db.execSQL("INSERT INTO todoitem (name, details, year, month, day, priority) " +
                "VALUES ('Clean Code', 'megtanulni mindkettőt', 2021, 3, 2, 2)");
        db.execSQL("INSERT INTO todoitem (name, details, year, month, day, priority) " +
                "VALUES ('Maven', 'megtanulni mindkettőt', 2021, 3, 24, 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE todoitem");
        onCreate(db);
    }
}

