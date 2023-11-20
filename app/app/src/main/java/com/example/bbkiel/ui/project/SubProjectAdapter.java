package com.example.bbkiel.ui.project;
import android.view.LayoutInflater;


import android.view.ViewGroup;
import android.widget.TextView;

import android.view.View;
import androidx.annotation.NonNull;
import com.example.bbkiel.R;
import com.example.bbkiel.model.Project;
import com.example.bbkiel.model.SubProject;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SubProjectAdapter extends RecyclerView.Adapter<SubProjectAdapter.SubProjectHolder> {

    private List<SubProject> subProjectList = new ArrayList<>();
    private final ListItemClickListener onClickListener;



    public SubProjectAdapter(ListItemClickListener onClickListener){
        this.onClickListener = onClickListener;

    }


    @NonNull
    @Override
    public SubProjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent,false);
        return new SubProjectHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubProjectHolder holder, int position){
        SubProject currentSubProject = subProjectList.get(position);
        holder.textViewTitle.setText(currentSubProject.getTitle());
        holder.textViewDescription.setText(currentSubProject.getDescription());

    }

    @Override
    public int getItemCount(){return this.subProjectList.size();}

    public void setSubProjects (List<SubProject> subProjectList){
        this.subProjectList = subProjectList;
        notifyDataSetChanged();
    }

    public SubProject getSubProjectById(int position){
        return this.subProjectList.get(position);
    }

    public class SubProjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textViewTitle;
        private TextView textViewDescription;

        public SubProjectHolder(@NonNull View itemView){
            super(itemView);
            this.textViewTitle = itemView.findViewById(R.id.textview_project_information);
            this.textViewDescription = itemView.findViewById(R.id.textview_project_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            onClickListener.onListItemClickSubProject(position);

        }
    }

    public interface ListItemClickListener{
        void onListItemClickSubProject(int position);
    }
}