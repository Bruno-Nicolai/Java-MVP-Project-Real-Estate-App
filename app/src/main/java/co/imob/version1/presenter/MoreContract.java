package co.imob.version1.presenter;

import android.content.Context;

import com.google.android.material.chip.Chip;

import java.util.List;

import co.imob.version1.adapter.ProductAdapter;
import co.imob.version1.model.Auth;

public interface MoreContract {

    interface View {

        void displayUsername();

        void goToProfileActivity();

        Context getContext();

    }

    interface Presenter {

        Auth getUser();

    }
}
