package co.imob.version1.model;

public class Address {

    private String street, city, zipcode;

    private Geo geo;

    public Address(String street, String city, String zip, Geo geo) {
        this.street = street;
        this.city = city;
        this.zipcode = zip;
        this.geo = geo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

}