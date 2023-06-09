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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        if (activity != null) activity.setSupportActionBar(toolbar);

        rv_home = view.findViewById(R.id.rv_catalog);
        rv_home.setLayoutManager((new LinearLayoutManager(getContext())));

        return view;

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        searchItem.setIcon(R.drawable.search_icon); // Set the icon here
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.toolbar_main_search, menu);

        searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();

        SearchManager searchManager = (SearchManager) requireActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));

        searchView.setImeOptions(EditorInfo.IME_ACTION_GO);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getContext() {
        return requireActivity().getApplicationContext();
    }

    @Override
    public void setHomeAdapter(ProductAdapter adapter) {
        homeAdapter = adapter;
        rv_home.setAdapter(homeAdapter);
    }

}