package com.mobprog.uas.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobprog.uas.R;
import com.mobprog.uas.model.ModelMovie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.ViewHolder> {

    private final Context context;
    private final List<ModelMovie> list;
    AdapterMovie.OnItemClickListener onItemClickListener;

    public AdapterMovie(Context context, List<ModelMovie> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterMovie.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_movie, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovie.ViewHolder holder, int position) {
        ModelMovie model = list.get(position);
        Picasso.get()
                .load(context.getString(R.string.base_url_image) + model.getUrl())
                .into(holder.imgFilm);
        holder.txtName.setText(model.getNama());
        holder.txtDesc.setText(model.getDesc());
        holder.txtPopular.setText(String.valueOf(model.getPopularity()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(AdapterMovie.OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgFilm;
        private final TextView txtName;
        private final TextView txtDesc;
        private final TextView txtPopular;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFilm = itemView.findViewById(R.id.imgFilm);
            txtName = itemView.findViewById(R.id.txtName);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            txtPopular = itemView.findViewById(R.id.txtPopular);

            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });

        }
    }
}
