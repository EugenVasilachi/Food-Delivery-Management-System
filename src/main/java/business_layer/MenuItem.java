package business_layer;

import java.io.Serializable;
import java.util.Objects;

public class MenuItem implements Serializable {
    private String product;
    private float rating;
    private float calories;
    private float protein;
    private float fat;
    private float sodium;
    private double price;

    public MenuItem(String product, float rating, float calories, float protein, float fat, float sodium, double price) {
        this.product = product;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    public double computePrice() {
        return price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /*@Override
    public boolean equals(Object obj) {
        MenuItem object = (MenuItem) obj;
        if (object.getProduct().equals(product) &&
        object.getRating() == rating &&
        object.getCalories() == calories &&
        object.getProtein() == protein &&
        object.getFat() == fat &&
        object.getSodium() == sodium &&
        object.getPrice() == price)
            return true;
        return false;
    }*/


    @Override
    public int hashCode() {
        return Objects.hash(product, rating, calories, protein, fat, sodium, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Float.compare(menuItem.rating, rating) == 0 && Float.compare(menuItem.calories, calories) == 0
                && Float.compare(menuItem.protein, protein) == 0 && Float.compare(menuItem.fat, fat) == 0
                && Float.compare(menuItem.sodium, sodium) == 0 && Double.compare(menuItem.price, price) == 0
                && Objects.equals(product, menuItem.product);
    }

    @Override
    public String toString(){
        return product + ", " + rating + ", " + calories + ", " + protein + ", " + fat + ", " + sodium + ", " + price;
    }

}
