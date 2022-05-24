package presentation_layer;

import business_layer.DeliveryService;
import business_layer.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;

public class AdministratorView extends JFrame {

    private final JPanel panel;
    private final JTextField name;
    private final JTextField rating;
    private final JTextField calories;
    private final JTextField protein;
    private final JTextField fat;
    private final JTextField sodium;
    private final JTextField price;
    private final JTable table;
    private final JTextField menuName;
    private final JLabel menuPrice;
    private HashSet<MenuItem> newMenus;
    private double newPrice = 0;

    public AdministratorView(DeliveryService deliveryService) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(970, 680));
        setVisible(true);
        setResizable(false);

        newMenus = new HashSet<>();

        panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);

        JLabel productLabel = new JLabel("Product");
        productLabel.setFont(new Font("Serif", Font.BOLD, 20));
        productLabel.setBounds(110, 30, 80, 30);
        productLabel.setBorder(null);
        panel.add(productLabel);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        nameLabel.setBounds(10, 70, 80, 25);
        nameLabel.setBorder(null);
        panel.add(nameLabel);

        JLabel ratingLabel = new JLabel("Rating");
        ratingLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        ratingLabel.setBounds(10, 105, 78, 25);
        ratingLabel.setBorder(null);
        panel.add(ratingLabel);

        JLabel caloriesLabel = new JLabel("Calories");
        caloriesLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        caloriesLabel.setBounds(10, 140, 80, 25);
        caloriesLabel.setBorder(null);
        panel.add(caloriesLabel);

        JLabel proteinLabel = new JLabel("Protein");
        proteinLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        proteinLabel.setBounds(10, 175, 80, 25);
        proteinLabel.setBorder(null);
        panel.add(proteinLabel);

        JLabel fatLabel = new JLabel("Fat");
        fatLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        fatLabel.setBounds(10, 210, 80, 25);
        fatLabel.setBorder(null);
        panel.add(fatLabel);

        JLabel sodiumLabel = new JLabel("Sodium");
        sodiumLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        sodiumLabel.setBounds(10, 245, 80, 25);
        sodiumLabel.setBorder(null);
        panel.add(sodiumLabel);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        priceLabel.setBounds(10, 280, 80, 25);
        priceLabel.setBorder(null);
        panel.add(priceLabel);

        JLabel menuNameLabel = new JLabel("Menu name");
        menuNameLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        menuNameLabel.setBorder(null);
        menuNameLabel.setBounds(500, 110, 110, 25);
        panel.add(menuNameLabel);

        JLabel menuPriceLabel = new JLabel("Menu price");
        menuPriceLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        menuPriceLabel.setBounds(500, 150, 110, 25);
        panel.add(menuPriceLabel);

        name = new JTextField();
        name.setFont(new Font("Serif", Font.PLAIN, 17));
        name.setBounds(110, 70, 400, 25);
        name.setBorder(null);
        panel.add(name);

        rating = new JTextField();
        rating.setFont(new Font("Serif", Font.PLAIN, 17));
        rating.setBounds(110, 105, 150, 25);
        rating.setBorder(null);
        panel.add(rating);

        calories = new JTextField();
        calories.setFont(new Font("Serif", Font.PLAIN, 17));
        calories.setBounds(110, 140, 150, 25);
        calories.setBorder(null);
        panel.add(calories);

        protein = new JTextField();
        protein.setFont(new Font("Serif", Font.PLAIN, 17));
        protein.setBounds(110, 175, 150, 25);
        protein.setBorder(null);
        panel.add(protein);

        fat = new JTextField();
        fat.setFont(new Font("Serif", Font.PLAIN, 17));
        fat.setBounds(110, 210, 150, 25);
        fat.setBorder(null);
        panel.add(fat);

        sodium = new JTextField();
        sodium.setFont(new Font("Serif", Font.PLAIN, 17));
        sodium.setBounds(110, 245, 150, 25);
        sodium.setBorder(null);
        panel.add(sodium);

        price = new JTextField();
        price.setFont(new Font("Serif", Font.PLAIN, 17));
        price.setBounds(110, 280, 150, 25);
        price.setBorder(null);
        panel.add(price);

        menuName = new JTextField();
        menuName.setFont(new Font("Serif", Font.PLAIN, 17));
        menuName.setBounds(630, 110, 150, 25);
        menuName.setBorder(null);
        panel.add(menuName);

        menuPrice = new JLabel("");
        menuPrice.setFont(new Font("Serif", Font.PLAIN, 17));
        menuPrice.setBounds(630, 150, 150, 25);
        menuPrice.setBorder(null);
        panel.add(menuPrice);

        table = new JTable();
        LogInView.printTable(table);
        table.setBorder(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setBounds(21, 325, 908, 251);
        panel.add(scrollPane);
        scrollPane.setViewportView(table);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int rand = table.getSelectedRow();
                name.setText(model.getValueAt(rand, 0).toString());
                rating.setText(model.getValueAt(rand, 1).toString());
                calories.setText(model.getValueAt(rand, 2).toString());
                protein.setText(model.getValueAt(rand, 3).toString());
                fat.setText(model.getValueAt(rand, 4).toString());
                sodium.setText(model.getValueAt(rand, 5).toString());
                price.setText(model.getValueAt(rand, 6).toString());
            }
        });

        JButton logOutButton = new JButton("Log out");
        logOutButton.addActionListener(e -> {
            LogInView view = new LogInView(deliveryService);
            view.show();
            dispose();
        });
        logOutButton.setFont(new Font("Serif", Font.PLAIN, 18));
        logOutButton.setBounds(810, 600, 110, 30);
        logOutButton.setFocusable(false);
        logOutButton.setBorder(null);
        panel.add(logOutButton);

        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Serif", Font.PLAIN, 18));
        addButton.setBounds(340, 105, 120, 25);
        addButton.setFocusable(false);
        addButton.setBorder(null);
        addButton.addActionListener(e -> {
            String productName = name.getText();
            double productPrice = Double.parseDouble(price.getText());
            float productCalories = Float.parseFloat(calories.getText());
            float productProtein = Float.parseFloat(protein.getText());
            float productFat = Float.parseFloat(fat.getText());
            float productSodium = Float.parseFloat(sodium.getText());
            float productRating = Float.parseFloat(rating.getText());
            MenuItem newMenu = new MenuItem(productName, productRating, productCalories, productProtein, productFat,
                    productSodium, productPrice);
            deliveryService.addProduct(newMenu);
            LogInView.printTable(table);
            JOptionPane.showMessageDialog(null, "Product successfully inserted");
        });
        panel.add(addButton);

        JButton editButton = new JButton("Edit");
        editButton.setFont(new Font("Serif", Font.PLAIN, 18));
        editButton.setBounds(340, 160, 120, 25);
        editButton.setFocusable(false);
        editButton.setBorder(null);
        editButton.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int row = table.getSelectedRow();
            String productName = model.getValueAt(row, 0).toString();
            float productRating = Float.parseFloat(model.getValueAt(row, 1).toString());
            float productCalories = Float.parseFloat(model.getValueAt(row, 2).toString());
            float productProtein = Float.parseFloat(model.getValueAt(row, 3).toString());
            float productFat = Float.parseFloat(model.getValueAt(row, 4).toString());
            float productSodium = Float.parseFloat(model.getValueAt(row, 5).toString());
            double productPrice = Double.parseDouble(model.getValueAt(row, 6).toString());
            MenuItem oldMenu = new MenuItem(productName, productRating, productCalories, productProtein,
                    productFat, productSodium, productPrice);
            MenuItem newMenu = new MenuItem(name.getText(), Float.parseFloat(rating.getText()),
                    Float.parseFloat(calories.getText()), Float.parseFloat(protein.getText()),
                    Float.parseFloat(fat.getText()), Float.parseFloat(sodium.getText()), Float.parseFloat(price.getText()));
            deliveryService.editProduct(oldMenu, newMenu);
            LogInView.printTable(table);
            JOptionPane.showMessageDialog(null, "Product successfully edited");
        });
        panel.add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Serif", Font.PLAIN, 18));
        deleteButton.setBounds(340, 215, 120, 25);
        deleteButton.setFocusable(false);
        deleteButton.setBorder(null);
        deleteButton.addActionListener(e -> {
            String productName = name.getText();
            double productPrice = Double.parseDouble(price.getText());
            float productCalories = Float.parseFloat(calories.getText());
            float productProtein = Float.parseFloat(protein.getText());
            float productFat = Float.parseFloat(fat.getText());
            float productSodium = Float.parseFloat(sodium.getText());
            float productRating = Float.parseFloat(rating.getText());
            MenuItem menuItem = new MenuItem(productName, productRating, productCalories, productProtein,
                    productFat, productSodium, productPrice);
            deliveryService.deleteProduct(menuItem);
            LogInView.printTable(table);
            JOptionPane.showMessageDialog(null, "Product successfully deleted");
        });
        panel.add(deleteButton);

        JButton addInMenuButton = new JButton("Add in menu");
        addInMenuButton.setFont(new Font("Serif", Font.PLAIN, 18));
        addInMenuButton.setBounds(570, 65, 200, 30);
        addInMenuButton.setFocusable(false);
        addInMenuButton.setBorder(null);
        addInMenuButton.addActionListener(e -> {
            MenuItem menuItem = new MenuItem(name.getText(), Float.parseFloat(rating.getText()),
                    Float.parseFloat(calories.getText()), Float.parseFloat(protein.getText()),
                    Float.parseFloat(fat.getText()), Float.parseFloat(sodium.getText()), Float.parseFloat(price.getText()));
            newMenus.add(menuItem);
            newPrice = newPrice + menuItem.getPrice() * 9 / 10;
            menuPrice.setText(String.valueOf(newPrice));
        });
        panel.add(addInMenuButton);

        JButton endMenuButton = new JButton("End menu");
        endMenuButton.setFont(new Font("Serif", Font.PLAIN, 18));
        endMenuButton.setBounds(570, 190, 200, 30);
        endMenuButton.setFocusable(false);
        endMenuButton.setBorder(null);
        endMenuButton.addActionListener(e -> {
            deliveryService.addMenu(menuName.getText(), newMenus);
            newMenus = null;
            LogInView.printTable(table);
            newPrice = 0;
            JOptionPane.showMessageDialog(null, "New menu successfully added");
        });
        panel.add(endMenuButton);

        JButton reportsButton = new JButton("Reports");
        reportsButton.setFont(new Font("Serif", Font.PLAIN, 18));
        reportsButton.setBounds(590, 250, 150, 30);
        reportsButton.setFocusable(false);
        reportsButton.setBorder(null);
        reportsButton.addActionListener(e -> {
            ReportsView view = new ReportsView(deliveryService);
            view.show();
            dispose();
        });
        panel.add(reportsButton);

        pack();

    }

}
