package business_layer;

import java.util.List;
import java.util.Set;

public interface IDeliveryServiceProcessing {

    List<MenuItem> findProducts(String category, String detail);

    void order(Order order, Set<MenuItem> products);

    void addProduct(MenuItem menuItem);

    void editProduct(MenuItem oldMenu, MenuItem newMenu);

    void deleteProduct(MenuItem menuItem);

    void addMenu(String name, Set<MenuItem> newMenu);

    void generateInterval(int fromHour, int byHour);

    void productsTimes(int times);

    void clientsReport(int times, int total);

    void productsOrderedWithinADayReport(int day);
}
