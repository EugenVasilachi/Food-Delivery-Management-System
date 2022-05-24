package presentation_layer;

import business_layer.DeliveryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportsView extends JFrame {

    private JPanel panel;
    private JTextField fromHour;
    private JTextField byHour;
    private JTextField productsReport;
    private JTextField clientsReport1, clientsReport2;
    private JTextField productsWithinASpecifiedDayReport;
    private JLabel fromHourLabel, byHourLabel;

    public ReportsView(DeliveryService deliveryService) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 600));
        setVisible(true);
        setResizable(false);

        panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);

        fromHourLabel = new JLabel("From hour");
        fromHourLabel.setBorder(null);
        fromHourLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        fromHourLabel.setBounds(25, 50, 110, 30);
        panel.add(fromHourLabel);

        byHourLabel = new JLabel("By hour");
        byHourLabel.setBorder(null);
        byHourLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        byHourLabel.setBounds(25, 90, 110, 30);
        panel.add(byHourLabel);

        fromHour = new JTextField();
        fromHour.setBounds(140, 50, 130, 30);
        fromHour.setFont(new Font("Serif", Font.PLAIN, 16));
        fromHour.setBorder(null);
        panel.add(fromHour);

        byHour = new JTextField();
        byHour.setBounds(140, 90, 130, 30);
        byHour.setFont(new Font("Serif", Font.PLAIN, 16));
        byHour.setBorder(null);
        panel.add(byHour);

        JLabel productsReportLabel = new JLabel("Products ordered more than:");
        productsReportLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        productsReportLabel.setBounds(25, 180, 280, 30);
        productsReportLabel.setBorder(null);
        panel.add(productsReportLabel);

        productsReport = new JTextField();
        productsReport.setFont(new Font("Serif", Font.PLAIN, 16));
        productsReport.setBounds(300, 180, 110, 30);
        productsReport.setBorder(null);
        panel.add(productsReport);

        JLabel clientsReportLabel1 = new JLabel("Clients that have ordered more than:");
        clientsReportLabel1.setFont(new Font("Serif", Font.PLAIN, 16));
        clientsReportLabel1.setBounds(25, 270, 350, 30);
        clientsReportLabel1.setBorder(null);
        panel.add(clientsReportLabel1);

        clientsReport1 = new JTextField();
        clientsReport1.setFont(new Font("Serif", Font.PLAIN, 16));
        clientsReport1.setBounds(280, 270, 50, 30);
        clientsReport1.setBorder(null);
        panel.add(clientsReport1);

        JLabel clientsReportLabel2 = new JLabel("times and the value of the order was higher than:");
        clientsReportLabel2.setFont(new Font("Serif", Font.PLAIN, 16));
        clientsReportLabel2.setBounds(365, 270, 350, 30);
        clientsReportLabel2.setBorder(null);
        panel.add(clientsReportLabel2);

        clientsReport2 = new JTextField();
        clientsReport2.setFont(new Font("Serif", Font.PLAIN, 16));
        clientsReport2.setBorder(null);
        clientsReport2.setBounds(730, 270, 90, 30);
        panel.add(clientsReport2);

        JLabel productsOrderedWithinADay =  new JLabel("Products ordered within day");
        productsOrderedWithinADay.setFont(new Font("Serif", Font.PLAIN, 16));
        productsOrderedWithinADay.setBorder(null);
        productsOrderedWithinADay.setBounds(25, 380, 200, 30);
        panel.add(productsOrderedWithinADay);

        productsWithinASpecifiedDayReport = new JTextField();
        productsWithinASpecifiedDayReport.setFont(new Font("Serif", Font.PLAIN, 16));
        productsWithinASpecifiedDayReport.setBorder(null);
        productsWithinASpecifiedDayReport.setBounds(230, 380, 150, 30);
        panel.add(productsWithinASpecifiedDayReport);

        JButton reportButton1 = new JButton("Generate report");
        reportButton1.setBounds(340, 70, 150, 30);
        reportButton1.setFocusable(false);
        reportButton1.setBorder(null);
        reportButton1.addActionListener(e -> {
            deliveryService.generateInterval(Integer.parseInt(fromHour.getText()),Integer.parseInt(byHour.getText()));
        });
        panel.add(reportButton1);

        JButton reportButton2 = new JButton("Generate report");
        reportButton2.setBounds(470, 180, 150, 30);
        reportButton2.setFocusable(false);
        reportButton2.setBorder(null);
        reportButton2.addActionListener(e -> {
            deliveryService.productsTimes(Integer.parseInt(productsReport.getText()));
        });
        panel.add(reportButton2);

        JButton reportButton3 = new JButton("Generate report");
        reportButton3.setBounds(365, 310, 150, 30);
        reportButton3.setFocusable(false);
        reportButton3.setBorder(null);
        reportButton3.addActionListener(e -> {
            deliveryService.clientsReport(Integer.parseInt(clientsReport1.getText()),
                    Integer.parseInt(clientsReport2.getText()));
        });
        panel.add(reportButton3);

        JButton reportButton4 = new JButton("Generate report");
        reportButton4.setBounds(420, 380, 150, 30);
        reportButton4.setFocusable(false);
        reportButton4.setBorder(null);
        reportButton4.addActionListener(e -> {
            deliveryService.productsOrderedWithinADayReport(Integer.parseInt(productsOrderedWithinADay.getText()));
        });
        panel.add(reportButton4);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Serif", Font.PLAIN, 20));
        backButton.setBounds(260, 480, 130, 30);
        backButton.setBorder(null);
        backButton.setFocusable(false);
        backButton.addActionListener(e -> {
            AdministratorView view = new AdministratorView(deliveryService);
            view.show();
            dispose();
        });
        panel.add(backButton);

        JButton logOutButton = new JButton("Log Out");
        logOutButton.setFont(new Font("Serif", Font.PLAIN, 20));
        logOutButton.setBounds(410, 480, 130, 30);
        logOutButton.setBorder(null);
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(e -> {
            LogInView view = new LogInView(deliveryService);
            view.show();
            dispose();
        });
        panel.add(logOutButton);

        pack();
    }

}
