package slvbjava.peldak.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import slvbjava.peldak.todoapp.model.ToDoItem;

public class TDIDetailsActivity extends AppCompatActivity {

    private ToDoItem tdItem;
    private Intent intent;
    private TextView tvName;
    private TextView tvDetails;
    private TextView tvYear;
    private TextView tvMonth;
    private TextView tvDay;
    private ImageView ivPrio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdi_details);

        tvName = findViewById(R.id.tvName);
        tvDetails = findViewById(R.id.tvDetails);
        tvYear = findViewById(R.id.tvYear);
        tvMonth = findViewById(R.id.tvMonth);
        tvDay = findViewById(R.id.tvDay);
        ivPrio = findViewById(R.id.ivPriority);

        intent = getIntent();

        tdItem = (ToDoItem) intent.getSerializableExtra("tditem");
        if (tdItem!=null) {
            tvName.setText(tdItem.getName());
            tvDetails.setText(tdItem.getDetails());
            tvYear.setText(tdItem.getYear()+".");
            tvMonth.setText(tdItem.getMonth()+".");
            tvDay.setText(tdItem.getDay()+".");

            if (tdItem.getPriority()==1) {
                ivPrio.setImageResource(R.drawable.high);
            } else if (tdItem.getPriority()==2) {
                ivPrio.setImageResource(R.drawable.medium);
            } else if (tdItem.getPriority()==3) {
                ivPrio.setImageResource(R.drawable.low);
            }
        }
    }

    public void goback(View view) {
        finish();
    }
}