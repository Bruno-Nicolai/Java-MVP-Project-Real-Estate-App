package co.imob.version1.service;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginService {

    private DatabaseReference dbRef;

    public LoginService() {
        dbRef = FirebaseDatabase.getInstance().getReference("users");
    }

    public void loginUser(String email, String password, final LoginCallback callback) {
        dbRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        String pwdFromDb = childSnapshot.child("password").getValue(String.class);
                        if (pwdFromDb.equals(password)) {
                            callback.onLoginSuccess();
                            return;
                        }
                    }
                    callback.onLoginFailure("Enter your password again, please.");
                } else {
                    callback.onLoginFailure("Email does not exist.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onLoginFailure("Login request canceled.");
            }
        });
    }
}
