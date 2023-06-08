package co.imob.version1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import co.imob.version1.R;
import co.imob.version1.model.Product;
import co.imob.version1.view.activity.DetailsActivity;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Product> products;
    Context context;

    public ProductAdapter(List<Product> products) {
        this.products = products;
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

        ((TextView) holder.view.findViewById(R.id.main_tv_title)).setText(currentProduct.getTitle());
        ((TextView) holder.view.findViewById(R.id.tv_price)).setText(currentProduct.getPrice());
        ((TextView) holder.view.findViewById(R.id.tv_address_city)).setText(currentProduct.getAddress().getCity());
        ((TextView) holder.view.findViewById(R.id.tv_address_street)).setText(currentProduct.getAddress().getStreet());
        ((TextView) holder.view.findViewById(R.id.tv_description)).setText(currentProduct.getDescription());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            Product selectedProduct = currentProduct;
            intent.putExtra("product", (Serializable) selectedProduct);

            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }
}
