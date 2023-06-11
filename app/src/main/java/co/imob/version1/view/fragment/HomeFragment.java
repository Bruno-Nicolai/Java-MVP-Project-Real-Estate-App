package co.imob.version1.view.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;
import java.util.Objects;

import co.imob.version1.R;
import co.imob.version1.adapter.ProductAdapter;
import co.imob.version1.presenter.HomeContract;
import co.imob.version1.presenter.HomePresenter;

public class HomeFragment extends Fragment implements HomeContract.View {

    private RecyclerView rv_home;
    private ProductAdapter homeAdapter;
    private HomeContract.Presenter presenter;
    private Toolbar toolbar;
    private MenuItem searchItem;
    private SearchView searchView;

    private TextView tvQuickFilter;
    private ChipGroup chipGroup;

    private Chip chipHouse, chipApartment, chipGarage, chipOffice, chipLandAndLots;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        presenter = new HomePresenter(this);
        presenter.getAllProducts();

        toolbar = view.findViewById(R.id.tb_main);
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        activity.setSupportActionBar(toolbar);

        tvQuickFilter = view.findViewById(R.id.tv_quick_filter);
        tvQuickFilter.setVisibility(View.GONE);
        chipGroup = view.findViewById(R.id.chip_group);
        chipGroup.setVisibility(View.GONE);

        chipHouse = view.findViewById(R.id.chip_category1);
        chipApartment = view.findViewById(R.id.chip_category2);
        chipGarage = view.findViewById(R.id.chip_category3);
        chipOffice = view.findViewById(R.id.chip_category4);
        chipLandAndLots = view.findViewById(R.id.chip_category5);

        rv_home = view.findViewById(R.id.rv_catalog);
        rv_home.setLayoutManager((new LinearLayoutManager(getContext())));

        chipHouse.setOnCheckedChangeListener((buttonView, isChecked) -> updateSearchViewFromChips());
        chipApartment.setOnCheckedChangeListener((buttonView, isChecked) -> updateSearchViewFromChips());
        chipGarage.setOnCheckedChangeListener((buttonView, isChecked) -> updateSearchViewFromChips());
        chipOffice.setOnCheckedChangeListener((buttonView, isChecked) -> updateSearchViewFromChips());
        chipLandAndLots.setOnCheckedChangeListener((buttonView, isChecked) -> updateSearchViewFromChips());
//        .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                updateSearchViewFromChips();
//            }
//        });

        return view;

    }

    @Override
    public void updateSearchViewFromChips() {
        StringBuilder searchFromChips = new StringBuilder();

        if (chipHouse.isChecked()) searchFromChips.append(chipHouse.getText()).append(" ");
        if (chipApartment.isChecked()) searchFromChips.append(chipApartment.getText()).append(" ");
        if (chipGarage.isChecked()) searchFromChips.append(chipGarage.getText()).append(" ");
        if (chipOffice.isChecked()) searchFromChips.append(chipOffice.getText()).append(" ");
        if (chipLandAndLots.isChecked()) searchFromChips.append(chipLandAndLots.getText()).append(" ");

        String chipContent = searchFromChips.toString().trim();
        searchView.setQuery(chipContent, false);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        searchItem.setIcon(R.drawable.search_icon);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.toolbar_main_search, menu);

        searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();
        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                updateFilterVisibility(true);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                updateFilterVisibility(false);
                return true;
            }
        });

        SearchManager searchManager = (SearchManager) requireActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));

        searchView.setImeOptions(EditorInfo.IME_ACTION_GO);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                updateFilterVisibility(false);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                homeAdapter.getFilter().filter(query);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search) {
            searchView.setIconified(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateFilterVisibility(boolean isSearchIconClicked) {
        if (!isSearchIconClicked) {
            tvQuickFilter.setVisibility(View.GONE);
            chipGroup.setVisibility(View.GONE);
        } else {
            tvQuickFilter.setVisibility(View.VISIBLE);
            chipGroup.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Chip getChip(int chipId) {
        if (chipId == R.id.chip_category1) return chipHouse;
        if (chipId == R.id.chip_category2) return chipApartment;
        if (chipId == R.id.chip_category3) return chipGarage;
        if (chipId == R.id.chip_category4) return chipOffice;
        if (chipId == R.id.chip_category5) return chipLandAndLots;
        return null;
    }

    @Override
    public Context getContext() {
        return requireActivity().getApplicationContext();
    }

    @Override
    public void setHomeAdapter(ProductAdapter adapter) {
        homeAdapter = adapter;
        homeAdapter.setSelectedChipsTexts(presenter.getSelectedChipContent());
        rv_home.setAdapter(homeAdapter);
    }

}