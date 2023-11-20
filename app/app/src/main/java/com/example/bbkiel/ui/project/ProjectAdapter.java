package com.example.bbkiel.ui.project;
import android.view.LayoutInflater;


import android.view.ViewGroup;
import android.widget.TextView;

import android.view.View;
import androidx.annotation.NonNull;
import com.example.bbkiel.R;
import com.example.bbkiel.model.Project;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectHolder> {

    private List<Project> projectList = new ArrayList<>();
    private final ListItemClickListener onClickListener;



    public ProjectAdapter(ListItemClickListener onClickListener){
        this.onClickListener = onClickListener;

    }


    @NonNull
    @Override
    public ProjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent,false);
        return new ProjectHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectHolder holder, int position){
        Project currentAbstractProject = projectList.get(position);
        holder.textViewSlug.setText(currentAbstractProject.getSlug());
        holder.textViewName.setText(currentAbstractProject.getName());

    }

    @Override
    public int getItemCount(){return this.projectList.size();}

    public void setProjects (List<Project> projectList){
        this.projectList = projectList;
        notifyDataSetChanged();
    }

    public Project getProjectById(int position){
        return this.projectList.get(position);
    }

    public class ProjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textViewSlug;
        private TextView textViewName;

        public ProjectHolder(@NonNull View itemView){
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textview_project_information);
            this.textViewSlug = itemView.findViewById(R.id.textview_project_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            onClickListener.onListItemClick(position);

        }
    }

    public interface ListItemClickListener{
        void onListItemClick(int position);
    }
}