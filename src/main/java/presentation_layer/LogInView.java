package presentation_layer;

import business_layer.DeliveryService;
import business_layer.User;
import dao.UserDAO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Vector;

public class LogInView extends JFrame {

    private JPanel panel;
    private JTextField username;
    private JPasswordField password;
    private JButton signInButton, signUpButton;
    private JLabel userLabel, passwordLabel, welcomeLabel;
    private UserDAO user = new UserDAO();

    public LogInView(DeliveryService deliveryService) {
        setPreferredSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);

        username = new JTextField();
        username.setFont(new Font("Serif", Font.PLAIN, 19));
        username.setBounds(140, 130, 220, 30);
        username.setBorder(null);

        userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        userLabel.setBounds(50, 126, 90, 30);

        password = new JPasswordField();
        password.setFont(new Font("Serif", Font.PLAIN, 19));
        password.setBounds(140, 200, 220, 30);
        password.setBorder(null);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        passwordLabel.setBounds(50, 196, 90, 30);

        welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        welcomeLabel.setBounds(180, 50, 150, 30);

        signInButton = new JButton("Sign In");
        signInButton.setFont(new Font("Serif", Font.PLAIN, 19));
        signInButton.setBounds(192, 280, 100, 30);
        signInButton.setFocusable(false);
        signInButton.setBorder(null);
        signInButton.addActionListener(e -> {
            String usernameStr = username.getText();
            String passwordStr = password.getText();
            String result = user.selectQuery(usernameStr, passwordStr);
            try {
                if (result == null) {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password");
                } else if (result.equals("admin")) {
                    System.out.println(result + " este admin");
                    AdministratorView administratorView = new AdministratorView(deliveryService);
                    administratorView.show();
                    dispose();
                } else {
                    System.out.println(result + " este user");
                    ClientView clientView = new ClientView(result, deliveryService);
                    clientView.show();
                    dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Serif", Font.PLAIN, 19));
        signUpButton.setBounds(192, 330, 100, 30);
        signUpButton.setFocusable(false);
        signUpButton.setBorder(null);
        signUpButton.addActionListener(e -> {
            String usernameStr = username.getText();
            String passwordStr = password.getText();
            user.insertUser(new User(usernameStr, passwordStr, 0));
        });

        panel.add(username);
        panel.add(userLabel);
        panel.add(password);
        panel.add(passwordLabel);
        panel.add(welcomeLabel);
        panel.add(signInButton);
        panel.add(signUpButton);

        pack();
        setResizable(false);
    }

    public static void printTable(JTable table) {
        File csv = new File("products.csv");
        DefaultTableModel tableModel = new DefaultTableModel();
        try {
            int start = 0;
            InputStreamReader i = new InputStreamReader(new FileInputStream(csv));
            CSVParser csvParser = CSVFormat.DEFAULT.parse(i);
            for (CSVRecord csvRecord : csvParser) {
                if (start == 0) {
                    start = 1;
                    tableModel.addColumn(csvRecord.get(0));
                    tableModel.addColumn(csvRecord.get(1));
                    tableModel.addColumn(csvRecord.get(2));
                    tableModel.addColumn(csvRecord.get(3));
                    tableModel.addColumn(csvRecord.get(4));
                    tableModel.addColumn(csvRecord.get(5));
                    tableModel.addColumn(csvRecord.get(6));
                } else {
                    Vector row = new Vector();
                    row.add(csvRecord.get(0));
                    row.add(csvRecord.get(1));
                    row.add(csvRecord.get(2));
                    row.add(csvRecord.get(3));
                    row.add(csvRecord.get(4));
                    row.add(csvRecord.get(5));
                    row.add(csvRecord.get(6));
                    tableModel.addRow(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        table.setModel(tableModel);
    }

}
