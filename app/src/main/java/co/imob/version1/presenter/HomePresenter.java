package co.imob.version1.presenter;

import java.util.List;

import co.imob.version1.adapter.ProductAdapter;
import co.imob.version1.model.Product;
import co.imob.version1.repository.ProductRepository;
import co.imob.version1.service.ProductService;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void getAllProducts() {
        ProductService.getAllProducts(view.getContext(), () -> {
            List<Product> products = ProductRepository.getInstance().getProducts();
            ProductAdapter adapter = new ProductAdapter(products);
            view.setHomeAdapter(adapter);
        });
    }
}
