package com.mobprog.uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobprog.uas.Api.ApiClient;
import com.mobprog.uas.Api.service.ApiMovieInterface;
import com.mobprog.uas.model.ModelDetailMovie;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieActivity extends AppCompatActivity {

    private ApiMovieInterface apiMovieInterface;

    private LinearLayout linearGenre;
    private Toolbar my_toolbar;
    private ImageView imgMovieDet;
    private TextView txtName;
    private TextView txtBudget;
    private TextView txtPopularity;
    private TextView txtRilis;
    private TextView txtRevenue;
    private TextView txtProdComp;
    private TextView txtDesc;

    private int id;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        my_toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        my_toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        linearGenre = findViewById(R.id.linearGenre);
        imgMovieDet = findViewById(R.id.imgMovieDet);
        txtName = findViewById(R.id.txtName);
        txtBudget = findViewById(R.id.txtBudget);
        txtPopularity = findViewById(R.id.txtPopularity);
        txtRilis = findViewById(R.id.txtRilis);
        txtRevenue = findViewById(R.id.txtRevenue);
        txtProdComp = findViewById(R.id.txtProdComp);
        txtDesc = findViewById(R.id.txtDesc);

        id = (int) getIntent().getSerializableExtra("id");

        initApi();

        Button btnPesan = findViewById(R.id.btnPesan);
        btnPesan.setOnClickListener(v -> {
            dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_order);

            EditText edtNama = dialog.findViewById(R.id.edtNama);
            EditText edtNoHp = dialog.findViewById(R.id.edtNoHp);
            android.widget.EditText edtNik = dialog.findViewById(R.id.edtNik);

            Button btnBatal = dialog.findViewById(R.id.btnBatalkan);
            Button btnOk = dialog.findViewById(R.id.btnOk);

            btnBatal.setOnClickListener(v1 -> dialog.dismiss());
            btnOk.setOnClickListener(v1 -> {
                if (!edtNama.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Terimakasih " + edtNama.getText().toString() + " sudah memesan", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            });

            dialog.show();
        });

    }

    private void initApi() {
        apiMovieInterface = ApiClient.getClient().create(ApiMovieInterface.class);
        setupData();
    }

    private void setupData() {
        Call<ModelDetailMovie> call = apiMovieInterface.getDetMovie(id, getBaseContext().getString(R.string.tmdb_api_key), "en-US");
        call.enqueue(new Callback<ModelDetailMovie>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ModelDetailMovie> call, Response<ModelDetailMovie> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200 && response.body() != null) {
                        ModelDetailMovie data = response.body();
                        my_toolbar.setTitle(data.getName());
                        Picasso.get()
                                .load(getBaseContext().getString(R.string.base_url_image) + data.getUrl())
                                .into(imgMovieDet);
                        txtName.setText(data.getName());
                        Locale ina = new Locale("id", "ID");
                        Currency rupiah = Currency.getInstance(ina);
                        NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(ina);
                        txtBudget.setText("Budget : " + rupiahFormat.format(data.getBudget()));
                        txtPopularity.setText("Populariy : " +data.getPopularity());
                        txtRilis.setText("Tanggal Rilis : " + data.getRelease());
                        txtRevenue.setText("Keuntungan : " + rupiahFormat.format(data.getRevenue()));

                        for (int x = 0; x < data.getGenres().size(); x++) {
                            @SuppressLint("InflateParams")
                            View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_genre, null);
                            TextView txtGenre = view.findViewById(R.id.txtGenre);
                            txtGenre.setText(data.getGenres().get(x).getName() + ", ");
                            linearGenre.addView(view);
                        }

                        txtProdComp.setText("Perusahaan Produksi : " + data.getProdCompanies().get(0).getName());
                        txtDesc.setText(data.getDesc());

                    }
                }
            }

            @Override
            public void onFailure(Call<ModelDetailMovie> call, Throwable t) {

            }
        });
    }

}