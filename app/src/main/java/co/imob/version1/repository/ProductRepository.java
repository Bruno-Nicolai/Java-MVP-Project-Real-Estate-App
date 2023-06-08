package co.imob.version1.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.imob.version1.model.Product;

public class ProductRepository {

    private Map<Integer, Product> productsMap;

    private static ProductRepository instance = null;

    private ProductRepository() {
        this.productsMap = new HashMap<>();
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public void addProduct(Product product) {
        if (product != null) {
            this.productsMap.put(product.getId(), product);
        }
    }

    public boolean contains(Product product) {
        return this.productsMap.containsValue(product);
    }

    public boolean contains(Integer id) {
        return this.productsMap.containsKey(id);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(this.productsMap.values());
    }

    public Product getProduct(int id) {
        return this.productsMap.get(id);
    }

}
