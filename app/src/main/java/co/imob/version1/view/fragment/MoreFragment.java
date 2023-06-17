package co.imob.version1.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import co.imob.version1.R;
import co.imob.version1.model.Auth;
import co.imob.version1.presenter.MoreContract;
import co.imob.version1.presenter.MorePresenter;

public class MoreFragment extends Fragment implements MoreContract.View {

    private MoreContract.Presenter presenter;

    private TextView username;

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
    }

    @Override
    public void displayUsername() {
        Auth user = presenter.getUser();
        String name = user.getName();
        username.setText(name);
    }

    @Override
    public Context getContext() {
        return requireActivity().getApplicationContext();
    }

}