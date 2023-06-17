package co.imob.version1.presenter;

import android.content.Context;

public interface ProfileContract {

    interface View {

        void logout();

        void goToLoginActivity();

        Context getContext();

    }

    interface Presenter {

        void logoutAction();

    }
}
