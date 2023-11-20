package com.example.bbkiel.ui.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.bbkiel.R;
import com.example.bbkiel.databinding.FragmentDistrictBinding;
import com.example.bbkiel.databinding.FragmentFavouritesBinding;
import com.example.bbkiel.databinding.FragmentProjectBinding;
import com.example.bbkiel.model.SubProject;
import com.example.bbkiel.ui.favourites.FavouritesViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DistrictFragment extends Fragment implements ExpandableListView.OnChildClickListener {


    private NavController navController;
    private FragmentDistrictBinding binding;
    private ProjectViewModel projectViewModel;

    ExpandableListView expandableListView;
    DistrictAdapter expandableListAdapter;
    List<String> districts;
    HashMap<String, List<SubProject>> subProjectsInAllDistricts;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        projectViewModel = new ViewModelProvider(requireActivity()).get(ProjectViewModel.class);

        binding = FragmentDistrictBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        NavHostFragment navHostFragment =
                (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        this.navController = navHostFragment.getNavController();


        //Setup von ExpandableListView
        expandableListView = binding.expandableDistrictView;

        //füllen der Hashmap mit allen Distrikten und Teilprojekten, diese werden im ProjectViewModel definiert
        subProjectsInAllDistricts = projectViewModel.getSubProjectsInAllDistricts();
        //setzen der districts liste (alle keys der Hashmap)
        districts = new ArrayList<String>(subProjectsInAllDistricts.keySet());
        //erstellen des Adapters
        //expandableListAdapter = new DistrictAdapter(this, districts, subProjectsInAllDistricts);
        expandableListAdapter = new DistrictAdapter(this.getContext(),districts, subProjectsInAllDistricts);
        //setten des Adapters
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(this);

        return root;
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view,
                                int  districtPosition, int projectPosition, long id) {

        // Navigation zu einem Teilprojekt (später aus einer Recycler View und somit ausgelagert in die Funktion onListItemClick)
        // siehe FavouritesFragment
        // ändern des Arguments title in den Namen des Beispielteilprojektes

        //speichern von titel und Id
        String subProjectTitle = expandableListAdapter.getChild(districtPosition, projectPosition).getTitle();
        int subProjectId = expandableListAdapter.getChild(districtPosition, projectPosition).getBudgetInvestmentId();

        // dem teilprojekt titel und Id übergeben innerhalb einer payload als Bundle
        Bundle payload = new Bundle();
        payload.putSerializable("subProjectId",subProjectId);
        payload.putString("title", subProjectTitle);

        //navigieren zum Projekt über einen Button und übergeben der Arguments
        //this.navController.navigate(R.id.action_district_to_subproject, payload);
        this.navController.navigate(R.id.action_navigation_project_to_subproject, payload);
        return false;
    }
}