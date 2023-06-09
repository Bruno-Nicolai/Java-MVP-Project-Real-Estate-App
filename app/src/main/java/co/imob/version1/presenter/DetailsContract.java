package co.imob.version1.presenter;

import android.content.Context;

import co.imob.version1.adapter.ProductAdapter;
import co.imob.version1.model.Product;

public interface DetailsContract {

    interface View {

        public void displayProduct(Product product);

//        public void setDetailsViewPagerAdapter(ProductAdapter adapter);

    }

    interface Presenter {
        public Product getProduct(int productId);
    }
}
