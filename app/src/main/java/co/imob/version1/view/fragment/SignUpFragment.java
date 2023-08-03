package co.imob.version1.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import co.imob.version1.R;
import co.imob.version1.presenter.SignUpContract;
import co.imob.version1.presenter.SignUpPresenter;
import co.imob.version1.view.activity.MainActivity;

public class SignUpFragment extends Fragment implements SignUpContract.View {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SignUpFragment() {
    }


    private SignUpContract.Presenter presenter;

    private EditText signupEtName, signupEtEmail, signupEtPassword, signupEtConfirmPassword;
    private Button signupBtn;

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
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new SignUpPresenter(this);
//        if (getActivity() != null) {
//            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
//        }

        signupEtName = view.findViewById(R.id.signup_et_name);
        signupEtEmail = view.findViewById(R.id.signup_et_email);
        signupEtPassword = view.findViewById(R.id.signup_et_password);
        signupEtConfirmPassword = view.findViewById(R.id.signup_et_confirm_password);

        signupBtn = view.findViewById(R.id.signup_btn_next);
        signupBtn.setOnClickListener(view1 -> {

            String name = signupEtName.getText().toString().trim();
            String email = signupEtEmail.getText().toString().trim();
            String password = signupEtPassword.getText().toString().trim();
            String confirmPassword = signupEtConfirmPassword.getText().toString().trim();

            if (presenter.validateSignUpForm(name, email, password, confirmPassword)) {
                presenter.signupUser(email, password);
            }

        });
    }

    @Override
    public void showNameError(String errorMessage) {
        signupEtName.setError(errorMessage);
    }

    @Override
    public void showEmailError(String errorMessage) {
        signupEtEmail.setError(errorMessage);
    }

    @Override
    public void showPasswordError(String errorMessage) {
        signupEtPassword.setError(errorMessage);
    }

    @Override
    public void showConfirmPasswordError(String errorMessage) {
        signupEtConfirmPassword.setError(errorMessage);
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
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

}