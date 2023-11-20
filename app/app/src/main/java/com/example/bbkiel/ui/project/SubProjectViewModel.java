package com.example.bbkiel.ui.project;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.bbkiel.Network.ApiClient;
import com.example.bbkiel.Network.ApiInterface;
import com.example.bbkiel.model.Comment;
import com.example.bbkiel.model.Project;
import com.example.bbkiel.model.SubProject;
import com.example.bbkiel.model.User;
import com.example.bbkiel.model.Votes;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubProjectViewModel extends ViewModel {

    private static final String TAG = "test";
    //Bsp Comments
    MutableLiveData<SubProject> subProject = new MutableLiveData<>();
    MutableLiveData<List<Comment>> comments = new MutableLiveData<>();
    MutableLiveData<Comment> postComment = new MutableLiveData<>();



    public MutableLiveData<SubProject> getSubProject (int id){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SubProject> call = apiInterface.getSubprojectbyid(id);
        call.enqueue(new Callback<SubProject>() {
            @Override
            public void onResponse(Call<SubProject> call, Response<SubProject> response) {
                if (response.isSuccessful()){
                    subProject.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<SubProject> call, Throwable t) {
                Log.e(TAG,"OnFailiure:" + t.getLocalizedMessage());
            }
        });
        return subProject;
    }



    // Methode die aus der Api die Json in die Module Class importiert und anzeigt/return
    public MutableLiveData<List<Comment>> getComments (int id){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Comment>> call = apiInterface.getAllComments(id);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()){
                    comments.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.e(TAG,"OnFailiure:" + t.getLocalizedMessage());
            }
        });
        return comments;
    }




    public void createComment(String comment){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Comment> call = apiInterface.postcomment(comment,null,151,"Budget::Investment",10);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.isSuccessful()){
                    Log.e("CreateComment", "succesfull");
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Log.e("CreateComment", "fehler" + t.toString());

            }
        });
    }



    public void votesub(int id, int investmentids, boolean vote){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Votes> call = apiInterface.postvoteinvesment(id,investmentids,vote);
        call.enqueue(new Callback<Votes>() {
            @Override
            public void onResponse(Call<Votes> call, Response<Votes> response) {
                if (response.isSuccessful()){
                    Log.e("CreateComment", "succesfull");
                }
            }

            @Override
            public void onFailure(Call<Votes> call, Throwable t) {
                Log.e("CreateComment", "fehler" + t.toString());

            }
        });
    }


    public void voteComment(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Votes> call = apiInterface.postvotecomment(10,151,true);
        call.enqueue(new Callback<Votes>() {
            @Override
            public void onResponse(Call<Votes> call, Response<Votes> response) {
                if (response.isSuccessful()){
                    Log.e("CreateComment", "succesfull");
                }
            }

            @Override
            public void onFailure(Call<Votes> call, Throwable t) {
                Log.e("CreateComment", "fehler" + t.toString());

            }
        });
    }



    public void addNewFollow(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<User> call = apiInterface.addnewfollow(10,153);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    Log.e("addnewfollow", "succesfull");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("addnewfollow", "fehler" + t.toString());

            }
        });
    }





}
