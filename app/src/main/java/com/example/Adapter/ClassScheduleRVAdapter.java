package com.example.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Model.Clazz;
import com.example.jesse.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Junaid on 3/10/2019.
 */

public class ClassScheduleRVAdapter  extends RecyclerView.Adapter<ClassScheduleRVAdapter.ClassViewHolder> {

    ArrayList<Clazz> classes;
    private Context context;

    public ClassScheduleRVAdapter(ArrayList<Clazz> classes) {
        this.classes = classes;
    }

    public void setClasses(ArrayList<Clazz> classes) {
        this.classes = classes;
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder{
        TextView classDate;
        TextView classStartTime;
        TextView classEndTime;
        TextView classLocationName;

        ClassViewHolder(View view) {
            super(view);
            classDate= view.findViewById(R.id.class_date);
            classStartTime = view.findViewById(R.id.class_start_time);
            classEndTime = view.findViewById(R.id.class_end_time);
            classLocationName = view.findViewById(R.id.class_location_name);
        }
    }

    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_class_schedule_row,parent,false);
        ClassViewHolder sv = new ClassViewHolder(v);
        return sv;
    }


    @Override
    public void onBindViewHolder(ClassViewHolder holder, final int position) {

        holder.classDate.setText("Date: " + classes.get(position).getStartDateTime().split("T")[0]);
        holder.classStartTime.setText("Start Time: " + classes.get(position).getStartDateTime().split("T")[1]);
        holder.classEndTime.setText("End Time: " + classes.get(position).getEndDateTime().split("T")[1]);
        holder.classLocationName.setText("Location: " + classes.get(position).getLocation().getName());

/*        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),FoodDetail.class);
                i.putExtra("foodItem",foodItems.get(position));
                view.getContext().startActivity(i);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        if(classes != null) {
            return this.classes.size();
        }
        return 0;
    }


}
