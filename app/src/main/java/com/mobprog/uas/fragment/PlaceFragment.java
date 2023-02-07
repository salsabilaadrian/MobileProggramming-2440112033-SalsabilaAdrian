package com.mobprog.uas.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobprog.uas.R;

import java.util.ArrayList;
import java.util.List;

public class PlaceFragment extends Fragment {

    List<LatLng> listCoord = new ArrayList<>();

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            listCoord.add(new LatLng(-6.193924061113853, 106.78813220277623));
            listCoord.add(new LatLng(-6.20175020412279, 106.78223868546155));
            googleMap.addMarker(new MarkerOptions()
                    .position(listCoord.get(0))
            .title("Cinema CGP Alpha"));
            googleMap.addMarker(new MarkerOptions()
                    .position(listCoord.get(1))
                    .title("Cinema CGP Beta"));

            changeBoundingZoom(listCoord, googleMap);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    public void changeBoundingZoom(final List<LatLng> listLatLng, GoogleMap mMap) {
        final LatLngBounds.Builder builder = new LatLngBounds.Builder();
        try {

            if (listLatLng != null) {
                for (int x = 0; x < listLatLng.size(); x++) {
                    builder.include(listLatLng.get(x));
                }
            }

            LatLngBounds bounds = builder.build();

            final int zoomWidth = getResources().getDisplayMetrics().widthPixels;
            final int zoomHeight = getResources().getDisplayMetrics().heightPixels;
            final int zoomPadding = (int) (zoomWidth * 0.10);

            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, zoomWidth,
                    zoomHeight, zoomPadding));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}