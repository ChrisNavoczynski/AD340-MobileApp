package com.example.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ProfileFragment extends Fragment {
    private SignInActivity.AttachSignIn attachSignIn;
    private FragmentManager fragmanage;
    private TextView name;
    private TextView age;
    private TextView asId;
    private TextView seekId;
    private TextView employ;
    private TextView describe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.SignedIn);
        age = view.findViewById(R.id.age);
        asId = view.findViewById(R.id.identified_as);
        seekId = view.findViewById(R.id.seeking);
        employ = view.findViewById(R.id.occupation);
        describe = view.findViewById(R.id.about);

        name.setText(this.attachSignIn.name);
        age.setText(this.attachSignIn.age);
        asId.setText(this.attachSignIn.asId);
        seekId.setText(this.attachSignIn.seekId);
        employ.setText(this.attachSignIn.employ);
        describe.setText(this.attachSignIn.describe);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constants.PROFILE_NAME, attachSignIn.name);
        outState.putString(Constants.AGE, attachSignIn.age);
        outState.putString(Constants.ID_AS, attachSignIn.asId);
        outState.putString(Constants.SEEKING,attachSignIn.seekId);
        outState.putString(Constants.OCCUPATION,attachSignIn.employ);
        outState.putString(Constants.ABOUT_ME,attachSignIn.describe);
    }

    void setAttachSignIn(SignInActivity.AttachSignIn attach) {
        this.attachSignIn = attach;
    }
}
