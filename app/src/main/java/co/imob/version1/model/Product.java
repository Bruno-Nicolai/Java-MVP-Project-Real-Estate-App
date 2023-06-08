package co.imob.version1.model;

import java.util.List;

public class Product {

    private int id;
    private String title;
    private Address address;
    private Geo geo;
    private String description;
    private List<String> pictures;
    private int bed;
    private int bath;
    private boolean security;
    private boolean garage;
    private boolean rent;
    private int extent;
    private String price;

    public Product(int id, String title, Address address, Geo geo, String description, List<String> pictures, int bed, int bath, boolean security, boolean garage, boolean rent, int extent, String price) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.geo = geo;
        this.description = description;
        this.pictures = pictures;
        this.bed = bed;
        this.bath = bath;
        this.security = security;
        this.garage = garage;
        this.rent = rent;
        this.extent = extent;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Address getAddress() {
        return address;
    }

    public Geo getGeo() {
        return geo;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public int getBed() {
        return bed;
    }

    public int getBath() {
        return bath;
    }

    public boolean isSecurity() {
        return security;
    }

    public boolean isGarage() {
        return garage;
    }

    public boolean isRent() {
        return rent;
    }

    public int getExtent() {
        return extent;
    }

    public String getPrice() {
        return price;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
