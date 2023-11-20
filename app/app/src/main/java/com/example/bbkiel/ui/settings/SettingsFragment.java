package com.example.bbkiel.ui.settings;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.navigation.NavController;
import com.example.bbkiel.databinding.FragmentSettingsBinding;
import com.example.bbkiel.R;
import com.example.bbkiel.model.User;

public class SettingsFragment extends Fragment {

    private SettingsViewModel mViewModel;
    private FragmentSettingsBinding binding;
    private NavController navController;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        settingsViewModel.getUserFromNetwork().observe(getViewLifecycleOwner(), user -> {
           binding.TexEditUserName.setText(user.getUsername());

        });

        NavHostFragment navHostFragment =
                (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        this.navController = navHostFragment.getNavController();


   final EditText editText = binding.TexEditUserName;

   editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
       @Override
       public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {

           editText.setText(textView.getText().toString());

           return false;
       }
   });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        // TODO: Use the ViewModel
    }

}