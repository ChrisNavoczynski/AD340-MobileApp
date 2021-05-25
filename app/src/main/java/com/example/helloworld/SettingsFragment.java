package com.example.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.helloworld.entity.User;
import com.example.helloworld.viewmodels.UserViewModel;

import java.util.List;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private Spinner reminderTime;
    private Spinner searchMiles;
    private CheckBox accountStatus;
    private Spinner ageRangeMin;
    private Spinner ageRangeMax;
    private Spinner genderId;
    private Button updateBtn;


    private UserViewModel userViewModel;

    @Override
    public void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        updateBtn = view.findViewById(R.id.updateButton);
        updateBtn.setOnClickListener(this);

        reminderTime = view.findViewById(R.id.reminderTime);
        searchMiles = view.findViewById(R.id.searchMiles);
        accountStatus = view.findViewById(R.id.accountStatus);
        ageRangeMin = view.findViewById(R.id.ageRangeMin);
        ageRangeMax = view.findViewById(R.id.ageRangeMax);
        genderId = view.findViewById(R.id.genderId);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        final Observer<List<User>> getUsersObserver = newSettings -> {
            if (newSettings == null || newSettings.size() <= 0) {
                return;
            }
            User user = newSettings.get(newSettings.size()-1);

            if (user == null) {
                return;
            }
            reminderTime.setSelection(user.getRemindTime());
            searchMiles.setSelection(user.getSearchMiles());
            accountStatus.setChecked(user.getAccountStatus());
            ageRangeMin.setSelection(user.getAgeRangeMin());
            ageRangeMax.setSelection(user.getAgeRangeMax());
            genderId.setSelection(getIndex(genderId,user.getGenderId()));
        };
        userViewModel.loadSettings(this.getContext()).observe(this.getViewLifecycleOwner(), getUsersObserver);
        return view;
    }

    public void updateSettings(View view) {
        User update = new User();
        update.setRemindTime(reminderTime.getSelectedItemPosition());
        update.setSearchMiles(searchMiles.getSelectedItemPosition());
        update.setAccountStatus(accountStatus.isChecked());
        update.setAgeRangeMin(ageRangeMin.getSelectedItemPosition());
        update.setAgeRangeMax(ageRangeMax.getSelectedItemPosition());
        update.setGenderId(genderId.getSelectedItem().toString());

        userViewModel.updateSettings(this.getContext(), update);
        Toast.makeText(getActivity(), "You have updated your settings", Toast.LENGTH_SHORT).show();
    }

    private int getIndex(Spinner spin, String genId) {
        for (int i = 0; i < spin.getCount(); i++) {
            if (spin.getItemAtPosition(i).toString().equalsIgnoreCase(genId)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void onClick(View view) {
        updateSettings(view);
    }

}
