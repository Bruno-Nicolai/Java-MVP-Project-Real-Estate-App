package co.imob.version1.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import co.imob.version1.R;
import co.imob.version1.adapter.ProductAdapter;
import co.imob.version1.model.Auth;
import co.imob.version1.model.Product;
import co.imob.version1.repository.ProductRepository;
import co.imob.version1.service.ProductService;

public class MorePresenter implements MoreContract.Presenter {

    private MoreContract.View view;

    private SharedPreferences sharedPreferences;

    public MorePresenter(MoreContract.View view) {
        this.view = view;
        sharedPreferences = view.getContext()
                .getSharedPreferences(view.getContext().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    @Override
    public Auth getUser() {
        String name = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");

        return new Auth(name, email, password);
    }

}
