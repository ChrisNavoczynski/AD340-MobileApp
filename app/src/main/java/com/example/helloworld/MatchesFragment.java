package com.example.helloworld;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.core.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.location.LocationListener;

import com.example.helloworld.entity.User;
import com.example.helloworld.viewmodels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment {

    RecyclerView recyclerView;
    public ArrayList mList = new ArrayList();
    private MatchesViewModel viewModel = new MatchesViewModel();
    LocationManager locationManager;
    Location myLocate;
    UserViewModel userViewModel;
    MatchViewAdapter adapter;
    Float maxRange = 16093.44f;

    @Override
    public void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        setHasOptionsMenu(true);

        if(getArguments() != null){
           mList = getArguments().getParcelableArrayList("matches");
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        myLocate = new Location(LocationManager.GPS_PROVIDER);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(false);
        GridLayoutManager myGridLayout = new GridLayoutManager(getActivity(), 2,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(myGridLayout);
        adapter = new MatchViewAdapter(getContext(), mList);
        recyclerView.setAdapter(adapter);
        updateGps(view);
        getSelectedDistance();
        getMatches();

        return view;
    }

    @Override
    public void onPause() {
        viewModel.clear();
        super.onPause();
    }

    private boolean checkLocation() {
        if(!isLocationEnabled()) {
            showAlert();
        }
        return isLocationEnabled();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this.getContext());
        dialog.setTitle(R.string.location_enable)
            .setMessage(getString(R.string.message_for_location))
            .setPositiveButton(R.string.setting_location, (paramDialogInterface, paraInt) -> {
                Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(myIntent);
            })
            .setNegativeButton(R.string.location_cancel, (paramDialogInterface, paramInt) -> {
            });
        dialog.show();
    }

    public void updateGps(View view) {
        if(!checkLocation()) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60 * 1000, 10, locationListener);
        }
    }

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            myLocate.setLatitude(location.getLatitude());
            myLocate.setLongitude(location.getLongitude());
            getMatches();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {}

        @Override
        public void onProviderDisabled(String s) {}
    };

    public void getMatches() {
        viewModel.getMatches(
                (ArrayList<Matches> matches) -> {
                    ArrayList<Matches> pastMax = new ArrayList<>();
                    float[] distance = new float[1];
                    for(Matches match : matches){
                        Location.distanceBetween(Double.parseDouble(match.lat), Double.parseDouble(match.longitude), myLocate.getLatitude(), myLocate.getLongitude(), distance);
                        if(distance[0] > maxRange){
                            pastMax.add(match);
                        }
                    }
                    matches.removeAll(pastMax);
                    adapter.setMatchesList(matches);
                    adapter.notifyDataSetChanged();
                });
    }

    public void getSelectedDistance() {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        Float one = 1609.34f;
        Float five = 8046.72f;
        Float ten = 16093.44f;
        Float fifteen = 2414.02f;
        Float twenty = 3218.69f;
        Float thirty = 4828.03f;
        Float forty = 6437.38f;
        Float fifty = 80467.2f;
        Float hundred = 160934.4f;

        final Observer<List<User>> getUserObserver = (users -> {
            if (users == null || users.size() <= 0) {
                return;
            }

            User setDist = users.get(users.size() - 1);
            if (setDist == null) {
                return;
            }
            Float range = 0f;
            switch (setDist.getSearchMiles()) {
                case 0:
                    range = one;
                    break;
                case 1:
                    range = five;
                    break;
                case 2:
                    range = ten;
                    break;
                case 3:
                    range = fifteen;
                    break;
                case 4:
                    range = twenty;
                    break;
                case 5:
                    range = thirty;
                    break;
                case 6:
                    range = forty;
                    break;
                case 7:
                    range = fifty;
                    break;
                case 8:
                    range = hundred;
                    break;
            }
            setRanch(range);
        });
        userViewModel.loadSettings(this.getContext()).observe(this.getViewLifecycleOwner(),getUserObserver);
    }

    public void setRanch(Float f) {
        this.maxRange = f;
    }
}

