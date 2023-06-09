package co.imob.version1.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

import co.imob.version1.R;
import co.imob.version1.adapter.DetailsViewPagerAdapter;
import co.imob.version1.model.Product;
import co.imob.version1.presenter.DetailsContract;
import co.imob.version1.presenter.DetailsPresenter;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    private DetailsContract.Presenter presenter;

    private ViewPager2 viewPager;
    private Toolbar toolbar;
    private List<String> imageUrls;
    private DetailsViewPagerAdapter viewPagerAdapter;

    private String price, city, zip, title, description;
    private int extent, beds, baths;
    private CheckBox checkBoxVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        int productId = getIntent().getIntExtra("product_id", -1);
        presenter = new DetailsPresenter(this);

        Product selectedProduct = presenter.getProduct(productId);

        toolbar = findViewById(R.id.tb_pictures);
        setSupportActionBar(toolbar);

        displayProduct(selectedProduct);

    }

    @Override
    public void displayProduct(Product product) {

        imageUrls = product.getPictures();

        viewPager = findViewById(R.id.vp_pictures);
        viewPagerAdapter = new DetailsViewPagerAdapter(this, imageUrls);
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(2);
        viewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);


        city = product.getAddress().getCity();
        TextView tvCity = findViewById(R.id.details_tv_address_city);
        tvCity.setText(city);

        zip = product.getAddress().getZipcode();
        TextView tvZipcode = findViewById(R.id.details_tv_address_zipcode);
        tvZipcode.setText(zip);

        title = product.getTitle();
        TextView tvTitle = findViewById(R.id.details_tv_title);
        tvTitle.setText(title);

        extent = product.getExtent();
        TextView tvExtent = findViewById(R.id.tv_product_extent);
        tvExtent.setText(String.valueOf(extent));

        price = product.getPrice();
        TextView tvPrice = findViewById(R.id.details_tv_price);
        tvPrice.setText(price);

        beds = product.getBed();
        TextView tvBeds = findViewById(R.id.tv_beds_nbr);
        tvBeds.setText(String.valueOf(beds));

        baths = product.getBath();
        TextView tvBaths = findViewById(R.id.tv_baths_nbr);
        tvBaths.setText(String.valueOf(baths));

        checkBoxVisibility = findViewById(R.id.check_alarms);
        checkBoxVisibility.setChecked(product.isSecurity());

        description = product.getDescription();
        TextView tvDesc = findViewById(R.id.details_tv_desc_text);
        tvDesc.setText(description);

    }

}