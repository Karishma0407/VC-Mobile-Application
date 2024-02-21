package com.example.facultymobileproject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView facultyBonusRateView;
    TextView facultyIdView;
    TextView facultyLNameView;
    TextView facultyFNameView;
    TextView facultySalaryView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        facultyBonusRateView = itemView.findViewById(R.id.facultyBonusRateView);
        facultyIdView = itemView.findViewById(R.id.facultyIdView);
        facultyLNameView = itemView.findViewById(R.id.facultyLNameView);
        facultyFNameView = itemView.findViewById(R.id.facultyFNameView);
        facultySalaryView = itemView.findViewById(R.id.facultySalaryView);
    }
}
