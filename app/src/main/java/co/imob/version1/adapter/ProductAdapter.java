package co.imob.version1.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import co.imob.version1.R;
import co.imob.version1.model.Product;
import co.imob.version1.view.activity.DetailsActivity;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements Filterable {

    List<Product> products;
    List<Product> filteredProducts;
//    List<Integer> likedProducts;

    private List<String> selectedChipsTexts;

    private Context context;

    public ProductAdapter(List<Product> products, List<String> selectedChipTexts) {
        this.products = products;
        filteredProducts = new ArrayList<>(products);
//        likedProducts = new ArrayList<>();
        this.selectedChipsTexts = selectedChipTexts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product currentProduct = products.get(position);

        String imageUrl = currentProduct.getPictures().get(0);
        Picasso.get().load(imageUrl).into((ImageView) holder.view.findViewById(R.id.iv_picture));

/*
        if (likedProducts.contains(currentProduct.getId())) {
            ((ImageView) holder.view.findViewById(R.id.iv_heart)).setImageResource(R.drawable.ic_baseline_favorite_24);
            ((ImageView) holder.view.findViewById(R.id.iv_heart))
                    .setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.fav_color), PorterDuff.Mode.SRC_IN);
        } else {
            ((ImageView) holder.view.findViewById(R.id.iv_heart)).setImageResource(R.drawable.ic_baseline_favorites_24);
            ((ImageView) holder.view.findViewById(R.id.iv_heart)).setColorFilter(null);
        }
*/

        ((TextView) holder.view.findViewById(R.id.main_tv_title)).setText(currentProduct.getTitle());
        ((TextView) holder.view.findViewById(R.id.tv_price)).setText(currentProduct.getPrice());
        ((TextView) holder.view.findViewById(R.id.tv_address_city)).setText(currentProduct.getAddress().getCity());
        ((TextView) holder.view.findViewById(R.id.tv_address_street)).setText(currentProduct.getAddress().getStreet());
        ((TextView) holder.view.findViewById(R.id.tv_description)).setText(currentProduct.getDescription());

        holder.view.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("product_id", currentProduct.getId());
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                List<Product> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    // No search text, include all products
                    filteredList.addAll(filteredProducts);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Product product : filteredProducts) {
                        if (productMatchesSearch(product, filterPattern) && productMatchesChips(product, selectedChipsTexts)) {
                            filteredList.add(product);
                        }
                    }
                }

                filterResults.values = filteredList;
                filterResults.count = filteredList.size();
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                products.clear();
                products.addAll((List) results.values);
                notifyDataSetChanged();

            }
        };

        return filter;
    }

    private boolean productMatchesSearch(Product product, String filterPattern) {

        filterPattern = filterPattern.toLowerCase();

        if (product.getTitle().toLowerCase().contains(filterPattern)) return true;
        if (product.getAddress().getStreet().toLowerCase().contains(filterPattern)) return true;
        return product.getAddress().getCity().toLowerCase().contains(filterPattern);

    }

    private boolean productMatchesChips(Product product, List<String> selectedChipsTexts) {

        if (selectedChipsTexts == null || selectedChipsTexts.size() == 0) {
            return true;
        }

        for (String chipText : selectedChipsTexts) {
            if (chipText.equals(product.getTitle())) {
                return true;
            }
        }

        return false;

    }

    public void setSelectedChipsTexts(List<String> selectedChipsTexts) {
        this.selectedChipsTexts = selectedChipsTexts;
    }

/*
    public void setLikedProducts(List<Integer> likedProducts) {
        this.likedProducts = likedProducts;
        notifyDataSetChanged();
    }
*/

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }

}
