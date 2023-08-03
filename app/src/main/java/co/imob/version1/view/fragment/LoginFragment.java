package co.imob.version1.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import co.imob.version1.R;
import co.imob.version1.presenter.LoginContract;
import co.imob.version1.presenter.LoginPresenter;
import co.imob.version1.view.activity.MainActivity;

public class LoginFragment extends Fragment implements LoginContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    private LoginContract.Presenter presenter;

    private CheckBox loginCheckboxRememberMe;

    private EditText loginEtEmail, loginEtPassword;
    private Button loginBtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new LoginPresenter(this);

        loginEtEmail = view.findViewById(R.id.login_et_email);
        loginEtPassword = view.findViewById(R.id.login_et_password);
        loginCheckboxRememberMe = view.findViewById(R.id.login_check_remember_me);

        loginBtn = view.findViewById(R.id.login_btn_next);
        loginBtn.setOnClickListener(view1 -> {

            String email = loginEtEmail.getText().toString();
            String password = loginEtPassword.getText().toString();

            presenter.validateEmail(email);
            presenter.validatePassword(password);
            presenter.loginUser(email, password);

            if (loginCheckboxRememberMe.isChecked()) {
                presenter.saveCredentials(email, password);
            } else {
                presenter.clearCredentials();
            }

        });

        presenter.loadSavedCredentials();

    }

    @Override
    public void setRememberMeChecked(boolean isChecked) {
        loginCheckboxRememberMe.setChecked(isChecked);
    }

    @Override
    public void setEmail(String savedEmail) {
        loginEtEmail.setText(savedEmail);
    }

    @Override
    public void setPassword(String savedPassword) {
        loginEtPassword.setText(savedPassword);
    }

    @Override
    public void showEmailError(String errorMessage) {
        loginEtEmail.setError(errorMessage);
        loginEtEmail.requestFocus();
    }

    @Override
    public void showPasswordError(String errorMessage) {
        loginEtPassword.setError(errorMessage);
        loginEtPassword.requestFocus();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToMainActivity() {
        Activity activity = getActivity();
        if (activity != null) {
            Intent intent = new Intent(activity, MainActivity.class);
            startActivity(intent);
            activity.finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

}