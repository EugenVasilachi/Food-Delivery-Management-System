package business_layer;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Observable;

public class Order extends Observable implements Serializable {
    private int orderID;
    private String clientName;
    private Date date;
    private int total;
    private static int numberOfOrders;

    public Order(String clientName, int total) {
        this.clientName = clientName;
        this.total = total;
        date = new Date();
        numberOfOrders++;
        orderID = numberOfOrders;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getClientName() {
        return clientName;
    }

    public Date getDate() {
        return date;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderID == order.orderID && clientName.equals(order.clientName) && total == order.total && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID);
    }

    @Override
    public String toString() {
        return "client " + clientName + " with the total " + total + "\n";
    }

}
