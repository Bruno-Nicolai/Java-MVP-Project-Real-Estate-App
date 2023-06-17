package co.imob.version1.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import co.imob.version1.R;
import co.imob.version1.model.Auth;
import co.imob.version1.presenter.MoreContract;
import co.imob.version1.presenter.MorePresenter;
import co.imob.version1.view.activity.ProfileActivity;

public class MoreFragment extends Fragment implements MoreContract.View {

    private MoreContract.Presenter presenter;

    private TextView username;
    private CardView btnProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = new MorePresenter(this);
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        username = view.findViewById(R.id.tv_username);
        displayUsername();
        btnProfile = view.findViewById(R.id.btn_profile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfileActivity();
            }
        });

    }

    @Override
    public void displayUsername() {
        Auth user = presenter.getUser();
        String name = user.getName();
        username.setText(name);
    }

    @Override
    public void goToProfileActivity() {
        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return requireActivity().getApplicationContext();
    }

}