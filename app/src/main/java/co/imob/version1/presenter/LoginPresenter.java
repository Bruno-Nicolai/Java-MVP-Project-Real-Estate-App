package co.imob.version1.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import co.imob.version1.R;
import co.imob.version1.model.Auth;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    private DatabaseReference dbRef;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        dbRef = FirebaseDatabase.getInstance().getReference("users");
    }

    @Override
    public void loginUser(String email, String password) {
        dbRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String pwdFromDb = snapshot.child(email).child("password").getValue(String.class);

                    if (!Objects.equals(pwdFromDb, password)) {
                        view.goToMainActivity();
                    } else {
                        view.showPasswordError("Enter your password again, please.");
                    }
                } else {
                    view.showEmailError("Email does not exist.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void validateEmail(String email) {
        if (email.isEmpty()) {
            view.showEmailError("Invalid Email Address.");
        } else {
            view.showEmailError(null);
        }
    }

    @Override
    public void validatePassword(String password) {
        if (password.isEmpty()) {
            view.showPasswordError("Invalid Password!");
        } else {
            view.showPasswordError(null);
        }
    }

    @Override
    public void onDestroy() {
    }
}
