package com.example.bbkiel.ui.project;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.DirectedLocationOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import com.example.bbkiel.R;
import com.example.bbkiel.databinding.FragmentMapsBinding;
import com.example.bbkiel.databinding.FragmentProjectBinding;
import com.example.bbkiel.ui.home.HomeViewModel;

import java.util.ArrayList;


public class MapsFragment extends Fragment {

    MapView osm;

    private FragmentMapsBinding binding;
    private ProjectViewModel projectViewModel;



    @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                       @Nullable Bundle savedInstanceState) {

        Log.d("sopro", "onCreate");



        binding = FragmentMapsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        projectViewModel =
                new ViewModelProvider(this).get(ProjectViewModel.class);

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

        projectViewModel.getProjectsFromConsul(3).observe(getViewLifecycleOwner(), project ->{

            for (int i = 0;i < project.getInvestments().size();i++){
                ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
                items.add(new OverlayItem(project.getInvestments().get(i).getTitle(), project.getInvestments().get(i).getDescription(), new GeoPoint(project.getInvestments().get(i).getLatitude(),project.getInvestments().get(i).getLongitude())));

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

        return root;
    }

    public void onResume(){
        super.onResume();

        binding.map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
        Log.d("sopro","wird aufgerufen");

    }

    public void onPause(){
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
     //   Configuration.getInstance().save(this.getContext(), prefs);
        Log.d("sopro","pause");
        binding.map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }




}