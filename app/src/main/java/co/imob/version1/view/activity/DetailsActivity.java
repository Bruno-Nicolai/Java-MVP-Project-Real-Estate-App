package co.imob.version1.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import co.imob.version1.R;
import co.imob.version1.adapter.DetailsViewPagerAdapter;
import co.imob.version1.model.Product;
import co.imob.version1.model.ViewPagerItem;

public class DetailsActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    ArrayList<ViewPagerItem> viewPagerItemArrayList;

    private String price, city, zip, title, description;
    private int extent, beds, baths;
    private CheckBox checkBoxVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Product selectedProduct = (Product) getIntent().getSerializableExtra("product");

        viewPager = findViewById(R.id.vp_pictures);
        viewPagerItemArrayList = new ArrayList<>();

        List<String> pictures = selectedProduct.getPictures();
        viewPagerItemArrayList = new ArrayList<>();
        viewPagerItemArrayList.add(new ViewPagerItem(pictures));

        DetailsViewPagerAdapter detailsViewPagerAdapter = new DetailsViewPagerAdapter(viewPagerItemArrayList);
        viewPager.setAdapter(detailsViewPagerAdapter);

        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(2);
        viewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);


        city = selectedProduct.getAddress().getCity();
        TextView tvCity = findViewById(R.id.details_tv_address_city);
        tvCity.setText(city);

        zip = selectedProduct.getAddress().getZipcode();
        TextView tvZipcode = findViewById(R.id.details_tv_address_zipcode);
        tvZipcode.setText(zip);

        title = selectedProduct.getTitle();
        TextView tvTitle = findViewById(R.id.details_tv_title);
        tvTitle.setText(title);

        extent = selectedProduct.getExtent();
        TextView tvExtent = findViewById(R.id.tv_product_extent);
        tvExtent.setText(String.valueOf(extent));

        price = selectedProduct.getPrice();
        TextView tvPrice = findViewById(R.id.tv_price);
        tvPrice.setText(price);

        beds = selectedProduct.getBed();
        TextView tvBeds = findViewById(R.id.tv_beds_nbr);
        tvBeds.setText(String.valueOf(beds));

        baths = selectedProduct.getBath();
        TextView tvBaths = findViewById(R.id.tv_baths_nbr);
        tvBaths.setText(String.valueOf(baths));

        checkBoxVisibility = findViewById(R.id.check_alarms);
        checkBoxVisibility.setChecked(selectedProduct.isSecurity());

        description = selectedProduct.getDescription();
        TextView tvDesc = findViewById(R.id.details_tv_desc_text);
        tvDesc.setText(description);


    }
}