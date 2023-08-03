package co.imob.version1.presenter;

import android.content.Context;

public interface FirstSightContract {
    interface View {

        void showWelcomeScreen();

        void goToLoginActivity();

        void goToSignupActivity();

        Context getContext();

    }

    interface Presenter {

        void checkFirstRun();

    }
}
