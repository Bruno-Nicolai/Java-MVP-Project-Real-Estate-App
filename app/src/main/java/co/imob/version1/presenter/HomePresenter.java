package co.imob.version1.presenter;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import co.imob.version1.R;
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
            ProductAdapter adapter = new ProductAdapter(products, getSelectedChipContent());
            view.setHomeAdapter(adapter);
        });
    }

    @Override
    public List<String> getSelectedChipContent() {

        Chip chipHouse = view.getChip(R.id.chip_category1);
        Chip chipApartment = view.getChip(R.id.chip_category2);
        Chip chipGarage = view.getChip(R.id.chip_category3);
        Chip chipOffice = view.getChip(R.id.chip_category4);
        Chip chipLandLots = view.getChip(R.id.chip_category5);

        List<String> selectedChipsTexts = new ArrayList<>();

        if (chipHouse.isChecked()) {
            selectedChipsTexts.add(chipHouse.getText().toString());
        } else if (chipApartment.isChecked()) {
            selectedChipsTexts.add(chipApartment.getText().toString());
        } else if (chipGarage.isChecked()) {
            selectedChipsTexts.add(chipGarage.getText().toString());
        } else if (chipOffice.isChecked()) {
            selectedChipsTexts.add(chipOffice.getText().toString());
        } else if (chipLandLots.isChecked()) {
            selectedChipsTexts.add(chipLandLots.getText().toString());
        }

        return selectedChipsTexts;
    }

}
