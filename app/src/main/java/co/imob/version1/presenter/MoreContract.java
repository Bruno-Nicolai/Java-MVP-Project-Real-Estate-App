package co.imob.version1.presenter;

import android.content.Context;

public interface MoreContract {

    interface View {

        void displayUsername();

        void goToProfileActivity();

        Context getContext();

    }

    interface Presenter {

    }
}
