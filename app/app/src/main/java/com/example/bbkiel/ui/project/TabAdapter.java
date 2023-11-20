package com.example.bbkiel.ui.project;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bbkiel.model.SubProject;

public class TabAdapter extends FragmentStateAdapter {


    public TabAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override  // Funktion um Fragmente anzuzeigen, abhängig von psoition
    public Fragment createFragment(int position) {
        switch(position) {
            case 0:
                Fragment mapsFragment = new MapsFragment();
                return mapsFragment;
            case 1:
                Fragment districtFragment = new DistrictFragment();
                return districtFragment;
            default:
                Fragment mapsFragment2 = new MapsFragment();
                return mapsFragment2;
       }
    }

    @Override
    public int getItemCount() {
        return 2;
    }



}
