package co.imob.version1.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import co.imob.version1.R;

public class MorePresenter implements MoreContract.Presenter {

    private MoreContract.View view;

    private SharedPreferences sharedPreferences;

    public MorePresenter(MoreContract.View view) {
        this.view = view;
        sharedPreferences = view.getContext()
                .getSharedPreferences(view.getContext().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

}
