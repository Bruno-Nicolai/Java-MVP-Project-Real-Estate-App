package co.imob.version1.presenter;

import android.content.Context;

import co.imob.version1.model.Product;

public interface DetailsContract {

    interface View {

        public void displayProduct(Product product);

        public void likeAction(boolean isLiked);

        public void showDialog();

        Context getContext();

    }

    interface Presenter {
        public Product getProduct(int productId);

        public boolean changeLike();

    }
}
