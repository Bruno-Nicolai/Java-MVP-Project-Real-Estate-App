package co.imob.version1.service;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.imob.version1.model.Address;
import co.imob.version1.model.Geo;
import co.imob.version1.model.Product;
import co.imob.version1.repository.ProductRepository;

public class ProductService {

    public static Geo geoFromJson(JSONObject json) {

        Geo geo = null;

        try {
            geo = new Geo(
                    json.getDouble("lat"),
                    json.getDouble("lng")
            );
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return geo;

    }

    public static Address addressFromJson(JSONObject json) {

        Address address = null;

        try {
            address = new Address(
                    json.getString("street"),
                    json.getString("city"),
                    json.getString("zipcode"),
                    null);
            if (json.has("geo")) {
                address.setGeo(geoFromJson(json.getJSONObject("geo")));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return address;

    }

    public static List<String> picturesFromJson(JSONArray json) throws JSONException {

        ArrayList<String> picturesList = new ArrayList<>();
        for (int i = 0; i < json.length(); i++) {
            try {
                picturesList.add(new String(json.getString(i)));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        return picturesList;

    }

    public static Product productFromJson(JSONObject json) {

        Product product = null;
        try {

            product = new Product(
                    json.getInt("id"),
                    json.getString("title"),
                    null,
                    null,
                    json.getString("description"),
                    null,
                    json.getInt("bed"),
                    json.getInt("bath"),
                    json.getBoolean("security"),
                    json.isNull("garage"),
                    json.isNull("rent"),
                    json.getInt("extent"),
                    json.getString("price")
            );
            if (json.has("address")) {
                JSONObject jsonAddress = json.getJSONObject("address");
                Address address = addressFromJson(jsonAddress);
                product.setAddress(address);
            }
            if (json.has("geo")) {
                JSONObject jsonGeo = json.getJSONObject("address");
                Geo geo = geoFromJson(jsonGeo);
                product.setGeo(geo);
            }
            if (json.has("pictures")) {
                JSONArray jsonPictures = json.getJSONArray("pictures");
                product.setPictures(picturesFromJson(jsonPictures));
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return product;

    }


    public static void getAllProducts(Context context, ServiceDone callback) {

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                "https://api-mobile-20231.onrender.com/products",
                null,
                response -> {

                    for (int i = 0; i < response.length(); i++) {
                        try {

                            JSONObject json = response.getJSONObject(i);
                            ProductRepository.getInstance().addProduct(productFromJson(json));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    if (callback != null) {
                        callback.onServiceDone();
                    }

                }, error -> {

            Toast.makeText(context, "Falha na requisição! " + error.getMessage(), Toast.LENGTH_SHORT).show();

        });
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

    }


    public static void getProduct(Context context, int id, ServiceDone callback) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "https://api-mobile-20231.onrender.com/products/" + id,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Product product;
                product = productFromJson(response);
            }
        }, error -> {
            Toast.makeText(context, "Falha na requisição! " + error.getMessage(), Toast.LENGTH_SHORT).show();
        });
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }
}
