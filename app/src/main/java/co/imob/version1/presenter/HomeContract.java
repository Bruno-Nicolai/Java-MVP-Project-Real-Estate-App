package co.imob.version1.presenter;

import android.content.Context;

import co.imob.version1.adapter.ProductAdapter;

public interface HomeContract {

    interface View {

        public Context getContext();

        public void setHomeAdapter(ProductAdapter adapter);

    }

    interface Presenter {
        public void getAllProducts();
    }
}
