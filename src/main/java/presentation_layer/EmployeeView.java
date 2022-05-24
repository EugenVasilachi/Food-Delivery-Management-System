package presentation_layer;

import business_layer.DeliveryService;
import business_layer.Order;
import business_layer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

public class EmployeeView extends JFrame implements Observer {

    private final DeliveryService deliveryService;
    private final JTextArea ordersList;
    private final JLabel  ordersListLabel;
    private final JPanel panel;

    public EmployeeView(DeliveryService deliveryService) {
        setPreferredSize(new Dimension(550, 550));
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.deliveryService = deliveryService;

        panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);

        ordersListLabel = new JLabel("Orders list");
        ordersListLabel.setBounds(230, 60, 200, 30);
        ordersListLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        ordersListLabel.setBorder(null);
        panel.add(ordersListLabel);

        ordersList = new JTextArea();
        ordersList.setBounds(15, 90, 500, 360);
        ordersList.setBorder(null);
        ordersList.setFont(new Font("Serif", Font.PLAIN, 14));
        panel.add(ordersList);

        pack();
        setResizable(false);
    }

    @Override
    public void update(Observable o, Object arg) {
        DeliveryService deliveryService = (DeliveryService) o;
        Order order = (Order) arg;
        HashSet<MenuItem> products = deliveryService.getOrderedProducts().get(order);
        String s = "Order " + order.toString() + "\n";
        s += "Products \n";
        for(MenuItem menuItem : products){
            s += menuItem.toString() + "\n";
        }
        ordersList.setText(s);
    }
}
