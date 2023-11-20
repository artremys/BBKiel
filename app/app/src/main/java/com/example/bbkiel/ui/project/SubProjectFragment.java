package com.example.bbkiel.ui.project;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bbkiel.R;
import com.example.bbkiel.databinding.FragmentSubProjectBinding;
import com.example.bbkiel.model.Comment;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.List;
import java.util.ArrayList;

//imports for customized Toolbar
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class SubProjectFragment extends Fragment implements CommentAdapter.ListItemClickListener {

    private SubProjectViewModel spViewModel;
    private NavController navController;
    private FragmentSubProjectBinding binding;
    private CommentAdapter commentAdapter;
    private List<Comment> comments = new ArrayList<>();
    int subprojectId;

    public static SubProjectFragment newInstance() {
        return new SubProjectFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //set customized toolbar
        setHasOptionsMenu(true);
        // speichern der Payload
        // Paylaod MUSS bei klick on Button und der Navigation mitgegeben werden, ansonsten Appabsturz
//         projectId = (Integer) getArguments().get("subProjectId");


        spViewModel =
                new ViewModelProvider(this).get(SubProjectViewModel.class);


        binding = FragmentSubProjectBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        NavHostFragment navHostFragment =
                (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        this.navController = navHostFragment.getNavController();




        //Setup ReccyclerView für Projekte
        //hier wird die RecyclerView an die RecyclerView in der XML datei gebindet
        RecyclerView commentRecyclerView = binding.includeCommentCardview.commentRecyclerView;
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        commentRecyclerView.setHasFixedSize(true);

        //setup adapter für Projekte
        commentAdapter = new CommentAdapter(this);
        commentRecyclerView.setAdapter(commentAdapter);


        //comments.addAll(spViewModel.getComments().getValue());

        //get Liste von Projekten aus dem Viewmodel
        spViewModel.getComments(subprojectId).observe(getViewLifecycleOwner(), comments -> {
            commentAdapter.setComments(comments);
        });


        spViewModel.getSubProject(subprojectId).observe(getViewLifecycleOwner(), subProject->{
            binding.descriptionTextView.setText(subProject.getDescription());
        });

        //Funktion um zu Kommentieren
        final EditText editText = binding.includeCommentCardview.commentWrite.commentTextInput;


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if(i == EditorInfo.IME_ACTION_DONE) {
                    spViewModel.createComment(textView.getText().toString());

                }
                return false;
            }
        });

        binding.include.likeProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     spViewModel.votesub(10, 151,true);
           //     spViewModel.getComments();
            }
        });

        //map
        Context ctx = this.getContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        binding.map.setTileSource(TileSourceFactory.MAPNIK);


        MapView osm = binding.map;

        osm.setTileSource(TileSourceFactory.MAPNIK);
        osm.setMultiTouchControls(true);

        IMapController mapController = osm.getController();
        mapController.setZoom(12.5);
        GeoPoint startPoint = new GeoPoint(54.329520, 10.137875);
        mapController.setCenter(startPoint);

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
        spViewModel = new ViewModelProvider(this).get(SubProjectViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onListItemClick(int postion) {

    }

    /*
    Next two methods set a customized tool in the fragment and define what
    will happen if the action is clicked.
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.top_app_bar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.favorite:
                Toast.makeText(super.getActivity(), "Well, Hello there", Toast.LENGTH_SHORT).show();
                spViewModel.addNewFollow();
                item.setIcon(R.drawable.ic_favourite_clicked);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}