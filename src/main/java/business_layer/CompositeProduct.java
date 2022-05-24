package business_layer;

import java.util.HashSet;
import java.util.Set;

public class CompositeProduct extends MenuItem{
    private Set<MenuItem> composition;

    public CompositeProduct(HashSet<MenuItem> composition, String product, float rating, float calories, float protein, float fat,
                            float sodium, double price) {
        super(product, rating, calories, protein, fat, sodium, price);
        this.composition = composition;
    }

    public void addProduct(MenuItem item) {
        composition.add(item);
    }

    public void removeProduct(MenuItem item) {
        composition.remove(item);
    }

    public boolean findProduct(String name) {
        return composition.stream().anyMatch(product -> product.getProduct().equals(name));
    }

    @Override
    public float getRating() {
        float rating = 0;
        for(MenuItem item : composition) {
            rating += item.getRating();
        }
        return rating / composition.size();
    }

    @Override
    public float getCalories() {
        float calories = 0;
        for(MenuItem item : composition) {
            calories += item.getCalories();
        }
        return calories;
    }

    @Override
    public float getProtein() {
        float protein = 0;
        for(MenuItem item : composition) {
            protein += item.getProtein();
        }
        return protein;
    }

    @Override
    public float getFat() {
        float fat = 0;
        for(MenuItem item : composition) {
            fat += item.getFat();
        }
        return fat;
    }

    @Override
    public float getSodium() {
        float sodium = 0;
        for(MenuItem item : composition) {
            sodium += item.getSodium();
        }
        return sodium;
    }

    @Override
    public double computePrice() {
        double price = 0;
        for(MenuItem item : composition) {
            price += item.computePrice() * 9 / 10; // 10% sale
        }
        return price;
    }

}