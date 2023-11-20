package com.example.bbkiel.ui.favourites;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bbkiel.Network.ApiClient;
import com.example.bbkiel.Network.ApiInterface;
import com.example.bbkiel.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouritesViewModel extends ViewModel {


    Project project;
    MutableLiveData<User> testuser = new MutableLiveData<>();
    MutableLiveData <Project> testproject = new MutableLiveData<>();
    MutableLiveData<List<Project>> projects = new MutableLiveData<>();
    LiveData<List<SubProject>> subProjects = new MutableLiveData<>();
    MutableLiveData<List<SubProject>> favlist = new MutableLiveData<>();
     User User;

    public FavouritesViewModel() {
       getProjectsFromConsul();
       getUserInformation();
     //   List<SubProject> tmpSubs = new ArrayList<>();
     //   tmpSubs.addAll(project.getInvestments());
    //    subProjects.postValue(tmpSubs);
     //   List<Project> tmpProjects = new ArrayList<>();
       // tmpProjects.add(project);
       // projects.getValue(tmpProjects);
    }


    public  MutableLiveData <Project> getProjectsFromConsul(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call <Project> call = apiInterface.getProject(3);
        call.enqueue(new Callback <Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                if (response.isSuccessful()){
             //       project.postValue(response.body());

                    Log.e("geht","Klappt:" +response.body());

                    testproject.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                Log.e("lost","OnFailiure:" + t.getLocalizedMessage());
            }
        });
        return testproject;
    }




    public  MutableLiveData<User> getUserInformation(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<User> call = apiInterface.getUserInfo(10);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    //       project.postValue(response.body());
                    User = new User(response.body());
                    Log.e("geht","Klappt:" +response.body());
                    testuser.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("lost","OnFailiure:" + t.getLocalizedMessage());
            }
        });
        return testuser;
    }





    // Methode die aus der Api die Json in die Module Class importiert und anzeigt/return
    public MutableLiveData<List<SubProject>> getFavlist(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<SubProject>> call = apiInterface.getUserfavorites(10);
        call.enqueue(new Callback<List<SubProject>>() {
            @Override
            public void onResponse(Call<List<SubProject>> call, Response<List<SubProject>> response) {
                if (response.isSuccessful()){
                    favlist.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<SubProject>> call, Throwable t) {
                Log.e("fehler","OnFailiure:" + t.getLocalizedMessage());
            }
        });
        return favlist;
    }


    public LiveData<List<Project>> getProjects() {
        return projects;
    }


    public LiveData<List<SubProject>> getSubProjects() {
        return subProjects;
    }
}