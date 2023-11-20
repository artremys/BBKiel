package com.example.bbkiel.ui.home;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bbkiel.R;
import com.example.bbkiel.databinding.FragmentHomeBinding;
import com.example.bbkiel.model.SubProject;
import com.example.bbkiel.ui.project.CommentAdapter;
import com.example.bbkiel.ui.project.ProjectAdapter;
import com.example.bbkiel.ui.project.SubProjectViewModel;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

import hilt_aggregated_deps._dagger_hilt_android_internal_modules_ApplicationContextModule;

public class HomeFragment extends Fragment implements ProjectAdapter.ListItemClickListener{

    private HomeViewModel mViewModel;
    private NavController navController;
    private FragmentHomeBinding binding;
    private ProjectAdapter projectAdapter;
    private ArrayList<OverlayItem> items = new ArrayList<>();
    private ArrayList<SubProject> subProjects = new ArrayList<>();

    public static HomeFragment newInstance() {

        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //permission abgfrage, falls nicht erlaubt dann wird gefragt über PermissionLauncher
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            requestPermissionLauncher.launch(Manifest.permission.INTERNET);
        }
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        }


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        mViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        NavHostFragment navHostFragment =
                (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        this.navController = navHostFragment.getNavController();

//kopiert vielleicht nicht notwendig
        Context ctx = this.getContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        MapView osm = binding.map;

        osm.setTileSource(TileSourceFactory.MAPNIK);
        osm.setMultiTouchControls(true);

        IMapController mapController = osm.getController();
        mapController.setZoom(12.5);
        GeoPoint startPoint = new GeoPoint(54.329520, 10.137875);
        mapController.setCenter(startPoint);

        MyLocationNewOverlay mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(this.getContext()),osm);
        mLocationOverlay.enableMyLocation();
        osm.getOverlays().add(mLocationOverlay);


        //Versuch die Teilprojekte auf Karteanzuzeigen
        //your items

        mViewModel.getSubprojectarea().observe(getViewLifecycleOwner(), subProject -> {
            //items.add(new OverlayItem("Title", "Description", new GeoPoint(0.0d,0.0d)));
            for (int i= 0;i<subProject.size();i++){
               // subProjects.add(subProject.get(i));
                items.add(new OverlayItem(subProject.get(i).getTitle(), subProject.get(i).getDescription(), new GeoPoint(subProject.get(i).getLatitude(),subProject.get(i).getLongitude())));

                int counter = i;
                ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(items,
                        new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {


                    @Override
                            public boolean onItemSingleTapUp(final int index, final OverlayItem item) {

                                /*int id = subProject.get(counter).getId();
                                Bundle payload = new Bundle();
                                payload.putInt("subProjectId",id);
                                String title = subProject.get(counter).getTitle();
                                navController.navigate(R.id.action_navigate_home_to_subproject,payload);*/

                                return true;
                            }
                            @Override
                            public boolean onItemLongPress(final int index, final OverlayItem item) {
                                return false;
                            }


                        }, this.getContext());
                mOverlay.setFocusItemsOnTap(true);





                osm.getOverlays().add(mOverlay);


            }
        });

         // Lat/Lon decimal degrees
        items.add(new OverlayItem("Title", "Description", new GeoPoint(0.0d,0.0d)));

//the overlay


        // Navigation zu einem Projekt (später aus einer Recycler View und somit ausgelagert in die Funktion onListItemClick)
        // siehe FavouritesFragment
        // ändern des Arguments title in den Namen des Beispielprojektes
        Bundle projectTitle = new Bundle();
        projectTitle.putString("title", "Beispiel Projekt 1");
        //navigieren zum Projekt über einen Button und übergeben der Arguments
        binding.projectButton.setOnClickListener(view -> {
            navController.navigate(R.id.action_navigation_home_to_navigation_project, projectTitle);
        });



        //Setup ReccyclerView für Projekte
        //hier wird die RecyclerView an die RecyclerView in der XML datei gebindet
        RecyclerView projectRecyclerView = binding.recyclerView2;
        projectRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        projectRecyclerView.setHasFixedSize(true);

        //setup adapter für Projekte
        projectAdapter = new ProjectAdapter(this);
        projectRecyclerView.setAdapter(projectAdapter);

        //get Liste von Projekten aus dem Viewmodel

        mViewModel.getAllMainProject().observe(getViewLifecycleOwner(), projects -> {

            projectAdapter.setProjects(projects);
        });



        SharedPreferences sp = this.getActivity().getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("Test", 10);
        editor.commit();


        return root;
    }

    public void onResume(){
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        binding.map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    public void onPause(){
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        binding.map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onListItemClick(int position) {
        Bundle payload = new Bundle();
        payload.putSerializable("projectId", projectAdapter.getProjectById(position).getId());

        // umbennen des Labels im Fragment Projekt (über das Argument title)
        String title = projectAdapter.getProjectById(position).getName();
        payload.putString("title", title);

        //navigieren zum Projekt
        this.navController.navigate(R.id.action_navigation_home_to_navigation_project, payload);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //PermissionLauncher um Permissions abzufragen die noch nicht erteilt wurden
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            });

}