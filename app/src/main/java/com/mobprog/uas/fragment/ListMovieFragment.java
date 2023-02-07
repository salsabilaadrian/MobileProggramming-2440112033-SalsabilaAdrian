package com.mobprog.uas.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobprog.uas.Api.ApiClient;
import com.mobprog.uas.Api.service.ApiMovieInterface;
import com.mobprog.uas.DetailMovieActivity;
import com.mobprog.uas.R;
import com.mobprog.uas.adapter.AdapterMovie;
import com.mobprog.uas.model.ModelMovie;
import com.mobprog.uas.model.ResultMovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMovieFragment extends Fragment implements AdapterMovie.OnItemClickListener {

    private ApiMovieInterface apiMovieInterface;
    private RecyclerView recyclerMovie;
    private List<ModelMovie> list = new ArrayList<>();

    public ListMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_move, container, false);

        recyclerMovie = view.findViewById(R.id.recyclerMovie);

        initApi();

        return view;
    }

    private void initApi() {
        apiMovieInterface = ApiClient.getClient().create(ApiMovieInterface.class);
        setupData();
    }

    private void setupData() {
        Call<ResultMovie> call = apiMovieInterface.getListMovie(requireActivity().getString(R.string.tmdb_api_key), "en-US", 1);
        call.enqueue(new Callback<ResultMovie>() {
            @Override
            public void onResponse(Call<ResultMovie> call, Response<ResultMovie> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200 && response.body() != null) {
                        list = response.body().getResults();
                        recyclerSetup();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultMovie> call, Throwable t) {

            }
        });
    }

    private void recyclerSetup() {
        AdapterMovie adapterMovie = new AdapterMovie(requireActivity(), list);
        adapterMovie.setOnItemClickListener(this);
        recyclerMovie.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerMovie.setAdapter(adapterMovie);
        recyclerMovie.setNestedScrollingEnabled(true);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(requireActivity(), DetailMovieActivity.class);
        intent.putExtra("id", list.get(position).getId());
        startActivity(intent);
    }
}