package presentation_layer;

import business_layer.DeliveryService;
import business_layer.MenuItem;
import business_layer.Order;
import data_layer.FileWriterClass;
import data_layer.Serializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class ClientView extends JFrame {

    private final JPanel panel;
    private final JTextField product, detail;
    private final JLabel productLabel;
    private final JButton addButton, endButton, searchButton, showMenuButton, logOutButton;
    private final JScrollPane scrollPane;
    private final JComboBox comboBox;
    private int total;
    private final JTable table;
    private Set<MenuItem> orderedProducts;
    private String bill;
    private EmployeeView employeeView;

    public ClientView(String username, DeliveryService deliveryService) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600));
        setVisible(true);

        employeeView = new EmployeeView(deliveryService);

        orderedProducts = new HashSet<>();
        employeeView = new EmployeeView(deliveryService);

        panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);

        product = new JTextField();
        product.setFont(new Font("Serif", Font.PLAIN, 19));
        product.setBorder(null);
        product.setBounds(10, 80, 350, 40);
        panel.add(product);

        productLabel = new JLabel("Product");
        productLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        productLabel.setBorder(null);
        productLabel.setBounds(10, 40, 110, 30);
        panel.add(productLabel);

        detail = new JTextField();
        detail.setFont(new Font("Serif", Font.PLAIN, 19));
        detail.setBounds(400, 140, 170, 40);
        detail.setBorder(null);
        panel.add(detail);

        table = new JTable();
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 280, 560, 190);
        scrollPane.setViewportView(table);
        panel.add(scrollPane);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int row = table.getSelectedRow();
                product.setText(model.getValueAt(row, 0).toString());
            }
        });

        var nutritionFacts = new DefaultComboBoxModel();
        nutritionFacts.addElement("Name");
        nutritionFacts.addElement("Rating");
        nutritionFacts.addElement("Calories");
        nutritionFacts.addElement("Protein");
        nutritionFacts.addElement("Fat");
        nutritionFacts.addElement("Sodium");
        nutritionFacts.addElement("Price");
        comboBox = new JComboBox(nutritionFacts);
        comboBox.setFont(new Font("Serif", Font.PLAIN, 19));
        comboBox.setBounds(400, 80, 170, 40);
        comboBox.setBorder(null);
        comboBox.setBackground(new Color(255, 255, 255));
        panel.add(comboBox);

        addButton = new JButton("Add product");
        addButton.setFocusable(false);
        addButton.setFont(new Font("Serif", Font.PLAIN, 19));
        addButton.setBorder(null);
        addButton.setBounds(40, 140, 180, 30);
        addButton.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int row = table.getSelectedRow();
            String name = model.getValueAt(row, 0).toString();
            float rating = Float.parseFloat(model.getValueAt(row, 1).toString());
            float calories = Float.parseFloat(model.getValueAt(row, 2).toString());
            float protein = Float.parseFloat(model.getValueAt(row, 3).toString());
            float fat = Float.parseFloat(model.getValueAt(row, 4).toString());
            float sodium = Float.parseFloat(model.getValueAt(row, 5).toString());
            double price = Float.parseFloat(model.getValueAt(row, 6).toString());
            MenuItem menuItem = new MenuItem(name, rating, calories, protein, fat, sodium, price);

            orderedProducts.add(menuItem);
            total += price;
            JOptionPane.showMessageDialog(null,"Product successfully added");
            Serializator.serialize(deliveryService);
        });
        panel.add(addButton);

        endButton = new JButton("End");
        endButton.setFocusable(false);
        endButton.setFont(new Font("Serif", Font.PLAIN, 19));
        endButton.setBorder(null);
        endButton.setBounds(40, 190, 180, 30);
        endButton.addActionListener(e -> {
            Order newOrder = new Order(username, total);
            deliveryService.order(newOrder, orderedProducts);
            bill = "Receipt client " + username + "\n";
            for(MenuItem menuItem : orderedProducts) {
                bill += menuItem.toString() + "\n";
            }
            bill += "Total: " + total + "\n";
            FileWriterClass f = new FileWriterClass("Receipt client " + username + ".txt");
            f.writeToFile(bill);
            total = 0;
            orderedProducts = null;
            bill="";
            JOptionPane.showMessageDialog(null,"Order completed successfully");
            Serializator.serialize(deliveryService);
        });
        panel.add(endButton);

        searchButton = new JButton("Search");
        searchButton.setFocusable(false);
        searchButton.setFont(new Font("Serif", Font.PLAIN, 19));
        searchButton.setBorder(null);
        searchButton.setBounds(400, 190, 170, 30);
        searchButton.addActionListener(e -> {
            String category = (String) comboBox.getSelectedItem();
            String detailString = detail.getText();
            List<MenuItem> productsList = deliveryService.findProducts(category, detailString);
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Name");
            tableModel.addColumn("Rating");
            tableModel.addColumn("Calories");
            tableModel.addColumn("Protein");
            tableModel.addColumn("Fat");
            tableModel.addColumn("Sodim");
            tableModel.addColumn("Price");
            for (MenuItem menuItem : productsList) {
                Vector row = new Vector();
                row.add(menuItem.getProduct());
                row.add(menuItem.getRating());
                row.add(menuItem.getCalories());
                row.add(menuItem.getProtein());
                row.add(menuItem.getFat());
                row.add(menuItem.getSodium());
                row.add(menuItem.getPrice());
                tableModel.addRow(row);
            }
            table.setModel(tableModel);
        });
        panel.add(searchButton);

        showMenuButton = new JButton("Show the menu");
        showMenuButton.setFocusable(false);
        showMenuButton.setFont(new Font("Serif", Font.PLAIN, 19));
        showMenuButton.setBorder(null);
        showMenuButton.setBounds(150, 240, 270, 30);
        showMenuButton.addActionListener(e -> {
            LogInView.printTable(table);
        });
        panel.add(showMenuButton);

        logOutButton = new JButton("Log Out");
        logOutButton.setFocusable(false);
        logOutButton.setFont(new Font("Serif", Font.PLAIN, 19));
        logOutButton.setBorder(null);
        logOutButton.setBounds(460, 490, 110, 30);
        logOutButton.addActionListener(e -> {
            LogInView view = new LogInView(deliveryService);
            view.show();
            dispose();
        });
        panel.add(logOutButton);

        pack();
        setResizable(false);
    }

}
