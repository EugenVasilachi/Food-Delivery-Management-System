package business_layer;

import data_layer.FileWriterClass;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {

    private final HashMap<Order, HashSet<MenuItem>> orderedProducts;
    private final HashSet<MenuItem> menu;
    private final HashSet<Order> orders;

    public DeliveryService() {
        menu = new HashSet<>();
        menuInitialize();
        orderedProducts = new HashMap<>();
        orders = new HashSet<>();
    }

    public HashMap<Order, HashSet<MenuItem>> getOrderedProducts() {
        return orderedProducts;
    }

    public HashSet<MenuItem> getMenu() {
        return menu;
    }

    public HashSet<Order> getOrders() {
        return orders;
    }

    public void menuInitialize() {
        File csv = new File("products.csv");
        try {
            int start = 0;
            InputStreamReader i = new InputStreamReader(new FileInputStream(csv));
            CSVParser csvParser = CSVFormat.DEFAULT.parse(i);
            for (CSVRecord csvRecord : csvParser) {
                if (start == 0) {
                    start = 1;
                } else {
                    String name = csvRecord.get(0);
                    float rating = Float.parseFloat(csvRecord.get(1));
                    float calories = Float.parseFloat(csvRecord.get(2));
                    float protein = Float.parseFloat(csvRecord.get(3));
                    float fat = Float.parseFloat(csvRecord.get(4));
                    float sodium = Float.parseFloat(csvRecord.get(5));
                    float price = Float.parseFloat(csvRecord.get(6));
                    menu.add(new MenuItem(name, rating, calories, protein, fat, sodium, price));
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void refreshCSV() {
        FileWriterClass fileWriterClass = new FileWriterClass("products.csv");
        String s = "Title,Rating,Calories,Protein,Fat,Sodium,Price\n";
        for (MenuItem m : menu) {
            s += m.toString() + "\n";
        }
        fileWriterClass.writeToFile(s);
    }

    @Override
    public void addProduct(MenuItem menuItem) {
        assert menuItem != null;
        assert wellFormed();

        int menuSize = menu.size();
        menu.add(menuItem);
        refreshCSV();

        assert menu.size() == menuSize + 1;
        assert wellFormed();
    }

    @Override
    public void editProduct(MenuItem oldMenu, MenuItem newMenu) {
        assert oldMenu != null && newMenu != null;
        assert wellFormed();

        int menuSize = menu.size();
        menu.remove(oldMenu);
        menu.add(newMenu);
        refreshCSV();

        assert menu.size() == menuSize;
        assert wellFormed();
    }

    @Override
    public void deleteProduct(MenuItem menuItem) {
        assert menuItem != null;
        assert wellFormed();

        int menuSize = menu.size();
        menu.remove(menuItem);
        refreshCSV();

        assert menu.size() == menuSize - 1;
        assert wellFormed();
    }

    @Override
    public List<MenuItem> findProducts(String category, String detail) {
        assert category != null && detail != null;
        assert wellFormed();

        if (category.equals("Name")) {
            return menu.stream().filter(menuItem -> menuItem.getProduct().toLowerCase().contains(detail.toLowerCase())).collect(Collectors.toList());
        } else if (category.equals("Rating")) {
            Float rating = Float.valueOf(detail);
            return menu.stream().filter(menuItem -> menuItem != null && Float.valueOf(menuItem.getRating()).compareTo(rating) == 0).collect(Collectors.toList());
        } else if (category.equals("Calories")) {
            Float calories = Float.valueOf(detail);
            return menu.stream().filter(menuItem -> menuItem != null && Float.valueOf(menuItem.getCalories()).equals(calories)).collect(Collectors.toList());
        } else if (category.equals("Protein")) {
            Float protein = Float.valueOf(detail);
            return menu.stream().filter(menuItem -> menuItem != null && Float.valueOf(menuItem.getProtein()).equals(protein)).collect(Collectors.toList());
        } else if (category.equals("Fat")) {
            Float fats = Float.valueOf(detail);
            return menu.stream().filter(menuItem -> menuItem != null && Float.valueOf(menuItem.getFat()).equals(fats)).collect(Collectors.toList());
        } else if (category.equals("Sodium")) {
            Float sodium = Float.valueOf(detail);
            return menu.stream().filter(menuItem -> menuItem != null && Float.valueOf(menuItem.getSodium()).equals(sodium)).collect(Collectors.toList());
        } else if (category.equals("Price")) {
            Double price = Double.valueOf(detail);
            return menu.stream().filter(menuItem -> menuItem != null && Double.valueOf(menuItem.getPrice()).compareTo(price) == 0).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public void addMenu(String name, Set<MenuItem> newMenu) {
        assert name != null && newMenu != null;
        assert wellFormed();

        int menuSize = menu.size();
        float calories = 0,
                protein = 0,
                fat = 0,
                sodium = 0,
                rating = 0;
        double price = 0;
        for (MenuItem menuItem : newMenu) {
            calories += menuItem.getCalories();
            protein += menuItem.getProtein();
            fat += menuItem.getFat();
            sodium += menuItem.getSodium();
            rating += menuItem.getRating();
            price += menuItem.getPrice() * 9 / 10; // 10% sale
        }
        CompositeProduct compositeProduct = new CompositeProduct((HashSet<MenuItem>) newMenu, name,
                rating / newMenu.size(), calories, protein, fat, sodium, price);
        menu.add(compositeProduct);
        refreshCSV();

        assert menu.size() == menuSize + 1;
        assert wellFormed();
    }

    @Override
    public void generateInterval(int fromHour, int byHour) {
        assert fromHour < byHour && fromHour >= 0 && byHour < 24;

        FileWriterClass fileWriterClass = new FileWriterClass("Report Time Interval.txt");
        String s = "Placed orders between " + fromHour + " o'clock " + " - " + byHour + " o'clock" + "\n\n";
        List<Order> orderList = orders.stream().filter(order -> order.getDate().getHours() >= fromHour
                && order.getDate().getHours() <= byHour).collect(Collectors.toList());

        for (Order i : orderList) {
            s += i.toString() + "\n";
        }
        fileWriterClass.writeToFile(s);
    }

    @Override
    public void productsTimes(int times) {
        assert times > 0;

        FileWriterClass f = new FileWriterClass("Products Times Report.txt");
        String s = "Products ordered more than " + times + " times\n\n";
        ArrayList<MenuItem> searchedProducts = new ArrayList<>();

        for (Order o : orders) {
            HashSet<MenuItem> products = orderedProducts.get(o);
            searchedProducts.addAll(products);
        }

        Map<Object, Long> count = searchedProducts.stream().collect(Collectors.groupingBy(MenuItem::getProduct,
                Collectors.counting()));
        // apple=2, banana=3

        for (Map.Entry<Object, Long> i : count.entrySet()) {
            if (i.getValue() >= times) {
                s += i.getKey() + " " + i.getValue() + "\n";
            }
        }
        f.writeToFile(s);
    }

    @Override
    public void clientsReport(int times, int total) {
        assert times > 0 && total > 0;

        FileWriterClass f = new FileWriterClass("Clients Report.txt");
        String s = "Clients that have ordered more than " + times + " times \n\n clientName -> order\n\n";
        List<Order> orderList = orders.stream().filter(order -> order.getTotal() >= total).collect(Collectors.toList());

        Map<Object, Long> count = orderList.stream().collect(Collectors.groupingBy(Order::getClientName,
                Collectors.counting()));

        for (Map.Entry<Object, Long> i : count.entrySet()) {
            if (i.getValue() >= times) {
                s += i.getKey() + " -> " + i.getValue() + "\n";
            }
        }
        f.writeToFile(s);
    }

    @Override
    public void productsOrderedWithinADayReport(int day) {
        assert day > 0 && day < 32;

        FileWriterClass f = new FileWriterClass("Products Ordered Within a Day Report.txt");
        String s = "Products ordered on " + day + " \n\n";
        List<Order> orderList = orders.stream().filter(order -> order.getDate().getDay() - 1 == day)
                .collect(Collectors.toList());
        ArrayList<MenuItem> products = new ArrayList<>();
        for (Order o : orderList) {
            HashSet<MenuItem> productsList = orderedProducts.get(o);
            products.addAll(productsList);
        }
        Map<Object, Long> count = products.stream().collect(Collectors.groupingBy(MenuItem::getProduct,
                Collectors.counting()));

        for (Map.Entry<Object, Long> i : count.entrySet()) {
            s += i.getKey() + " " + i.getValue() + "\n";
        }
        f.writeToFile(s);
    }

    @Override
    public void order(Order order, Set<MenuItem> products) {
        assert order != null && products != null;
        assert wellFormed();

        orders.add(order);
        orderedProducts.put(order, (HashSet<MenuItem>) products);
        setChanged();
        notifyObservers(order);
    }

    public boolean wellFormed() {
        return menu != null && orderedProducts != null && orders != null;
    }

}
