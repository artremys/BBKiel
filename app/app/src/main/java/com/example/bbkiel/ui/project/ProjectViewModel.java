package com.example.bbkiel.ui.project;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bbkiel.Network.ApiClient;
import com.example.bbkiel.Network.ApiClientadress;
import com.example.bbkiel.Network.ApiInterface;
import com.example.bbkiel.model.Adresslocation;
import com.example.bbkiel.model.Comment;
import com.example.bbkiel.model.Project;
import com.example.bbkiel.model.SubProject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectViewModel extends ViewModel {
    private HashMap<String,String> districts = new HashMap<>();
    private MutableLiveData<Project> project = new MutableLiveData<>();
    private MutableLiveData<Adresslocation> adresslocation = new MutableLiveData<>();


    // füllen der Liste von Distrikten, direkt mit Namen
    private void createDistricts(){
       // List<String> districts = new ArrayList<>();
        districts.put("24103"," - Kiel-Damperhof");
        districts.put("24105"," - Kiel-Brunswick");
        districts.put("24106"," - Kiel-Wik");
        districts.put("24107"," - Kiel-Suchsdorf");
        districts.put("24109"," - Kiel-Mettenhof");
        districts.put("24111"," - Kiel-Russee");
        districts.put("24113"," - Kiel-Hassee");
        districts.put("24114"," - Kiel-Südfriedhof");
        districts.put("24116"," - Kiel-Schreventeich");
        districts.put("24118"," - Kiel-Ravensberg");
        districts.put("24143"," - Kiel-Gaarden-Ost");
        districts.put("24145"," - Kiel-Neumeimersdorf");
        districts.put("24146"," - Kiel-Elmschenhagen-Nord");
        districts.put("24148"," - Kiel-Wellingdorf");
        districts.put("24149"," - Kiel-Neumühlen");
        districts.put("24159"," - Kiel-Friedrichsort");
        districts.put("24161"," - Kiel-Altenholz");
        districts.put("24161"," - Kiel-Altenholz2");
     //   districts.postValue(tmpDistricts);
    }


    public  MutableLiveData<Project> getProjectsFromConsul(int id){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Project> call = apiInterface.getProject(3);
        call.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                if (response.isSuccessful()){
                    //       project.postValue(response.body());

                    Log.e("geht","Klappt:" +response.body());

                    project.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                Log.e("lost","OnFailiure:" + t.getLocalizedMessage());
            }
        });
        return project;
    }



    public HashMap<String,List<SubProject>> getSubProjectsInAllDistricts(){
        createDistricts();
        getPostCode();

        HashMap<String, List<SubProject>> subProjectsInAllDistricts = new HashMap<String, List<SubProject>>();
        List<SubProject> subProjects = new ArrayList<>();
 //       subProjects.addAll(project.getValue().getInvestments());

        for(int i = 0 ; i < subProjects.size(); i++){

            String currentDistrict = districts.get(i).substring(0,4);
            List<SubProject> currentSubProjects = new ArrayList<>();





            if(currentSubProjects.size() != 0){
                subProjectsInAllDistricts.put(districts.get(i),currentSubProjects);
            }
        }

        return subProjectsInAllDistricts;
    }


    // Methode die aus der Api die Json in die Module Class importiert und anzeigt/return
    public MutableLiveData<Adresslocation> getPostCode(){
        ApiInterface apiInterface = ApiClientadress.getClient().create(ApiInterface.class);
        Call<Adresslocation> call = apiInterface.getpostcode(54.3233869,10.1392193);
        call.enqueue(new Callback<Adresslocation>() {
            @Override
            public void onResponse(Call<Adresslocation> call, Response<Adresslocation> response) {
                if (response.isSuccessful()){
                    adresslocation.postValue(response.body());
                    Log.e("geht","drinnen" + response.body().getAddress().getPostcode());
                }
            }
            @Override
            public void onFailure(Call<Adresslocation> call, Throwable t) {
                Log.e("TAG","OnFailiure:" + t.getLocalizedMessage());
            }
        });
        return adresslocation;
    }


}