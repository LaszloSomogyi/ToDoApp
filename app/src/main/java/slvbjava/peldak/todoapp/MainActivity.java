package slvbjava.peldak.todoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ContextMenu;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import slvbjava.peldak.todoapp.comparators.DateComparator;
import slvbjava.peldak.todoapp.comparators.NameComparator;
import slvbjava.peldak.todoapp.comparators.PriorityComparator;
import slvbjava.peldak.todoapp.model.ToDoAdapter;
import slvbjava.peldak.todoapp.model.ToDoItem;
import slvbjava.peldak.todoapp.model.ToDoItemDAO;

public class MainActivity extends AppCompatActivity {

    private static final int RQC_NEW_ITEM = 1;
    private List<ToDoItem> items;
    private ToDoAdapter adapter;
    private ToDoItemDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewItem();
            }
        });

        dao = new ToDoItemDAO(this);
        items = dao.getAllToDoItems();

        adapter = new ToDoAdapter(this, R.layout.list_item, items);
        ListView lvitems = findViewById(R.id.lvItems);
        lvitems.setAdapter(adapter);

        registerForContextMenu(lvitems);
        dateChecker();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ToDoItem tditem = adapter.getItem(info.position);

        if (item.getItemId() == R.id.ci_details) {
            Intent intent = new Intent(this, TDIDetailsActivity.class);
            intent.putExtra("tditem", tditem);
            intent.putExtra("index", info.position);
            startActivity(intent);
            return true;

        } else if (item.getItemId() == R.id.ci_remove) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Törlési megerősítés");
            builder.setMessage("Biztosan törölni akarod?");
            builder.setNegativeButton("Nem", null);
            builder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.remove(tditem);
                    dao.removeToDoItem(tditem);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.oi_ordering_byname) {
            Collections.sort(items, new NameComparator());
            adapter = new ToDoAdapter(this, R.layout.list_item, items);
            ListView lvitems = findViewById(R.id.lvItems);
            lvitems.setAdapter(adapter);
            return true;

        } else if (id == R.id.oi_ordering_bypriority) {
            Collections.sort(items, new PriorityComparator());
            adapter = new ToDoAdapter(this, R.layout.list_item, items);
            ListView lvitems = findViewById(R.id.lvItems);
            lvitems.setAdapter(adapter);

            return true;

        } else if (id == R.id.oi_ordering_bydate) {
            Collections.sort(items, new DateComparator());
            adapter = new ToDoAdapter(this, R.layout.list_item, items);
            ListView lvitems = findViewById(R.id.lvItems);
            lvitems.setAdapter(adapter);
            return true;

        }

        else if (id == R.id.oi_add_new) {
            addNewItem();
            return true;

        } else if (id == R.id.oi_remove_all) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Törlési megerősítés");
            builder.setMessage("Biztos törlöd az összes teendőt?");
            builder.setNegativeButton("Nem", null);
            builder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.clear();
                    dao.removeAllToDoItems();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addNewItem(){
        Intent intent = new Intent(this, ToDoItemActivity.class);
        startActivityForResult(intent, RQC_NEW_ITEM);
    }

    public void getDetailsActivity(){
        Intent intent = new Intent(this, TDIDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==RESULT_OK){
            ToDoItem tdItem = (ToDoItem) data.getSerializableExtra("tdi");
                adapter.add(tdItem);
                dao.saveToDoItem(tdItem);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void dateChecker ( ) {
        for (ToDoItem item : items) {
            Date date = new Date();
            if (date.getYear()+1900==item.getYear() && date.getMonth()+1==item.getMonth() && date.getDate()==item.getDay()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Mai teendőd:");
                builder.setMessage(item.getName());
                builder.setNeutralButton("oké", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
}