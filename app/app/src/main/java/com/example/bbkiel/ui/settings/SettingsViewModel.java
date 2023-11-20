package com.example.bbkiel.ui.settings;

import android.util.Log;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bbkiel.Network.ApiClient;
import com.example.bbkiel.Network.ApiInterface;
import com.example.bbkiel.model.Comment;
import com.example.bbkiel.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsViewModel extends ViewModel {


   MutableLiveData <User> user = new MutableLiveData<>();


    public MutableLiveData<User> getUserFromNetwork(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<User> call = apiInterface.getUserInfo(10);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    user.postValue(response.body());
                    Log.e("ifsuccesful","ESKLAPPT" + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            Log.e("onFail","das" + t.getLocalizedMessage());
            }
        });

    return user;

    }




    public void changeusername(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<User> call = apiInterface.changeusername(10,"posttest");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                }
                getUserFromNetwork();
                Log.e("ifsuccesfull","ja" + response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("onFail","das" + t.getLocalizedMessage());

            }
        });
    }



}