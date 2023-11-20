package com.example.bbkiel.ui.project;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.bbkiel.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


import com.example.bbkiel.databinding.FragmentProjectBinding;
import com.example.bbkiel.ui.favourites.FavouritesViewModel;
import com.example.bbkiel.ui.settings.SettingsViewModel;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bbkiel.model.*;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;


public class ProjectFragment extends Fragment implements TabLayoutMediator.TabConfigurationStrategy {

    private ProjectViewModel projectViewModel;
    private NavController navController;
    private FragmentProjectBinding binding;
    private CommentAdapter commentAdapter;
    private int userid;
    private int projectId = 3;

    //tabs und viewpager
    TabAdapter tabAdapter;
    TabLayout tabLayout;
    ViewPager2 viewPager;


    ArrayList<String> titles;


    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // speichern der Payload
        // Paylaod MUSS bei klick on Button und der Navigation mitgegeben werden, ansonsten Appabsturz
        projectId = (Integer) getArguments().get("projectId");

        projectViewModel = new ViewModelProvider(requireActivity()).get(ProjectViewModel.class);


        binding = FragmentProjectBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        NavHostFragment navHostFragment =
                (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        this.navController = navHostFragment.getNavController();


        SharedPreferences sp = this.getActivity().getSharedPreferences("pref", Activity.MODE_PRIVATE);
        userid = sp.getInt("Test",-1);
        // Navigation zu einem Teilprojekt (später aus einer Recycler View und somit ausgelagert in die Funktion onListItemClick)
        // siehe FavouritesFragment
        // ändern des Arguments title in den Namen des Beispielteilprojektes
        Bundle subProjectTitle = new Bundle();
        subProjectTitle.putString("title", "Beispiel Teilprojekt 1");
        subProjectTitle.putInt("subProjectId", 151);
        //navigieren zum Projekt über einen Button und übergeben der Arguments
        binding.subProjectButton.setOnClickListener(view -> {
            navController.navigate(R.id.action_navigation_project_to_subproject, subProjectTitle);
        });


        tabAdapter = new TabAdapter(this);
        viewPager = binding.viewpager;
        viewPager.setAdapter(tabAdapter);

        titles = new ArrayList<>();
        titles.add("Map");
        titles.add("Distrikte");

        TabLayout tabLayout = binding.tabs;
        viewPager.setUserInputEnabled(false);
        new TabLayoutMediator(tabLayout, viewPager,this).attach();




        projectViewModel.getProjectsFromConsul(projectId).observe(getViewLifecycleOwner(), project -> {
            binding.infoDumb.setText(project.getSlug());
            binding.createdAt.setText(project.createdAt.toString());

            binding.phaseText.setText(project.phase);




        });


        return root;
    }


        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        }


    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        tab.setText(titles.get(position));
    }

    }