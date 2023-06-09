package co.imob.version1.presenter;

import co.imob.version1.model.Product;
import co.imob.version1.repository.ProductRepository;

public class DetailsPresenter implements DetailsContract.Presenter {

    private DetailsContract.View view;

    public DetailsPresenter(DetailsContract.View view) {
        this.view = view;
    }

    @Override
    public Product getProduct(int productId) {
        return ProductRepository.getInstance().getProduct(productId);
    }
}
