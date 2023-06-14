package co.imob.version1.view.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
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

    private ImageView btn_like;
    private Button btn_buy, btn_pay, btn_offer;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        int productId = getIntent().getIntExtra("product_id", -1);
        presenter = new DetailsPresenter(this);

        Product selectedProduct = presenter.getProduct(productId);

        toolbar = findViewById(R.id.tb_pictures);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });

        displayProduct(selectedProduct);
        btn_like = findViewById(R.id.iv_heart);
        btn_like.setOnClickListener(view -> {
            boolean isLiked = presenter.changeLike();
            likeAction(isLiked);
        });


        btn_buy = findViewById(R.id.btn_buy);
        btn_buy.setOnClickListener(view -> {
            showDialog();
        });

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

    @Override
    public void likeAction(boolean isLiked) {
        if (isLiked) {
            btn_like.setImageResource(R.drawable.ic_baseline_favorite_24);
            btn_like.setColorFilter(ContextCompat.getColor(this, R.color.fav_color), PorterDuff.Mode.SRC_IN);
        } else {
            btn_like.setImageResource(R.drawable.ic_baseline_favorites_24);
            btn_like.setColorFilter(null);
        }
    }

    @Override
    public void showDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet);

        btn_pay = dialog.findViewById(R.id.btn_pay);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsActivity.this, "PAY", Toast.LENGTH_SHORT).show();
            }
        });

        btn_offer = dialog.findViewById(R.id.btn_offer);
        btn_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsActivity.this, "OFFER", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.Theme_Imob_DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    @Override
    public Context getContext() {
        return this;
    }

}