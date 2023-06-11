package co.imob.version1.service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.imob.version1.model.Auth;

public class SignUpService {

    private DatabaseReference dbRef;

    public SignUpService() {
        dbRef = FirebaseDatabase.getInstance().getReference("users");
    }

    public void registerUser(String name, String email, String password, final SignUpCallback callback) {
        Auth user = new Auth(name, email, password);
        dbRef.child(name).setValue(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onRegisterSuccess("You are now a new member, " + name + "!");
                    }
                    else callback.onRegisterFailure("Registration failed. Please, try again.");
                });

    }
}
