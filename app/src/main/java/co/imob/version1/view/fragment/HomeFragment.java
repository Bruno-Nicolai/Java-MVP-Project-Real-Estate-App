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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import co.imob.version1.R;
import co.imob.version1.adapter.ProductAdapter;
import co.imob.version1.presenter.HomeContract;

public class HomeFragment extends Fragment implements HomeContract.View {

    RecyclerView rv_home;
    ProductAdapter homeAdapter;
    Toolbar toolbar;
    private MenuItem menuItem;
    private SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
//        homeAdapter.startListening();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_main_search, menu);
/*        menuItem = menu.findItem(R.id.search);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setIconified(true);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mySearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mySearch(query);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        toolbar = view.findViewById(R.id.tb_main);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        rv_home = view.findViewById(R.id.rv_catalog);
        rv_home.setLayoutManager((new LinearLayoutManager(getContext())));

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

//        NavController navController = Navigation.findNavController(view);
//        AppBarConfiguration appBarConfig = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        Toolbar toolbar = view.findViewById(R.id.tb_main);
//
//        NavigationUI.setupWithNavController(toolbar, navController, appBarConfig);
//
//        toolbar.setOnMenuItemClickListener(item -> {
//            return item.getItemId() == R.id.search;
//        });

    }

    private void mySearch(String query) {
//        newHomeAdapter = new ProductAdapter();
//        newHomeAdapter.startListening();
//        rv_home.setAdapter(newHomeAdapter);
    }

    @Override
    public void setHomeAdapter(ProductAdapter adapter) {
        homeAdapter = adapter;
//        homeAdapter.startListening();
        rv_home.setAdapter(homeAdapter);
    }

}