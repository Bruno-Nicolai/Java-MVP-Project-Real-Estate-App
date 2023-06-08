package co.imob.version1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import co.imob.version1.R;
import co.imob.version1.model.ViewPagerItem;

public class DetailsViewPagerAdapter extends RecyclerView.Adapter<DetailsViewPagerAdapter.ViewHolder> {

    ArrayList<ViewPagerItem> viewPagerItemArrayList;

    public DetailsViewPagerAdapter(ArrayList<ViewPagerItem> viewPagerItemArrayList) {
        this.viewPagerItemArrayList = viewPagerItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewpager_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ViewPagerItem viewPagerItem = viewPagerItemArrayList.get(position);
        List<String> imageUrls = viewPagerItem.getImageUrls();
        for (String imageUrl : imageUrls) {
            Picasso.get().load(imageUrl).into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return viewPagerItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_img);
        }
    }
}
