package com.mobprog.uas.Api.service;

import com.mobprog.uas.model.ModelDetailMovie;
import com.mobprog.uas.model.ModelMovie;
import com.mobprog.uas.model.ResultMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiMovieInterface {

    @GET("now_playing")
    Call<ResultMovie> getListMovie(@Query("api_key") String apiKey,
                                   @Query("language") String language,
                                   @Query("page") int page);

    @GET("{movie_id}")
    Call<ModelDetailMovie> getDetMovie(@Path(value = "movie_id", encoded = true) int movieId,
                                       @Query("api_key") String apiKey,
                                       @Query("language") String language);
}
