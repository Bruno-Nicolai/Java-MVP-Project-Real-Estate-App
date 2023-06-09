package co.imob.version1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.imob.version1.R;
import co.imob.version1.model.Product;
import co.imob.version1.view.activity.DetailsActivity;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements /*View.OnClickListener,*/ Filterable {

    List<Product> products;
    List<Product> filteredProducts;
    Context context;

//    private View.OnClickListener listener;

    public ProductAdapter(List<Product> products) {
        this.products = products;
        filteredProducts = new ArrayList<>(products);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        context = parent.getContext();
//        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product currentProduct = products.get(position);

        String imageUrl = currentProduct.getPictures().get(0);
        Picasso.get().load(imageUrl).into((ImageView) holder.view.findViewById(R.id.iv_picture));

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
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Product> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(filteredProducts);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Product product : filteredProducts) {
                    if (product.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(product);
                    } else if (product.getAddress().getStreet().toLowerCase().contains(filterPattern)) {
                        filteredList.add(product);
                    } else if (product.getAddress().getCity().toLowerCase().contains(filterPattern)) {
                        filteredList.add(product);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            products.clear();
            products.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

//    @Override
//    public void onClick(View v) {
//        if (listener != null) {
//            listener.onClick(v);
//        }
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }

//    public void setOnItemClickListener(View.OnClickListener listener) {
//        this.listener = listener;
//    }
}
