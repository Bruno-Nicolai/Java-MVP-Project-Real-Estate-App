package co.imob.version1.model;

import java.util.List;

public class ViewPagerItem {

    public List<String> imageUrls;

    public ViewPagerItem(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }
}

