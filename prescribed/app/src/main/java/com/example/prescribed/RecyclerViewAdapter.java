package com.example.prescribed;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private List<String> images;
    private List<String> medNames;
    private List<String> timeText;
    private List<String> notesText;
    private Context context;

    public RecyclerViewAdapter(List<String> images, List<String> medNames, List<String> timeText,List<String> notesText, Context context) {
        this.context = context;
        this.images = images;
        this.medNames = medNames;
        this.timeText = timeText;
        this.notesText = notesText;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listmedications, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    //call when adding item to list
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Glide.with(context)
//                .asBitmap()
//                .load(images.get(position))
//                .into(holder.image);
        holder.medName.setText(medNames.get(position));
        holder.timeText.setText(timeText.get(position));
        holder.notes.setText(notesText.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, medNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return medNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView medName;
        TextView timeText;
        TextView notes;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            medName = itemView.findViewById(R.id.med_name);
            timeText = itemView.findViewById(R.id.time_text);
            notes = itemView.findViewById(R.id.notes_text);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
