package co.imob.version1.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import co.imob.version1.R;
import co.imob.version1.adapter.DetailsViewPagerAdapter;
import co.imob.version1.model.ViewPagerItem;

public class DetailsActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    ArrayList<ViewPagerItem> viewPagerItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        viewPager = findViewById(R.id.vp_pictures);
        int[] images = {
                R.drawable.pic_detail,
                R.drawable.pic_new_1,
                R.drawable.pic_new_2
        };
        viewPagerItemArrayList = new ArrayList<>();

        for (int i = 0; i < images.length; i++) {
            ViewPagerItem viewPagerItem = new ViewPagerItem(images[i]);
            viewPagerItemArrayList.add(viewPagerItem);
        }

        DetailsViewPagerAdapter detailsViewPagerAdapter = new DetailsViewPagerAdapter(viewPagerItemArrayList);
        viewPager.setAdapter(detailsViewPagerAdapter);

        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(2);
        viewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

    }
}