package com.example.bbkiel.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bbkiel.Network.ApiClient;
import com.example.bbkiel.Network.ApiInterface;
import com.example.bbkiel.model.Project;
import com.example.bbkiel.model.SubProject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    MutableLiveData<List<Project>> project = new MutableLiveData<>();
    MutableLiveData<List<SubProject>> subprojectarea = new MutableLiveData<>();


    public MutableLiveData<List<Project>> getAllMainProject (){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Project>> call = apiInterface.getAllProjects();
        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                if (response.isSuccessful()){
                    project.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                Log.e("TAG","OnFailiure:" + t.getLocalizedMessage());
            }
        });
        return project;
    }


    public MutableLiveData<List<SubProject>> getSubprojectarea (){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<SubProject>> call = apiInterface.getsubprojectbylocation(54.3233869,10.1392193);
        call.enqueue(new Callback<List<SubProject>>() {
            @Override
            public void onResponse(Call<List<SubProject>> call, Response<List<SubProject>> response) {
                if (response.isSuccessful()){
                    subprojectarea.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<SubProject>> call, Throwable t) {
                Log.e("TAG","OnFailiure:" + t.getLocalizedMessage());
            }
        });
        return subprojectarea;
    }

}