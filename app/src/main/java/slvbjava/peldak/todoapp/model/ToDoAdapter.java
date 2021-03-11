package slvbjava.peldak.todoapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import slvbjava.peldak.todoapp.R;

public class ToDoAdapter extends ArrayAdapter<ToDoItem> {

    private int resource;

    public ToDoAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);

        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ToDoItem tditem = getItem(position);

        if (convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, null);
        }

        TextView tvNam = convertView.findViewById(R.id.tvName);
        TextView tvYea = convertView.findViewById(R.id.tvYear);
        TextView tvMon = convertView.findViewById(R.id.tvMonth);
        TextView tvDa = convertView.findViewById(R.id.tvDay);
        ImageView ivPrio = convertView.findViewById(R.id.ivPriority);

        tvNam.setText(tditem.getName());
        tvYea.setText(tditem.getYear()+".");
        if (tditem.getMonth()<10) {
            tvMon.setText("0"+tditem.getMonth()+".");
        } else {
            tvMon.setText(tditem.getMonth()+".");
        }
        if (tditem.getDay()<10) {
            tvDa.setText("0"+tditem.getDay()+".");
        } else {
            tvDa.setText(tditem.getDay()+".");
        }

        if (tditem.getPriority()==1) {
            ivPrio.setImageResource(R.drawable.high);
        } else if (tditem.getPriority()==2) {
            ivPrio.setImageResource(R.drawable.medium);
        } else if (tditem.getPriority()==3) {
            ivPrio.setImageResource(R.drawable.low);
        }

        return convertView;
    }
}
