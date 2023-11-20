package com.example.bbkiel.Network;

import com.example.bbkiel.model.Adresslocation;
import com.example.bbkiel.model.Comment;
import com.example.bbkiel.model.Project;
import com.example.bbkiel.model.SubProject;
import com.example.bbkiel.model.User;
import com.example.bbkiel.model.Votes;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    /////////////////////
    //      GET      //
    ///////////////////

    @GET("projects")
    Call<List<Project>> getAllProjects();

    // 151
    @GET("commentbyid/{id}")
    Call<List<Comment>> getAllComments(@Path("id") int projectid);

    // 3
    @GET("projectbyid/{id}")
    Call<Project> getProject(@Path("id") int projectid);

    //10
    @GET("user/{id}")
    Call<User> getUserInfo(@Path("id") int userid);

    //151
    @GET("investmentbyid/{id}")
    Call<SubProject> getSubprojectbyid(@Path("id") int subprojectid);

    @GET("investmentbyarea/{latitude}/{longitude}")
    Call<List<SubProject>> getsubprojectbylocation(@Path("latitude") double latitude,
                                                   @Path("longitude") double longitude);


    //Favorieten eines User bekommen
    @GET("getfav/{id}")
    Call<List<SubProject>> getUserfavorites(@Path("id") int id);

    /////////////////////
    //      Post      //
    ///////////////////


    //Post für name ändern
    @POST("changeusername")
    Call<User> changeusername(
            @Query("id") int id,
            @Query("username") String username
    );

    //Post für favorietien abgeben
    @POST("newfollow")
    Call<User> addnewfollow(@Query("userId") int userId,
                            @Query("budgetInvestmentId") int budgetInvestmentId );

    //Post für bewertung
    @POST("voteInvesment")
    Call<Votes> postvoteinvesment(
            @Query("id") int userid,
            @Query("budgetinvestmentid") int investmentids,
            @Query("flag") boolean vote
    );

    @POST("voteComment")
    Call<Votes> postvotecomment (
            //kommentar id
            @Query("id") int commentid,
            //das ist die userid
            @Query("userid") int userid,
            //art des votes true = upvote, false ist ein downvote
            @Query("flag") boolean vote
    );

    // Levi plz post request
    @POST("reverse?format=jsonv2&")
    Call<Adresslocation> getpostcode(@Query("lat") double latitude,
                                     @Query("lon")double longitude);


    //Post für kommentar
    @POST("comment")
    Call<Comment> postcomment(
            @Query("body") String body,
            @Query("ancestry") String ancestry,
            @Query("commentableId") int commentableId,
            @Query("commentableType") String commentableType,
            @Query("userId") int userId
    );

}
