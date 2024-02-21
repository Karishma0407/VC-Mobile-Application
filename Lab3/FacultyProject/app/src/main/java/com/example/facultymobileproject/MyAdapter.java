package com.example.facultymobileproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Faculty> facultyList;

    public MyAdapter(Context context, List<Faculty> facultyList) {
        this.context = context;
        this.facultyList = facultyList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_all_faculties,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.facultyBonusRateView.setText(String.valueOf(facultyList.get(position).getBonus()));
        holder.facultyIdView.setText(String.valueOf(facultyList.get(position).getFaculty_id()));
        holder.facultyFNameView.setText(facultyList.get(position).getFaculty_FName());
        holder.facultyLNameView.setText(facultyList.get(position).getFaculty_LName());
        holder.facultySalaryView.setText(String.valueOf(facultyList.get(position).getSalary()));
    }

    @Override
    public int getItemCount() {
        return facultyList.size();
    }
}
