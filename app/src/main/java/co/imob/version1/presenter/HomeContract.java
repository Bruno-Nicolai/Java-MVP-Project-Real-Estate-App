package co.imob.version1.presenter;

import android.content.Context;

import com.google.android.material.chip.Chip;

import java.util.List;

import co.imob.version1.adapter.ProductAdapter;

public interface HomeContract {

    interface View {

        public Context getContext();

        public void updateFilterVisibility(boolean isSearchIconClicked);

        public Chip getChip(int chipId);

        public void updateSearchViewFromChips();

        public void setHomeAdapter(ProductAdapter adapter);

//        public void onLikedStatusChanged(int productId, boolean isLiked);

    }

    interface Presenter {
        public void getAllProducts();

//        public void setLikedStatusChangeListener();

        public List<String> getSelectedChipContent();
    }
}
