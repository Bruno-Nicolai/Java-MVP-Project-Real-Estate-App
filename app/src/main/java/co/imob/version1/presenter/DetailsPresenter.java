package co.imob.version1.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import co.imob.version1.R;
import co.imob.version1.model.Product;
import co.imob.version1.repository.ProductRepository;

public class DetailsPresenter implements DetailsContract.Presenter {

    private DetailsContract.View view;
    private SharedPreferences sharedPreferences;
    private boolean isLiked = false;

    public DetailsPresenter(DetailsContract.View view) {
        this.view = view;
        sharedPreferences = view.getContext()
                .getSharedPreferences(view.getContext().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    @Override
    public Product getProduct(int productId) {
        return ProductRepository.getInstance().getProduct(productId);
    }

    @Override
    public boolean changeLike() {
        isLiked = !isLiked;
        if (isLiked) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLiked", isLiked);
            editor.apply();
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("isLiked");
            editor.apply();
        }
        return isLiked;
    }
}
