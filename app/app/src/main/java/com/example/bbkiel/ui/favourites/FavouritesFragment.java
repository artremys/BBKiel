package com.example.bbkiel.ui.favourites;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bbkiel.R;
import com.example.bbkiel.databinding.FragmentFavouritesBinding;
import com.example.bbkiel.model.Project;

import com.example.bbkiel.ui.project.ProjectAdapter;
import com.example.bbkiel.ui.project.SubProjectAdapter;

public class FavouritesFragment extends Fragment implements ProjectAdapter.ListItemClickListener, SubProjectAdapter.ListItemClickListener{

    private FavouritesViewModel fViewModel;
    private NavController navController;
    private FragmentFavouritesBinding binding;
    private ProjectAdapter adapterProject;  // adapter für Liste von Projekten
    private SubProjectAdapter adapterSubProject; // adapter für Liste von Teilprojekten

    public static FavouritesFragment newInstance() {
        return new FavouritesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        fViewModel =
                new ViewModelProvider(this).get(FavouritesViewModel.class);

        binding = FragmentFavouritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        NavHostFragment navHostFragment =
                (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        this.navController = navHostFragment.getNavController();

        fViewModel.getProjectsFromConsul();


//        //Setup ReccyclerView für Projekte
//        //hier wird die RecyclerView an die RecyclerView in der XML datei gebindet
//        RecyclerView projectRecyclerView = binding.projectRecyclerView;
//        projectRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
//        projectRecyclerView.setHasFixedSize(true);
//
//        //setup adapter für Projekte
//        adapterProject = new ProjectAdapter(this);
//        projectRecyclerView.setAdapter(adapterProject);
//
//        //get Liste von Projekten aus dem Viewmodel
//      fViewModel.getProjects().observe(getViewLifecycleOwner(), projects -> {
//           adapterProject.setProjects(projects);
//       });


        //Setup ReccyclerView für TeilProjekte
        RecyclerView subProjectRecyclerView = binding.subprojectRecyclerView;
        subProjectRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        subProjectRecyclerView.setHasFixedSize(true);

        //setup adapter für TeilProjekte
        adapterSubProject = new SubProjectAdapter(this);
        subProjectRecyclerView.setAdapter(adapterSubProject);

        //get Liste von TeilProjekten aus dem Viewmodel
        fViewModel.getFavlist().observe(getViewLifecycleOwner(), subProjects -> {
            adapterSubProject.setSubProjects(subProjects);
        });




        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fViewModel = new ViewModelProvider(this).get(FavouritesViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onListItemClick(int position) {
        Bundle payload = new Bundle();
        payload.putSerializable("project", adapterProject.getProjectById(position));

        // umbennen des Labels im Fragment Projekt (über das Argument title)
        String title = adapterProject.getProjectById(position).getName();
        payload.putString("title", title);

        //navigieren zum Projekt
        this.navController.navigate(R.id.action_navigation_favourites_to_navigation_project, payload);
    }

    @Override
    public void onListItemClickSubProject(int position) {
        Bundle payload = new Bundle();
        payload.putSerializable("subProject", adapterSubProject.getSubProjectById(position));

        // umbennen des Labels im Fragment Projekt (über das Argument title)
        String title = adapterSubProject.getSubProjectById(position).getTitle();
        payload.putString("title", title);
        this.navController.navigate(R.id.action_navigation_favourites_to_navigation_subproject, payload);
    }


}