package slvbjava.peldak.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import slvbjava.peldak.todoapp.model.ToDoItem;

public class ToDoItemActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etDetails;
    private EditText etYear;
    private EditText etMonth;
    private EditText etDay;
    private RadioGroup rgPriority;
    private ToDoItem tdItem;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_item);

        etName = findViewById(R.id.etName);
        etDetails = findViewById(R.id.etDetails);
        etYear = findViewById(R.id.etYear);
        etMonth = findViewById(R.id.etMonth);
        etDay = findViewById(R.id.etDay);
        rgPriority = findViewById(R.id.rgPriority);
        intent = getIntent();

        tdItem = (ToDoItem) intent.getSerializableExtra("tdi");
        if (tdItem!=null) {
            etName.setText(tdItem.getName());
            etDetails.setText(tdItem.getDetails());
            etYear.setText(tdItem.getYear());
            etMonth.setText(tdItem.getMonth());
            etDay.setText(tdItem.getDay());
            rgPriority = findViewById(R.id.rgPriority);
        }
    }


    public void mentes(View view) {
        if (tdItem==null) {
            tdItem = new ToDoItem();
        }
            tdItem.setName(etName.getText().toString());
            tdItem.setDetails(etDetails.getText().toString());
            tdItem.setYear(Integer.parseInt(etYear.getText().toString()));
            tdItem.setMonth(Integer.parseInt(etMonth.getText().toString()));
            tdItem.setDay(Integer.parseInt(etDay.getText().toString()));

            switch (rgPriority.getCheckedRadioButtonId()){
                case R.id.rbHigh:
                    tdItem.setPriority(1);
                    break;
                case R.id.rbMiddle:
                    tdItem.setPriority(2);
                    break;
                case R.id.rbLow:
                    tdItem.setPriority(3);
                    break;
            }
        intent.putExtra("tdi", tdItem);
        setResult(RESULT_OK, intent);
        finish();
    }


    public void megsem(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}